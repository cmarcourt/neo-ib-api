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
package ch.aonyx.broker.ib.api.order;

import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readDouble;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readInt;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readString;

import java.io.InputStream;

import ch.aonyx.broker.ib.api.io.AbstractEventCreatingInputStreamConsumerSupport;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class OrderStateUpdateEventCreatingInputStreamConsumer extends
		AbstractEventCreatingInputStreamConsumerSupport<OrderStateUpdateEvent> {

	public OrderStateUpdateEventCreatingInputStreamConsumer(final InputStream inputStream,
			final int serverCurrentVersion) {
		super(inputStream, serverCurrentVersion);
	}

	@Override
	protected OrderStateUpdateEvent consumeVersionLess(final InputStream inputStream) {
		final int orderId = readInt(inputStream);
		final String orderStatus = readString(inputStream);
		final int filledQuantity = readInt(inputStream);
		final int remainingQuantity = readInt(inputStream);
		final double averageFilledPrice = readDouble(inputStream);
		int permanentId = 0;
		if (getVersion() >= 2) {
			permanentId = readInt(inputStream);
		}
		int parentOrderId = 0;
		if (getVersion() >= 3) {
			parentOrderId = readInt(inputStream);
		}
		double lastFilledPrice = 0;
		if (getVersion() >= 4) {
			lastFilledPrice = readDouble(inputStream);
		}
		int clientId = 0;
		if (getVersion() >= 5) {
			clientId = readInt(inputStream);
		}
		String heldCause = null;
		if (getVersion() >= 6) {
			heldCause = readString(inputStream);
		}
		return createEvent(orderId, orderStatus, filledQuantity, remainingQuantity, averageFilledPrice, permanentId,
				parentOrderId, lastFilledPrice, clientId, heldCause);
	}

	private OrderStateUpdateEvent createEvent(final int orderId, final String orderStatus, final int filledQuantity,
			final int remainingQuantity, final double averageFilledPrice, final int permanentId,
			final int parentOrderId, final double lastFilledPrice, final int clientId, final String heldCause) {
		return new OrderStateUpdateEvent(toOrderId(orderId), OrderStatus.fromLabel(orderStatus), filledQuantity,
				remainingQuantity, averageFilledPrice, permanentId, toOrderId(parentOrderId), lastFilledPrice,
				clientId, heldCause);
	}

}
