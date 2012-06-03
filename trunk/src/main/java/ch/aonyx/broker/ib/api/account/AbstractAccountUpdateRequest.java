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
package ch.aonyx.broker.ib.api.account;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ch.aonyx.broker.ib.api.AbstractRequestSupport;
import ch.aonyx.broker.ib.api.OutgoingMessageId;
import ch.aonyx.broker.ib.api.util.ByteArrayRequestBuilder;
import ch.aonyx.broker.ib.api.util.RequestBuilder;
import ch.aonyx.broker.ib.api.util.StringIdUtils;

/**
 * @author Christophe Marcourt
 * @version 1.0.0
 */
public abstract class AbstractAccountUpdateRequest extends AbstractRequestSupport {

	private static final int VERSION = 2;
	private final String accountName;
	private final boolean subscribe;

	protected AbstractAccountUpdateRequest(final String accountName, final boolean subscribe) {
		super(StringIdUtils.uniqueRandomId());
		this.accountName = accountName;
		this.subscribe = subscribe;
	}

	@Override
	public byte[] getBytes() {
		final RequestBuilder builder = createRequestBuilder();
		return builder.toBytes();
	}

	private RequestBuilder createRequestBuilder() {
		final RequestBuilder builder = new ByteArrayRequestBuilder();
		builder.append(OutgoingMessageId.ACCOUNT_UPDATE_SUBSCRIPTION_REQUEST.getId());
		builder.append(VERSION);
		builder.append(subscribe);
		builder.append(accountName);
		return builder;
	}

	public final String getAccountName() {
		return accountName;
	}

	public final boolean isSubscribe() {
		return subscribe;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(accountName).append(subscribe).toHashCode();
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
		final AbstractAccountUpdateRequest rhs = (AbstractAccountUpdateRequest) obj;
		return new EqualsBuilder().append(accountName, rhs.accountName).append(subscribe, rhs.subscribe).isEquals();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
