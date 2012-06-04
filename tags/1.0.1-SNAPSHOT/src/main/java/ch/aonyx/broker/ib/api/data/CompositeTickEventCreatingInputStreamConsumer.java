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
package ch.aonyx.broker.ib.api.data;

import static ch.aonyx.broker.ib.api.data.TickType.ASK_SIZE;
import static ch.aonyx.broker.ib.api.data.TickType.BID_SIZE;
import static ch.aonyx.broker.ib.api.data.TickType.LAST_SIZE;
import static ch.aonyx.broker.ib.api.data.TickType.UNKNOWN;
import static ch.aonyx.broker.ib.api.data.TickType.fromValue;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readDouble;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readInt;

import java.io.InputStream;

import org.apache.commons.lang3.BooleanUtils;

import ch.aonyx.broker.ib.api.io.AbstractEventCreatingInputStreamConsumerSupport;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class CompositeTickEventCreatingInputStreamConsumer extends
		AbstractEventCreatingInputStreamConsumerSupport<CompositeTickEvent> {

	private static final int VERSION_2 = 2;
	private static final int VERSION_3 = 3;

	public CompositeTickEventCreatingInputStreamConsumer(final InputStream inputStream, final int serverCurrentVersion) {
		super(inputStream, serverCurrentVersion);
	}

	@Override
	protected CompositeTickEvent consumeVersionLess(final InputStream inputStream) {
		final int requestId = readInt(inputStream);
		final int tickType = readInt(inputStream);
		final double price = readDouble(inputStream);
		int size = 0;
		if (getVersion() >= VERSION_2) {
			size = readInt(inputStream);
		}
		int autoExecute = 0;
		if (getVersion() >= VERSION_3) {
			autoExecute = readInt(inputStream);
		}

		final TickPriceEvent tickPriceEvent = createTickPriceEvent(requestId, tickType, price, autoExecute);
		final TickSizeEvent tickSizeEvent = createTickSizeEvent(requestId, tickType, size);
		return createCompositeTickEvent(tickPriceEvent, tickSizeEvent);
	}

	private TickPriceEvent createTickPriceEvent(final int requestId, final int tickType, final double price,
			final int autoExecute) {
		return new TickPriceEvent(toRequestId(requestId), TickType.fromValue(tickType), price,
				BooleanUtils.toBoolean(autoExecute));
	}

	private TickSizeEvent createTickSizeEvent(final int requestId, final int tickType, final int size) {
		if (getVersion() >= VERSION_2) {
			final TickType type = fromValue(tickType);
			int sizeTickType = UNKNOWN.getValue();
			switch (type) {
			case BID_PRICE:
				sizeTickType = BID_SIZE.getValue();
				break;
			case ASK_PRICE:
				sizeTickType = ASK_SIZE.getValue();
				break;
			case LAST_PRICE:
				sizeTickType = LAST_SIZE.getValue();
				break;
			}
			if (sizeTickType != UNKNOWN.getValue()) {
				return new TickSizeEvent(toRequestId(requestId), TickType.fromValue(sizeTickType), size);
			}
		}
		return null;
	}

	private CompositeTickEvent createCompositeTickEvent(final TickPriceEvent tickPriceEvent,
			final TickSizeEvent tickSizeEvent) {
		return new CompositeTickEvent(tickPriceEvent, tickSizeEvent);
	}

}
