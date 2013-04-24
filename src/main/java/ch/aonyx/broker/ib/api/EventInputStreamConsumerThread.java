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

import static ch.aonyx.broker.ib.api.IncomingMessageId.fromId;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readInt;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.aonyx.broker.ib.api.io.EventCreatingConsumer;

import com.lmax.disruptor.RingBuffer;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
final class EventInputStreamConsumerThread implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventInputStreamConsumerThread.class);
    private final String threadName = "consumer";
    private final InputStream inputStream;
    private final InputStreamConsumerService consumerService;
    private final RingBuffer<EventWrapper> ringBuffer;
    private final EventWrapperTranslator eventWrapperTranslator = new EventWrapperTranslator();
    private boolean running = true;

    EventInputStreamConsumerThread(final InputStream inputStream, final RingBuffer<EventWrapper> ringBuffer) {
        this.inputStream = inputStream;
        this.ringBuffer = ringBuffer;
        consumerService = new InputStreamConsumerService(this.inputStream);
    }

    InputStream getInputStream() {
        return inputStream;
    }

    void setServerCurrentVersion(final int serverCurrentVersion) {
        consumerService.setServerCurrentVersion(serverCurrentVersion);
    }

    @Override
    public void run() {
        while (running) {
            final Event event = consumeMessage();
            publishEvent(event);
        }
        IOUtils.closeQuietly(inputStream);
    }

    private <E extends Event> Event consumeMessage() {
        final int messageId = readInt(inputStream);
        final EventCreatingConsumer<E> consumer = consumerService.getEventCreatingConsumer(fromId(messageId));
        return consumer.consume();
    }

    private void publishEvent(final Event event) {
        LOGGER.debug("dispatch event: {} to disruptor", event.toString());
        ringBuffer.publishEvent(eventWrapperTranslator, event);
    }

    void stop() {
        running = false;
    }

    String getThreadName() {
        return threadName;
    }
}