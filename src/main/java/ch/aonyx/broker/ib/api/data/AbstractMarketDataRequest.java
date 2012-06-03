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

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ch.aonyx.broker.ib.api.AbstractRequestSupport;
import ch.aonyx.broker.ib.api.ClientMessageCode;
import ch.aonyx.broker.ib.api.Feature;
import ch.aonyx.broker.ib.api.OutgoingMessageId;
import ch.aonyx.broker.ib.api.RequestException;
import ch.aonyx.broker.ib.api.contract.Contract;
import ch.aonyx.broker.ib.api.contract.SecurityType;
import ch.aonyx.broker.ib.api.contract.UnderlyingCombo;
import ch.aonyx.broker.ib.api.order.ComboLeg;
import ch.aonyx.broker.ib.api.util.ByteArrayRequestBuilder;
import ch.aonyx.broker.ib.api.util.RequestBuilder;
import ch.aonyx.broker.ib.api.util.StringIdUtils;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * @author Christophe Marcourt
 * @version 1.0.0
 */
public abstract class AbstractMarketDataRequest extends AbstractRequestSupport {

	private static final int VERSION = 9;
	private final Contract contract;
	private final boolean snapshot;
	private final ReturnedTickTypeFilter[] returnedTickTypeFilters;
	private final Function<ReturnedTickTypeFilter, String> returnedTickTypeFilterToStringFunction = new Function<ReturnedTickTypeFilter, String>() {
		@Override
		public String apply(final ReturnedTickTypeFilter input) {
			return String.valueOf(input.getId());
		}
	};

	protected AbstractMarketDataRequest(final Contract contract, final boolean snapshot,
			final ReturnedTickTypeFilter... returnedTickTypeFilters) {
		this(StringIdUtils.uniqueIdFromContract(contract), contract, snapshot, returnedTickTypeFilters);
	}

	protected AbstractMarketDataRequest(final String id, final Contract contract, final boolean snapshot,
			final ReturnedTickTypeFilter... returnedTickTypeFilters) {
		super(id);
		this.contract = contract;
		this.snapshot = snapshot;
		this.returnedTickTypeFilters = returnedTickTypeFilters;
	}

	@Override
	public byte[] getBytes() {
		final RequestBuilder builder = createRequestBuilder();
		return builder.toBytes();
	}

	private RequestBuilder createRequestBuilder() {
		final RequestBuilder builder = new ByteArrayRequestBuilder();
		checkDeltaNeutralOrderSupport();
		checkContractIdSupport();
		builder.append(OutgoingMessageId.MARKET_DATA_SUBSCRIPTION_REQUEST.getId());
		builder.append(VERSION);
		builder.append(toInternalId(getId()));
		appendContract(builder);
		appendCombo(builder);
		appendUnderlyingCombo(builder);
		appendReturnedTickTypeFilters(builder);
		builder.append(snapshot);
		return builder;
	}

	private void checkDeltaNeutralOrderSupport() {
		if (contract.getUnderlyingCombo() != null) {
			if (!Feature.DELTA_NEUTRAL_COMBO_ORDER.isSupportedByVersion(getServerCurrentVersion())) {
				throw new RequestException(ClientMessageCode.UPDATE_TWS, "It does not support delta-neutral orders.",
						this);
			}
		}
	}

	private void checkContractIdSupport() {
		if (contract.getId() > 0) {
			if (!Feature.MARKET_DATA_REQUEST_BY_CONTRACT_ID.isSupportedByVersion(getServerCurrentVersion())) {
				throw new RequestException(ClientMessageCode.UPDATE_TWS,
						"Market data request by contract id is not supported.", this);
			}
		}
	}

	private void appendContract(final RequestBuilder builder) {
		if (Feature.MARKET_DATA_REQUEST_BY_CONTRACT_ID.isSupportedByVersion(getServerCurrentVersion())) {
			builder.append(contract.getId());
		}
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

	private void appendCombo(final RequestBuilder builder) {
		if (SecurityType.COMBO.equals(contract.getSecurityType())) {
			builder.append(contract.getComboLegs().size());
			for (final ComboLeg comboLeg : contract.getComboLegs()) {
				builder.append(comboLeg.getContractId());
				builder.append(comboLeg.getRatio());
				builder.append(comboLeg.getOrderAction().getAbbreviation());
				builder.append(comboLeg.getExchange());
			}
		}
	}

	private void appendUnderlyingCombo(final RequestBuilder builder) {
		if (Feature.DELTA_NEUTRAL_COMBO_ORDER.isSupportedByVersion(getServerCurrentVersion())) {
			final UnderlyingCombo underComp = contract.getUnderlyingCombo();
			if (underComp != null) {
				builder.append(true);
				builder.append(underComp.getContractId());
				builder.append(underComp.getDelta());
				builder.append(underComp.getPrice());
			} else {
				builder.append(false);
			}
		}
	}

	private void appendReturnedTickTypeFilters(final RequestBuilder builder) {
		String returnedTickTypeFilterCommaSeparatedList = null;
		if ((returnedTickTypeFilters != null) && (returnedTickTypeFilters.length > 0)) {
			final List<String> returnedTickTypeFilterValues = Lists.transform(
					Lists.newArrayList(returnedTickTypeFilters), returnedTickTypeFilterToStringFunction);
			returnedTickTypeFilterCommaSeparatedList = StringUtils.join(returnedTickTypeFilterValues, ',');
		}
		builder.append(returnedTickTypeFilterCommaSeparatedList);
	}

	public final Contract getContract() {
		return contract;
	}

	public final ReturnedTickTypeFilter[] getReturnedTickTypeFilters() {
		return returnedTickTypeFilters;
	}

	public final boolean isSnapshot() {
		return snapshot;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(contract).append(returnedTickTypeFilters).append(snapshot).toHashCode();
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
		final AbstractMarketDataRequest rhs = (AbstractMarketDataRequest) obj;
		return new EqualsBuilder().append(contract, rhs.contract)
				.append(returnedTickTypeFilters, rhs.returnedTickTypeFilters).append(snapshot, rhs.snapshot).isEquals();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}