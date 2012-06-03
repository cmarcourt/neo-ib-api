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

import ch.aonyx.broker.ib.api.order.NextValidOrderIdEvent;
import ch.aonyx.broker.ib.api.order.NextValidOrderIdEventListener;

/**
 * @author Christophe Marcourt
 * @version 1.0.0
 */
final class InitializeNextValidOrderIdEventListener implements NextValidOrderIdEventListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(InitializeNextValidOrderIdEventListener.class);

	@Override
	public void notify(final NextValidOrderIdEvent event) {
		OrderIdInternalIdBinding.getInstance().initializeSequence(event.getNextValidOrderId());
		LOGGER.info("initialized next valid order id: {}", event.getNextValidOrderId());
	}

}
