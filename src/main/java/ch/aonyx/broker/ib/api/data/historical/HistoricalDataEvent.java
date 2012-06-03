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
package ch.aonyx.broker.ib.api.data.historical;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ch.aonyx.broker.ib.api.AbstractEventSupport;
import ch.aonyx.broker.ib.api.Id;

/**
 * @author Christophe Marcourt
 * @version 1.0.0
 */
public final class HistoricalDataEvent extends AbstractEventSupport {

	private final String dateTime;
	private final double open;
	private final double high;
	private final double low;
	private final double close;
	private final int volume;
	private final int tradeNumber;
	private final double weightedAveragePrice;
	private final boolean hasGap;

	public HistoricalDataEvent(final Id requestId, final String dateTime, final double open, final double high,
			final double low, final double close, final int volume, final int tradeNumber,
			final double weightedAveragePrice, final boolean hasGap) {
		super(requestId);
		this.dateTime = dateTime;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
		this.tradeNumber = tradeNumber;
		this.weightedAveragePrice = weightedAveragePrice;
		this.hasGap = hasGap;
	}

	@Override
	public Class<?> getAssignableListenerType() {
		return HistoricalDataEventListener.class;
	}

	public final String getDateTime() {
		return dateTime;
	}

	public final double getOpen() {
		return open;
	}

	public final double getHigh() {
		return high;
	}

	public final double getLow() {
		return low;
	}

	public final double getClose() {
		return close;
	}

	public final int getVolume() {
		return volume;
	}

	public final int getTradeNumber() {
		return tradeNumber;
	}

	public final double getWeightedAveragePrice() {
		return weightedAveragePrice;
	}

	public final boolean isHasGap() {
		return hasGap;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
