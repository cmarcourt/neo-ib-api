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

/**
 * @author Christophe Marcourt
 * @version 1.0.0
 */
final class ConcurrentlyEventPublisher extends AbstractEventPublisher {

	private Executor executor;

	ConcurrentlyEventPublisher(final EventNotifier eventNotifier) {
		super(eventNotifier);
		createExecutor();
	}

	private void createExecutor() {
		executor = Executors.newCachedThreadPool();
	}

	@Override
	public void publishEvent(final Event event) {
		if (event != null) {
			event.setSequence(getSequence().getAndIncrement());
			executor.execute(new EventNotifierWrapperTask(event, getEventNotifier()));
		}
	}

}
