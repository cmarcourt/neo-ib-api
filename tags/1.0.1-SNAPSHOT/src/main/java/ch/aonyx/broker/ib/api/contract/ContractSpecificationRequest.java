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
package ch.aonyx.broker.ib.api.contract;

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
import ch.aonyx.broker.ib.api.SimpleRequest;
import ch.aonyx.broker.ib.api.util.ByteArrayRequestBuilder;
import ch.aonyx.broker.ib.api.util.RequestBuilder;
import ch.aonyx.broker.ib.api.util.StringIdUtils;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class ContractSpecificationRequest extends AbstractRequestSupport implements SimpleRequest {

	private static final int VERSION = 6;
	private final Contract contract;

	public ContractSpecificationRequest(final Contract contract) {
		this(StringIdUtils.uniqueIdFromContract(contract), contract);
	}

	public ContractSpecificationRequest(final String id, final Contract contract) {
		super(id);
		this.contract = contract;
	}

	public final Contract getContract() {
		return contract;
	}

	@Override
	public byte[] getBytes() {
		final RequestBuilder builder = createRequestBuilder();
		return builder.toBytes();
	}

	private RequestBuilder createRequestBuilder() {
		final RequestBuilder builder = new ByteArrayRequestBuilder();
		checkSecurityIdType();
		builder.append(OutgoingMessageId.CONTRACT_SPECIFICATION_REQUEST.getId());
		builder.append(VERSION);
		if (Feature.CONTRACT_SPECIFICATION_MARKER.isSupportedByVersion(getServerCurrentVersion())) {
			builder.append(toInternalId(getId()));
		}
		appendContract(builder);
		return builder;
	}

	private void checkSecurityIdType() {
		if (StringUtils.isNotEmpty(contract.getSecurityId()) || (contract.getSecurityIdentifierCode() != null)) {
			if (!Feature.SECURITY_ID_TYPE.isSupportedByVersion(getServerCurrentVersion())) {
				throw new RequestException(ClientMessageCode.UPDATE_TWS,
						"It does not support secIdType and secId parameters.", this);
			}
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
		builder.append(contract.getCurrencyCode());
		builder.append(contract.getLocalSymbol());
		builder.append(contract.isIncludeExpired());
		if (Feature.SECURITY_ID_TYPE.isSupportedByVersion(getServerCurrentVersion())) {
			builder.append(contract.getSecurityIdentifierCode().getAcronym());
			builder.append(contract.getSecurityId());
		}
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(contract).toHashCode();
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
		final ContractSpecificationRequest rhs = (ContractSpecificationRequest) obj;
		return new EqualsBuilder().append(contract, rhs.contract).isEquals();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
