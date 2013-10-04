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

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ch.aonyx.broker.ib.api.AbstractRequestSupport;
import ch.aonyx.broker.ib.api.OutgoingMessageId;
import ch.aonyx.broker.ib.api.SubscriptionRequest;
import ch.aonyx.broker.ib.api.contract.Contract;
import ch.aonyx.broker.ib.api.contract.SecurityType;
import ch.aonyx.broker.ib.api.order.ComboLeg;
import ch.aonyx.broker.ib.api.util.ByteArrayRequestBuilder;
import ch.aonyx.broker.ib.api.util.RequestBuilder;
import ch.aonyx.broker.ib.api.util.StringIdUtils;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class HistoricalDataSubscriptionRequest extends AbstractRequestSupport implements SubscriptionRequest {

    private static final int VERSION = 4;
    private final Contract contract;
    private final String endDateTime;
    private final TimeSpan duration;
    private final BarSize barSize;
    private final HistoricalDataType historicalDataType;
    private final boolean useRegularTradingHours;
    private final DateFormat dateFormat;

    public HistoricalDataSubscriptionRequest(final Contract contract, final String endDateTime,
            final TimeSpan duration, final BarSize barSize, final HistoricalDataType historicalDataType,
            final boolean useRegularTradingHours, final DateFormat dateFormat) {
        this(StringIdUtils.uniqueIdFromContract(contract), contract, endDateTime, duration, barSize,
                historicalDataType, useRegularTradingHours, dateFormat);
    }

    public HistoricalDataSubscriptionRequest(final String id, final Contract contract, final String endDateTime,
            final TimeSpan duration, final BarSize barSize, final HistoricalDataType historicalDataType,
            final boolean useRegularTradingHours, final DateFormat dateFormat) {
        super(id);
        this.contract = contract;
        this.endDateTime = endDateTime;
        this.duration = duration;
        this.barSize = barSize;
        this.historicalDataType = historicalDataType;
        this.useRegularTradingHours = useRegularTradingHours;
        this.dateFormat = dateFormat;
    }

    @Override
    public byte[] getBytes() {
        final RequestBuilder builder = createRequestBuilder();
        return builder.toBytes();
    }

    private RequestBuilder createRequestBuilder() {
        final RequestBuilder builder = new ByteArrayRequestBuilder();
        builder.append(OutgoingMessageId.HISTORICAL_DATA_SUBSCRIPTION_REQUEST.getId());
        builder.append(VERSION);
        builder.append(toInternalId(getId()));
        appendContract(builder);
        builder.append(endDateTime);
        builder.append(barSize.getFormattedBarSize());
        builder.append(duration.getFormattedDuration());
        builder.append(useRegularTradingHours);
        builder.append(historicalDataType.getLabel());
        builder.append(dateFormat.getValue());
        appendComboLegs(builder);
        return builder;
    }

    private void appendContract(final RequestBuilder builder) {
        builder.append(contract.getSymbol());
        builder.append(contract.getSecurityType().getAbbreviation());
        builder.append(contract.getExpiry());
        builder.append(contract.getStrike());
        builder.append(contract.getOptionRight().getName());
        builder.append(contract.getMultiplier());
        builder.append(contract.getExchange());
        builder.append(contract.getPrimaryExchange());
        builder.append(contract.getCurrencyCode());
        builder.append(contract.getLocalSymbol());
        builder.append(contract.isIncludeExpired());
    }

    private void appendComboLegs(final RequestBuilder builder) {
        if (SecurityType.COMBO.equals(contract.getSecurityType())) {
            final List<ComboLeg> comboLegs = contract.getComboLegs();
            builder.append(comboLegs.size());
            for (final ComboLeg comboLeg : comboLegs) {
                builder.append(comboLeg.getContractId());
                builder.append(comboLeg.getRatio());
                builder.append(comboLeg.getOrderAction().getAbbreviation());
                builder.append(comboLeg.getExchange());
            }
        }
    }

    public Contract getContract() {
        return contract;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public TimeSpan getDuration() {
        return duration;
    }

    public BarSize getBarSize() {
        return barSize;
    }

    public HistoricalDataType getHistoricalDataType() {
        return historicalDataType;
    }

    public boolean useRegularTradingHours() {
        return useRegularTradingHours;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(barSize).append(contract).append(dateFormat).append(duration)
                .append(endDateTime).append(historicalDataType).append(useRegularTradingHours).toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        final HistoricalDataSubscriptionRequest rhs = (HistoricalDataSubscriptionRequest) obj;
        return new EqualsBuilder().append(barSize, rhs.barSize).append(contract, rhs.contract)
                .append(dateFormat, rhs.dateFormat).append(duration, rhs.duration).append(endDateTime, rhs.endDateTime)
                .append(historicalDataType, rhs.historicalDataType)
                .append(useRegularTradingHours, rhs.useRegularTradingHours).isEquals();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
