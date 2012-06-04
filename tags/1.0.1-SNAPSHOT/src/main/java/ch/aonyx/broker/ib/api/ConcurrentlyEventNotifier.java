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

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
final class ConcurrentlyEventNotifier extends AbstractEventNotifier {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConcurrentlyEventNotifier.class);
	private final Executor executor;

	ConcurrentlyEventNotifier(final EventListenerService eventListenerService) {
		super(eventListenerService);
		executor = Executors.newCachedThreadPool();
	}

	@Override
	void notifyEvent(final EventListener<Event> eventListener, final Event event) {
		executor.execute(new Runnable() {
			@Override
			public void run() {
				LOGGER.debug("listener '{}' notifies event: {}", eventListener.getClass().getName(), event.toString());
				eventListener.notify(event);
			}
		});
	}

}
