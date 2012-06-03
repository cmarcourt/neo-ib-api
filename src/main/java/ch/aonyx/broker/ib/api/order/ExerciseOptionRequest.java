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
package ch.aonyx.broker.ib.api.order;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ch.aonyx.broker.ib.api.AbstractRequestSupport;
import ch.aonyx.broker.ib.api.OutgoingMessageId;
import ch.aonyx.broker.ib.api.SimpleRequest;
import ch.aonyx.broker.ib.api.contract.Contract;
import ch.aonyx.broker.ib.api.util.ByteArrayRequestBuilder;
import ch.aonyx.broker.ib.api.util.RequestBuilder;
import ch.aonyx.broker.ib.api.util.StringIdUtils;

/**
 * @author Christophe Marcourt
 * @version 1.0.0
 */
public final class ExerciseOptionRequest extends AbstractRequestSupport implements SimpleRequest {

	private static final int VERSION = 1;
	private final Contract contract;
	private final ExerciseAction action;
	private final int quantity;
	private final String accountName;
	private final boolean override;

	public ExerciseOptionRequest(final Contract contract, final ExerciseAction action, final int quantity,
			final String accountName, final boolean override) {
		this(StringIdUtils.uniqueIdFromContract(contract), contract, action, quantity, accountName, override);
	}

	public ExerciseOptionRequest(final String id, final Contract contract, final ExerciseAction action,
			final int quantity, final String accountName, final boolean override) {
		super(id);
		this.contract = contract;
		this.action = action;
		this.quantity = quantity;
		this.accountName = accountName;
		this.override = override;
	}

	@Override
	public byte[] getBytes() {
		final RequestBuilder builder = createRequestBuilder();
		return builder.toBytes();
	}

	private RequestBuilder createRequestBuilder() {
		final RequestBuilder builder = new ByteArrayRequestBuilder();
		builder.append(OutgoingMessageId.EXERCISE_OPTION_REQUEST.getId());
		builder.append(VERSION);
		builder.append(toInternalId(getId()));
		appendContract(builder);
		builder.append(action.getValue());
		builder.append(quantity);
		builder.append(accountName);
		builder.append(override);
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
		builder.append(contract.getCurrencyCode());
		builder.append(contract.getLocalSymbol());
	}

	public final Contract getContract() {
		return contract;
	}

	public final ExerciseAction getAction() {
		return action;
	}

	public final int getQuantity() {
		return quantity;
	}

	public final String getAccountName() {
		return accountName;
	}

	public final boolean isOverride() {
		return override;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(accountName).append(action).append(contract).append(override)
				.append(quantity).toHashCode();
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
		final ExerciseOptionRequest rhs = (ExerciseOptionRequest) obj;
		return new EqualsBuilder().append(accountName, rhs.accountName).append(action, rhs.action)
				.append(contract, rhs.contract).append(override, rhs.override).append(quantity, rhs.quantity)
				.isEquals();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
