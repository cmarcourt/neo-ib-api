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

import java.util.List;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
abstract class AbstractEventNotifier implements EventNotifier {

	private final EventListenerService eventListenerService;

	AbstractEventNotifier(final EventListenerService eventListenerService) {
		this.eventListenerService = eventListenerService;
	}

	@Override
	public void notify(final Event event) {
		final List<EventListener<Event>> eventListeners = getEventListeners(event);
		if (!eventListeners.isEmpty()) {
			for (final EventListener<Event> eventListener : eventListeners) {
				notifyEvent(eventListener, event);
			}
		}
	}

	private List<EventListener<Event>> getEventListeners(final Event event) {
		return eventListenerService.getEventListeners(event.getAssignableListenerType());
	}

	abstract void notifyEvent(EventListener<Event> eventListener, Event event);
}
