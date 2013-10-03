/*
 * Copyright (C) 2012 Aonyx
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.aonyx.broker.ib.api;

import static ch.aonyx.broker.ib.api.ClientMessageCode.CONNECTION_SERVER_TIME;
import static ch.aonyx.broker.ib.api.ClientMessageCode.ERROR_CREATING_INPUT_STREAM;
import static ch.aonyx.broker.ib.api.ClientMessageCode.ERROR_CREATING_OUPUT_STREAM;
import static ch.aonyx.broker.ib.api.ClientMessageCode.SERVER_CURRENT_VERSION;
import static ch.aonyx.broker.ib.api.ClientMessageCode.SUBSCRIPTION_ALREADY_SUBSCRIBED;
import static ch.aonyx.broker.ib.api.ClientMessageCode.UPDATE_TWS;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readString;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.aonyx.broker.ib.api.io.IOStreamException;
import ch.aonyx.broker.ib.api.io.OutputStreamRequestSender;
import ch.aonyx.broker.ib.api.io.RequestSender;
import ch.aonyx.broker.ib.api.system.RegisterClientRequest;
import ch.aonyx.broker.ib.api.util.AnnotationUtils;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.lmax.disruptor.dsl.Disruptor;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
final class SocketSession implements Session, OrderIdInternalIdListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(SocketSession.class);
    private static final int RING_BUFFER_SIZE = 128;
    private VersionProperties versionProperties;
    private int clientCurrentVersion;
    private int serverMinimumRequiredVersion;
    private int serverCurrentVersion;
    private final Socket socket;
    private final int clientId;
    private OutputStream outputStream;
    private RequestSender requestSender;
    private EventInputStreamConsumerThread eventInputStreamConsumerThread;
    private boolean canStart = true;
    private boolean started = false;
    private boolean handShakeDone = false;
    private final HashMap<EventListener<? extends Event>, Class<?>> annotatedListenerCache = Maps.newHashMap();
    private final ServerMessageEventListener defaultServerMessageEventListener = new ServerMessageEventLoggingListener();
    private final InitializeNextValidOrderIdEventListener nextValidOrderIdEventListener = new InitializeNextValidOrderIdEventListener();
    private final ClientCallback clientCallback;
    private final ClientCallbackNonBlockingCaller callbackNonBlockingCaller = new ClientCallbackNonBlockingCaller();
    private final Queue<Request> requests = new ConcurrentLinkedQueue<Request>();
    private final Queue<OrderRequest> delayOrderRequests = new ConcurrentLinkedQueue<OrderRequest>();
    private final Queue<SubscriptionRequest> subscriptionRequests = new ConcurrentLinkedQueue<SubscriptionRequest>();
    private final RequestBiMapService requestService = new RequestBiMapService();
    private final EventNotifier eventNotifier;
    private final Multimap<Class<?>, EventListener<? extends Event>> eventListenerMap = HashMultimap.create();
    private final EventListenerService eventListenerService = new EventListenerMultimapService(eventListenerMap);
    private Disruptor<EventWrapper> disruptor;

    SocketSession(final Socket socket, final int clientId, final ClientCallback clientCallback) {
        Validate.notNull(socket);
        Validate.notNull(clientCallback);
        this.socket = socket;
        this.clientId = clientId;
        this.clientCallback = clientCallback;
        checkForEmptyEventListener();
        registerListeners();
        eventNotifier = new ConcurrentlyEventNotifier(eventListenerService);
        setVersions();
        createOutputStreamRequestSender();
        createDisruptor();
        createEventInputStreamThread();
        handShake();
    }

    private void checkForEmptyEventListener() {
        if (!eventListenerMap.containsKey(EmptyEventListener.class)) {
            eventListenerMap.put(EmptyEventListener.class, new EmptyEventListener());
        }
    }

    private void registerListeners() {
        registerListener(defaultServerMessageEventListener);
        registerListener(nextValidOrderIdEventListener);
        OrderIdInternalIdBinding.getInstance().addListener(this);
    }

    @Override
    public <E extends Event> void registerListener(final EventListener<E> listener) {
        Validate.notNull(listener);
        final Class<?> eventListenerType = getEventListenerType(listener);
        eventListenerMap.put(eventListenerType, listener);
    }

    private <E extends Event> Class<?> getEventListenerType(final EventListener<E> listener) {
        if (!annotatedListenerCache.containsKey(listener)) {
            final Pair<AssignableListenerType, Class<?>> immutablePair = AnnotationUtils.findAnnotation(
                    listener.getClass(), AssignableListenerType.class);
            if (immutablePair != null) {
                annotatedListenerCache.put(listener, immutablePair.getRight());
            } else {
                LOGGER.warn("Annotation {} was not found for listener {}", AssignableListenerType.class.getName(),
                        listener.toString());
            }
        }
        return annotatedListenerCache.get(listener);
    }

    private void setVersions() {
        versionProperties = new VersionProperties();
        clientCurrentVersion = versionProperties.getClientCurrentVersion();
        serverMinimumRequiredVersion = versionProperties.getServerMinimumRequiredVersion();
    }

    private void createOutputStreamRequestSender() {
        try {
            outputStream = new DataOutputStream(socket.getOutputStream());
            requestSender = new OutputStreamRequestSender(outputStream);
        } catch (final IOException e) {
            callbackNonBlockingCaller.onFailure(clientCallback,
                    new IOStreamException(ERROR_CREATING_OUPUT_STREAM, e.getMessage(), e));
            LOGGER.error("", e);
            closeSession();
        }
    }

    private void closeSession() {
        IOUtils.closeQuietly(outputStream);
        eventInputStreamConsumerThread.stop();
        IOUtils.closeQuietly(socket);
    }

    @SuppressWarnings("unchecked")
    private void createDisruptor() {
        disruptor = new Disruptor<EventWrapper>(new EventWrapperFactory(), RING_BUFFER_SIZE,
                Executors.newCachedThreadPool());
        disruptor.handleEventsWith(new ConcurrentlyEventHandler(eventNotifier));
    }

    private void createEventInputStreamThread() {
        try {
            eventInputStreamConsumerThread = new EventInputStreamConsumerThread(new DataInputStream(
                    socket.getInputStream()), disruptor.getRingBuffer());
        } catch (final IOException e) {
            callbackNonBlockingCaller.onFailure(clientCallback,
                    new IOStreamException(ERROR_CREATING_INPUT_STREAM, e.getMessage(), e));
            LOGGER.error("", e);
            closeSession();
        }
    }

    private void handShake() {
        if (!handShakeDone) {
            handShakeDone = true;
            serverCurrentVersionRequest();
            notifyServerCurrentVersion();
            notifyServerTime();
            if (serverCurrentVersion < serverMinimumRequiredVersion) {
                final String detailedMessage = "Minimum server version required '" + serverMinimumRequiredVersion
                        + "', current server version '" + serverCurrentVersion + "'";
                callbackNonBlockingCaller.onFailure(clientCallback, new NeoIbApiClientException(UPDATE_TWS,
                        detailedMessage));
                LOGGER.error(detailedMessage);
                closeSession();
                canStart = false;
            } else {
                registerClient();
            }
        }
    }

    private void serverCurrentVersionRequest() {
        final ServerCurrentVersionSynchronousRequest serverCurrentVersionSynchronousRequest = new ServerCurrentVersionSynchronousRequest(
                clientCurrentVersion, outputStream, eventInputStreamConsumerThread.getInputStream());
        serverCurrentVersion = serverCurrentVersionSynchronousRequest.getResponse();
    }

    private void notifyServerCurrentVersion() {
        eventInputStreamConsumerThread.setServerCurrentVersion(serverCurrentVersion);
        LOGGER.info("current server version: {}", serverCurrentVersion);
        defaultServerMessageEventListener.onInfo(newServerMessageEvent(SERVER_CURRENT_VERSION, serverCurrentVersion));
    }

    private ServerMessageEvent newServerMessageEvent(final ClientMessageCode clientMessageCode,
            final Object... parameters) {
        return new ServerMessageEvent(clientMessageCode.getCode(), clientMessageCode.getMessage(), parameters);
    }

    private void notifyServerTime() {
        final String serverTime = getServerTime();
        LOGGER.info("server time: {}", serverTime);
        defaultServerMessageEventListener.onInfo(newServerMessageEvent(CONNECTION_SERVER_TIME, serverTime));
    }

    private String getServerTime() {
        return readString(eventInputStreamConsumerThread.getInputStream());
    }

    private void registerClient() {
        final RegisterClientRequest registerClientRequest = new RegisterClientRequest(clientId);
        sendRequest(registerClientRequest);
    }

    private synchronized void sendRequest(final Request request) {
        try {
            addAndBindRequest(request);
            final long beginningTimeNano = System.nanoTime();
            requestSender.send(request);
            final long endingTimeNano = System.nanoTime();
            final CallbackObject callbackObject = new CallbackObject(request, beginningTimeNano, endingTimeNano);
            callbackNonBlockingCaller.onSuccess(clientCallback, callbackObject);
        } catch (final NeoIbApiClientException exception) {
            callbackNonBlockingCaller.onFailure(clientCallback, exception);
        }
    }

    private void addAndBindRequest(final Request request) {
        if (!requestService.contains(request.getId())) {
            requestService.addRequest(request);
            bindRequest(request);
        }
    }

    private void bindRequest(final Request request) {
        if (OrderRequest.class.isAssignableFrom(request.getClass())) {
            OrderIdInternalIdBinding.getInstance().addAndBind(request);
        } else {
            RequestIdInternalIdBinding.getInstance().addAndBind(request);
        }
    }

    @Override
    public void start() {
        if (canStart) {
            disruptor.start();
            startEventInputStreamConsumerThread();
            sendRequests();
            started = true;
        } else {
            final String detailedMessage = "Due to several problems, session can't start";
            callbackNonBlockingCaller.onFailure(clientCallback, new SessionException(
                    ClientMessageCode.CANT_START_SESSION, detailedMessage));
            LOGGER.error(detailedMessage);
            closeSession();
        }
    }

    private void startEventInputStreamConsumerThread() {
        new Thread(eventInputStreamConsumerThread, eventInputStreamConsumerThread.getThreadName()).start();
    }

    private void sendRequests() {
        for (final Request request : requests) {
            sendRequest(request);
        }
    }

    @Override
    public boolean isStarted() {
        return started;
    }

    @Override
    public void stop() {
        eventInputStreamConsumerThread.stop();
        disruptor.shutdown();
        started = false;
    }

    @Override
    public void request(final SimpleRequest request) {
        Validate.notNull(request);
        decorateRequestAndSend(request);
    }

    private void decorateRequestAndSend(final Request request) {
        decorateRequest(request);
        sendNowOrDelay(request);
    }

    private void decorateRequest(final Request request) {
        request.setServerCurrentVersion(serverCurrentVersion);
    }

    private void sendNowOrDelay(final Request request) {
        if (started) {
            sendRequest(request);
        } else {
            requests.add(request);
        }
    }

    @Override
    public void orderRequest(final OrderRequest request) {
        Validate.notNull(request);
        decorateRequest(request);
        if (OrderIdInternalIdBinding.getInstance().isInitialized()) {
            sendNowOrDelay(request);
        } else {
            delaySendOrderRequest(request);
        }
    }

    private void delaySendOrderRequest(final OrderRequest request) {
        delayOrderRequests.add(request);
    }

    @Override
    public void sequenceInitialized() {
        for (final OrderRequest orderRequest : delayOrderRequests) {
            sendNowOrDelay(orderRequest);
        }
    }

    @Override
    public void subscribe(final SubscriptionRequest request) {
        Validate.notNull(request);
        if (!isAlreadySubscribed(request)) {
            decorateRequestAndSend(request);
        } else {
            callbackNonBlockingCaller.onFailure(clientCallback, new SessionException(SUBSCRIPTION_ALREADY_SUBSCRIBED,
                    request.toString()));
        }
    }

    private boolean isAlreadySubscribed(final SubscriptionRequest request) {
        for (final SubscriptionRequest sr : subscriptionRequests) {
            if (sr.equals(request)) {
                return true;
            }
        }
        subscriptionRequests.add(request);
        return false;
    }

    @Override
    public void unsubscribe(final UnsubscriptionRequest request) {
        Validate.notNull(request);
        if (removeSubscription(request)) {
            decorateRequestAndSend(request);
        }
    }

    private boolean removeSubscription(final UnsubscriptionRequest request) {
        for (final SubscriptionRequest subscriptionRequest : subscriptionRequests) {
            if (subscriptionRequest.getId().equals(request.getId())) {
                subscriptionRequests.remove(subscriptionRequest);
                return true;
            }
        }
        return false;
    }

    @Override
    public RequestService getRequestService() {
        return requestService;
    }

    @Override
    public <E extends Event> void unregisterListener(final EventListener<E> listener) {
        if (eventListenerService.containsListener(listener)) {
            eventListenerMap.remove(getEventListenerType(listener), listener);
        }
    }

    @Override
    public EventListenerService getEventListenerService() {
        return eventListenerService;
    }

}
