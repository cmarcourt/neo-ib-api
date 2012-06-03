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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Christophe Marcourt
 * @version 1.0.0
 */
final class EventNotifierWrapperTask implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventNotifierWrapperTask.class);
	private final Event event;
	private final EventNotifier eventNotifier;

	EventNotifierWrapperTask(final Event event, final EventNotifier eventNotifier) {
		this.event = event;
		this.eventNotifier = eventNotifier;
	}

	@Override
	public void run() {
		LOGGER.debug("notify event: {}", event.toString());
		eventNotifier.notify(event);
	}

}
