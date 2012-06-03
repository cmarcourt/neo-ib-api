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
package ch.aonyx.broker.ib.api.data.option;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ch.aonyx.broker.ib.api.AbstractRequestSupport;
import ch.aonyx.broker.ib.api.ClientMessageCode;
import ch.aonyx.broker.ib.api.Feature;
import ch.aonyx.broker.ib.api.OutgoingMessageId;
import ch.aonyx.broker.ib.api.RequestException;
import ch.aonyx.broker.ib.api.SubscriptionRequest;
import ch.aonyx.broker.ib.api.contract.Contract;
import ch.aonyx.broker.ib.api.util.ByteArrayRequestBuilder;
import ch.aonyx.broker.ib.api.util.RequestBuilder;
import ch.aonyx.broker.ib.api.util.StringIdUtils;

/**
 * @author Christophe Marcourt
 * @version 1.0.0
 */
public final class OptionPriceSubscriptionRequest extends AbstractRequestSupport implements SubscriptionRequest {

	private static final int VERSION = 1;
	private final Contract contract;
	private final double volatility;
	private final double underlyingPrice;

	public OptionPriceSubscriptionRequest(final Contract contract, final double volatility, final double underlyingPrice) {
		this(StringIdUtils.uniqueIdFromContract(contract), contract, volatility, underlyingPrice);
	}

	public OptionPriceSubscriptionRequest(final String id, final Contract contract, final double volatility,
			final double underlyingPrice) {
		super(id);
		this.contract = contract;
		this.volatility = volatility;
		this.underlyingPrice = underlyingPrice;
	}

	@Override
	public byte[] getBytes() {
		final RequestBuilder builder = createRequestBuilder();
		return builder.toBytes();
	}

	private RequestBuilder createRequestBuilder() {
		final RequestBuilder builder = new ByteArrayRequestBuilder();
		checkCalculateOptionPrice();
		builder.append(OutgoingMessageId.OPTION_PRICE_SUBSCRIPTION_REQUEST.getId());
		builder.append(VERSION);
		builder.append(toInternalId(getId()));
		appendContract(builder);
		builder.append(volatility);
		builder.append(underlyingPrice);
		return builder;
	}

	private void checkCalculateOptionPrice() {
		if (!Feature.CALCULATE_OPTION_PRICE.isSupportedByVersion(getServerCurrentVersion())) {
			throw new RequestException(ClientMessageCode.UPDATE_TWS,
					"It does not support calculate option price requests.", this);
		}
	}

	private void appendContract(final RequestBuilder builder) {
		builder.append(contract.getId());
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

	public final Contract getContract() {
		return contract;
	}

	public final double getVolatility() {
		return volatility;
	}

	public final double getUnderlyingPrice() {
		return underlyingPrice;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(contract).append(volatility).append(underlyingPrice).toHashCode();
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
		final OptionPriceSubscriptionRequest rhs = (OptionPriceSubscriptionRequest) obj;
		return new EqualsBuilder().append(contract, rhs.contract).append(volatility, rhs.volatility)
				.append(underlyingPrice, rhs.underlyingPrice).isEquals();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
