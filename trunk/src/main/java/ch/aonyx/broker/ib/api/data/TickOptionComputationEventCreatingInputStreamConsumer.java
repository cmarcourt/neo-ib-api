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

import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readDouble;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readInt;

import java.io.InputStream;

import ch.aonyx.broker.ib.api.io.AbstractEventCreatingInputStreamConsumerSupport;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class TickOptionComputationEventCreatingInputStreamConsumer extends
		AbstractEventCreatingInputStreamConsumerSupport<TickOptionComputationEvent> {

	private static final int NOT_YET_COMPUTED_0 = 0;
	private static final int NOT_YET_COMPUTED_1 = 1;
	private static final int VERSION = 6;

	public TickOptionComputationEventCreatingInputStreamConsumer(final InputStream inputStream,
			final int serverCurrentVersion) {
		super(inputStream, serverCurrentVersion);
	}

	@Override
	protected TickOptionComputationEvent consumeVersionLess(final InputStream inputStream) {
		final int requestId = readInt(inputStream);
		final int tickType = readInt(inputStream);
		final double impliedVolatility = getComputedValue0(inputStream);
		final double delta = getComputedValue1(inputStream);
		double price = Double.MAX_VALUE;
		double presentValueDividend = Double.MAX_VALUE;
		double gamma = Double.MAX_VALUE;
		double vega = Double.MAX_VALUE;
		double theta = Double.MAX_VALUE;
		double underlyingPrice = Double.MAX_VALUE;
		if ((getVersion() >= VERSION) || (tickType == TickType.MODEL_OPTION_COMPUTATION.getValue())) {
			price = getComputedValue0(inputStream);
			presentValueDividend = getComputedValue0(inputStream);
		}
		if (getVersion() >= VERSION) {
			gamma = getComputedValue1(inputStream);
			vega = getComputedValue1(inputStream);
			theta = getComputedValue1(inputStream);
			underlyingPrice = getComputedValue0(inputStream);
		}
		return createEvent(requestId, tickType, impliedVolatility, delta, price, presentValueDividend, gamma, vega,
				theta, underlyingPrice);
	}

	private double getComputedValue0(final InputStream inputStream) {
		double value = readDouble(inputStream);
		if (value < NOT_YET_COMPUTED_0) {
			value = Double.MAX_VALUE;
		}
		return value;
	}

	private double getComputedValue1(final InputStream inputStream) {
		double value = readDouble(inputStream);
		if (Math.abs(value) > NOT_YET_COMPUTED_1) {
			value = Double.MAX_VALUE;
		}
		return value;
	}

	private TickOptionComputationEvent createEvent(final int requestId, final int tickType,
			final double impliedVolatility, final double delta, final double price, final double presentValueDividend,
			final double gamma, final double vega, final double theta, final double underlyingPrice) {
		return new TickOptionComputationEvent(toRequestId(requestId), TickType.fromValue(tickType), impliedVolatility,
				delta, price, presentValueDividend, gamma, vega, theta, underlyingPrice);
	}

}
