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

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ch.aonyx.broker.ib.api.AbstractEventSupport;
import ch.aonyx.broker.ib.api.Id;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class RealTimeBarEvent extends AbstractEventSupport {

    private final long timestamp;
    private final double open;
    private final double high;
    private final double low;
    private final double close;
    private final long volume;
    private final double weightedAveragePrice;
    private final int tradeNumber;

    public RealTimeBarEvent(final Id requestId, final long timestamp, final double open, final double high,
            final double low, final double close, final long volume, final double weightedAveragePrice,
            final int tradeNumber) {
        super(requestId);
        this.timestamp = timestamp;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.weightedAveragePrice = weightedAveragePrice;
        this.tradeNumber = tradeNumber;
    }

    @Override
    public Class<?> getAssignableListenerType() {
        return RealTimeBarEventListener.class;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public double getOpen() {
        return open;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getClose() {
        return close;
    }

    public long getVolume() {
        return volume;
    }

    public double getWeightedAveragePrice() {
        return weightedAveragePrice;
    }

    public int getTradeNumber() {
        return tradeNumber;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
