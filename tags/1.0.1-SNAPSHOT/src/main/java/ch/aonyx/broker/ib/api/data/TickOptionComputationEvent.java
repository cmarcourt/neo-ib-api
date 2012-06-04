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

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ch.aonyx.broker.ib.api.Id;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class TickOptionComputationEvent extends AbstractTickEvent {

	private final double impliedVolatility;
	private final double delta;
	private final double price;
	private final double presentValueDividend;
	private final double gamma;
	private final double vega;
	private final double theta;
	private final double underlyingPrice;

	public TickOptionComputationEvent(final Id requestId, final TickType type, final double impliedVolatility,
			final double delta, final double price, final double presentValueDividend, final double gamma,
			final double vega, final double theta, final double underlyingPrice) {
		super(requestId, type);
		this.impliedVolatility = impliedVolatility;
		this.delta = delta;
		this.price = price;
		this.presentValueDividend = presentValueDividend;
		this.gamma = gamma;
		this.vega = vega;
		this.theta = theta;
		this.underlyingPrice = underlyingPrice;
	}

	@Override
	public Class<?> getAssignableListenerType() {
		return TickOptionComputationEventListener.class;
	}

	public final double getImpliedVolatility() {
		return impliedVolatility;
	}

	public final double getDelta() {
		return delta;
	}

	public final double getPrice() {
		return price;
	}

	public final double getPresentValueDividend() {
		return presentValueDividend;
	}

	public final double getGamma() {
		return gamma;
	}

	public final double getVega() {
		return vega;
	}

	public final double getTheta() {
		return theta;
	}

	public final double getUnderlyingPrice() {
		return underlyingPrice;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
