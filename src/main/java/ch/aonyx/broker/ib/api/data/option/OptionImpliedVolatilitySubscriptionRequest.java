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
public final class OptionImpliedVolatilitySubscriptionRequest extends AbstractRequestSupport implements
		SubscriptionRequest {

	private static final int VERSION = 1;
	private final Contract contract;
	private final double optionPrice;
	private final double underlyingPrice;

	public OptionImpliedVolatilitySubscriptionRequest(final Contract contract, final double optionPrice,
			final double underlyingPrice) {
		this(StringIdUtils.uniqueIdFromContract(contract), contract, optionPrice, underlyingPrice);
	}

	public OptionImpliedVolatilitySubscriptionRequest(final String id, final Contract contract,
			final double optionPrice, final double underlyingPrice) {
		super(id);
		this.contract = contract;
		this.optionPrice = optionPrice;
		this.underlyingPrice = underlyingPrice;
	}

	@Override
	public byte[] getBytes() {
		final RequestBuilder builder = createRequestBuilder();
		return builder.toBytes();
	}

	private RequestBuilder createRequestBuilder() {
		final RequestBuilder builder = new ByteArrayRequestBuilder();
		checkCalculateImpliedVolatility();
		builder.append(OutgoingMessageId.OPTION_IMPLIED_VOLATILITY_SUBSCRIPTION_REQUEST.getId());
		builder.append(VERSION);
		builder.append(toInternalId(getId()));
		appendContract(builder);
		builder.append(optionPrice);
		builder.append(underlyingPrice);
		return builder;
	}

	private void checkCalculateImpliedVolatility() {
		if (!Feature.CALCULATE_IMPLIED_VOLATILITY.isSupportedByVersion(getServerCurrentVersion())) {
			throw new RequestException(ClientMessageCode.UPDATE_TWS,
					"It does not support calculate implied volatility requests.", this);
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

	public final double getOptionPrice() {
		return optionPrice;
	}

	public final double getUnderlyingPrice() {
		return underlyingPrice;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(contract).append(optionPrice).append(underlyingPrice).toHashCode();
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
		final OptionImpliedVolatilitySubscriptionRequest rhs = (OptionImpliedVolatilitySubscriptionRequest) obj;
		return new EqualsBuilder().append(contract, rhs.contract).append(optionPrice, rhs.optionPrice)
				.append(underlyingPrice, rhs.underlyingPrice).isEquals();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
