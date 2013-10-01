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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ch.aonyx.broker.ib.api.AbstractRequestSupport;
import ch.aonyx.broker.ib.api.OutgoingMessageId;
import ch.aonyx.broker.ib.api.SubscriptionRequest;
import ch.aonyx.broker.ib.api.contract.Contract;
import ch.aonyx.broker.ib.api.util.ByteArrayRequestBuilder;
import ch.aonyx.broker.ib.api.util.RequestBuilder;
import ch.aonyx.broker.ib.api.util.StringIdUtils;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class RealTimeBarSubscriptionRequest extends AbstractRequestSupport implements SubscriptionRequest {

    private static final int VERSION = 1;
    private final Contract contract;
    private final int size;
    private final RealTimeBarDataType realTimeBarDataType;
    private final boolean useRegularTradingHours;

    public RealTimeBarSubscriptionRequest(final Contract contract, final int size,
            final RealTimeBarDataType realTimeBarDataType, final boolean regularTradingHours) {
        this(StringIdUtils.uniqueIdFromContract(contract), contract, size, realTimeBarDataType, regularTradingHours);
    }

    public RealTimeBarSubscriptionRequest(final String id, final Contract contract, final int size,
            final RealTimeBarDataType realTimeBarDataType, final boolean regularTradingHours) {
        super(id);
        this.contract = contract;
        this.size = size;
        this.realTimeBarDataType = realTimeBarDataType;
        useRegularTradingHours = regularTradingHours;
    }

    @Override
    public byte[] getBytes() {
        final RequestBuilder builder = createRequestBuilder();
        return builder.toBytes();
    }

    private RequestBuilder createRequestBuilder() {
        final RequestBuilder builder = new ByteArrayRequestBuilder();
        builder.append(OutgoingMessageId.REAL_TIME_BAR_SUBSCRIPTION_REQUEST.getId());
        builder.append(VERSION);
        builder.append(toInternalId(getId()));
        appendContract(builder);
        builder.append(size);
        builder.append(realTimeBarDataType.getLabel());
        builder.append(useRegularTradingHours);
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
    }

    public Contract getContract() {
        return contract;
    }

    public int getSize() {
        return size;
    }

    public RealTimeBarDataType getRealTimeBarDataType() {
        return realTimeBarDataType;
    }

    public boolean useRegularTradingHours() {
        return useRegularTradingHours;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(contract).append(realTimeBarDataType).append(useRegularTradingHours)
                .append(size).toHashCode();
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
        final RealTimeBarSubscriptionRequest rhs = (RealTimeBarSubscriptionRequest) obj;
        return new EqualsBuilder().append(contract, rhs.contract).append(realTimeBarDataType, rhs.realTimeBarDataType)
                .append(useRegularTradingHours, rhs.useRegularTradingHours).append(size, rhs.size).isEquals();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
