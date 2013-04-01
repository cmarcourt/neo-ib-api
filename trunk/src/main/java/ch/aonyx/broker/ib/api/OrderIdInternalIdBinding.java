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

import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.event.EventListenerList;

import ch.aonyx.broker.ib.api.util.StringIdUtils;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
final class OrderIdInternalIdBinding {

    private static final OrderIdInternalIdBinding INSTANCE = new OrderIdInternalIdBinding();
    private final BiMap<Id, Integer> binding;
    private AtomicInteger sequence;
    private boolean initialized;
    private final EventListenerList listenerList = new EventListenerList();

    static OrderIdInternalIdBinding getInstance() {
        return INSTANCE;
    }

    void addListener(final OrderIdInternalIdListener listener) {
        listenerList.add(OrderIdInternalIdListener.class, listener);
    }

    /**
     * Initialization must be done once before using any other methods. Initialization can only be done once.
     */
    synchronized void initializeSequence(final int nextValidOrderId) {
        if (!initialized) {
            initialized = true;
            sequence = new AtomicInteger(nextValidOrderId);
            notifyListeners();
        }
    }

    private void notifyListeners() {
        final OrderIdInternalIdListener[] listeners = listenerList.getListeners(OrderIdInternalIdListener.class);
        for (final OrderIdInternalIdListener listener : listeners) {
            listener.sequenceInitialized();
        }
    }

    boolean isInitialized() {
        return initialized;
    }

    private OrderIdInternalIdBinding() {
        binding = HashBiMap.create();
    }

    void addAndBind(final Request request) {
        bind(request.getId(), getBindingId());
    }

    private int getBindingId() {
        final int value = sequence.get();
        if (value < 0) {
            return sequence.getAndDecrement();
        } else {
            return sequence.getAndIncrement();
        }
    }

    void bind(final Id id, final int bindingId) {
        binding.put(id, bindingId);
    }

    boolean containsOrderId(final Id orderId) {
        return binding.containsKey(orderId);
    }

    Id getOrderId(final int internalId) {
        if (containsInternalId(internalId)) {
            return binding.inverse().get(internalId);
        }
        return bindAndGetUnexistingId(internalId);
    }

    boolean containsInternalId(final int internalId) {
        return binding.containsValue(internalId);
    }

    private Id bindAndGetUnexistingId(final int internalId) {
        final Id id = new OrderStringId(StringIdUtils.uniqueRandomId());
        bind(id, internalId);
        return id;
    }

    int getInternalId(final Id orderId) {
        return binding.get(orderId);
    }
}
