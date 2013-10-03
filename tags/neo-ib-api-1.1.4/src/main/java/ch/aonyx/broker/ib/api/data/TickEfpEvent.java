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
public final class TickEfpEvent extends AbstractTickEvent {

    private final double basisPoints;
    private final String formattedBasisPoints;
    private final double impliedFuturePrice;
    private final int holdDays;
    private final String futureExpiry;
    private final double dividendImpact;
    private final double dividendToExpiry;

    public TickEfpEvent(final Id requestId, final TickType type, final double basisPoints,
            final String formattedBasisPoints, final double impliedFuturePrice, final int holdDays,
            final String futureExpiry, final double dividendImpact, final double dividendToExpiry) {
        super(requestId, type);
        this.basisPoints = basisPoints;
        this.formattedBasisPoints = formattedBasisPoints;
        this.impliedFuturePrice = impliedFuturePrice;
        this.holdDays = holdDays;
        this.futureExpiry = futureExpiry;
        this.dividendImpact = dividendImpact;
        this.dividendToExpiry = dividendToExpiry;
    }

    @Override
    public Class<?> getAssignableListenerType() {
        return TickEfpEventListener.class;
    }

    public double getBasisPoints() {
        return basisPoints;
    }

    public String getFormattedBasisPoints() {
        return formattedBasisPoints;
    }

    public double getImpliedFuturePrice() {
        return impliedFuturePrice;
    }

    public int getHoldDays() {
        return holdDays;
    }

    public String getFutureExpiry() {
        return futureExpiry;
    }

    public double getDividendImpact() {
        return dividendImpact;
    }

    public double getDividendToExpiry() {
        return dividendToExpiry;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
