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

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
final class EventListenerMultimapService implements EventListenerService {

	private final Multimap<Class<?>, EventListener<? extends Event>> eventListenerMap;

	EventListenerMultimapService(final Multimap<Class<?>, EventListener<? extends Event>> eventListenerMap) {
		this.eventListenerMap = eventListenerMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E extends Event> List<EventListener<E>> getEventListeners(final Class<?> eventListenerType) {
		final List<EventListener<E>> eventListeners = Lists.newArrayList();
		final Collection<EventListener<? extends Event>> eventListenerCollection = eventListenerMap
				.get(eventListenerType);
		for (final EventListener<? extends Event> eventListener : eventListenerCollection) {
			eventListeners.add((EventListener<E>) eventListener);
		}
		return eventListeners;
	}

	@Override
	public <E extends Event> boolean containsListener(final EventListener<E> listener) {
		for (final Class<?> key : getKeys()) {
			final List<EventListener<E>> eventListeners = getEventListeners(key);
			for (final EventListener<E> eventListener : eventListeners) {
				if (eventListener.equals(listener)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Set<Class<?>> getKeys() {
		return eventListenerMap.keySet();
	}

}
