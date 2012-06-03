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
package ch.aonyx.broker.ib.api.bulletin;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ch.aonyx.broker.ib.api.AbstractRequestSupport;
import ch.aonyx.broker.ib.api.OutgoingMessageId;
import ch.aonyx.broker.ib.api.SubscriptionRequest;
import ch.aonyx.broker.ib.api.util.ByteArrayRequestBuilder;
import ch.aonyx.broker.ib.api.util.RequestBuilder;
import ch.aonyx.broker.ib.api.util.StringIdUtils;

/**
 * @author Christophe Marcourt
 * @version 1.0.0
 */
public final class NewsBulletinSubscriptionRequest extends AbstractRequestSupport implements SubscriptionRequest {

	private static final int VERSION = 1;
	private final boolean includeExistingDailyNews;

	protected NewsBulletinSubscriptionRequest(final boolean includeExistingDailyNews) {
		super(StringIdUtils.uniqueRandomId());
		this.includeExistingDailyNews = includeExistingDailyNews;
	}

	public final boolean isIncludeExistingDailyNews() {
		return includeExistingDailyNews;
	}

	@Override
	public byte[] getBytes() {
		final RequestBuilder builder = createRequestBuilder();
		return builder.toBytes();
	}

	private RequestBuilder createRequestBuilder() {
		final RequestBuilder builder = new ByteArrayRequestBuilder();
		builder.append(OutgoingMessageId.NEWS_BULLETIN_SUBSCRIPTION_REQUEST.getId());
		builder.append(VERSION);
		builder.append(includeExistingDailyNews);
		return builder;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(includeExistingDailyNews).toHashCode();
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
		final NewsBulletinSubscriptionRequest rhs = (NewsBulletinSubscriptionRequest) obj;
		return new EqualsBuilder().append(includeExistingDailyNews, rhs.includeExistingDailyNews).isEquals();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
