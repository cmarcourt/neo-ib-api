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
package ch.aonyx.broker.ib.api.data.bar;

import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readDouble;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readInt;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readLong;

import java.io.InputStream;

import ch.aonyx.broker.ib.api.io.AbstractEventCreatingInputStreamConsumerSupport;

/**
 * @author Christophe Marcourt
 * @version 1.0.0
 */
public final class RealTimeBarEventCreatingInputStreamConsumer extends
		AbstractEventCreatingInputStreamConsumerSupport<RealTimeBarEvent> {

	public RealTimeBarEventCreatingInputStreamConsumer(final InputStream inputStream, final int serverCurrentVersion) {
		super(inputStream, serverCurrentVersion);
	}

	@Override
	protected RealTimeBarEvent consumeVersionLess(final InputStream inputStream) {
		final int requestId = readInt(inputStream);
		final long timestamp = readLong(inputStream);
		final double open = readDouble(inputStream);
		final double high = readDouble(inputStream);
		final double low = readDouble(inputStream);
		final double close = readDouble(inputStream);
		final long volume = readLong(inputStream);
		final double weightedAveragePrice = readDouble(inputStream);
		final int tradeNumber = readInt(inputStream);
		return createEvent(requestId, timestamp, open, high, low, close, volume, weightedAveragePrice, tradeNumber);
	}

	private RealTimeBarEvent createEvent(final int requestId, final long timestamp, final double open,
			final double high, final double low, final double close, final long volume,
			final double weightedAveragePrice, final int tradeNumber) {
		return new RealTimeBarEvent(toRequestId(requestId), timestamp, open, high, low, close, volume,
				weightedAveragePrice, tradeNumber);
	}

}
