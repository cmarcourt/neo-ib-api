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
package ch.aonyx.broker.ib.api.data.scanner;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ch.aonyx.broker.ib.api.AbstractRequestSupport;
import ch.aonyx.broker.ib.api.OutgoingMessageId;
import ch.aonyx.broker.ib.api.SubscriptionRequest;
import ch.aonyx.broker.ib.api.util.ByteArrayRequestBuilder;
import ch.aonyx.broker.ib.api.util.RequestBuilder;

/**
 * @author Christophe Marcourt
 * @version 1.0.0
 */
public final class MarketScannerSubscriptionRequest extends AbstractRequestSupport implements SubscriptionRequest {

	private static final int VERSION = 3;
	private final MarketScannerFilter marketScannerFilter;

	public MarketScannerSubscriptionRequest(final String id, final MarketScannerFilter filter) {
		super(id);
		marketScannerFilter = filter;
	}

	public MarketScannerFilter getMarketScannerFilter() {
		return marketScannerFilter;
	}

	@Override
	public byte[] getBytes() {
		final RequestBuilder builder = createRequestBuilder();
		return builder.toBytes();
	}

	private RequestBuilder createRequestBuilder() {
		final RequestBuilder builder = new ByteArrayRequestBuilder();
		builder.append(OutgoingMessageId.MARKET_SCANNER_SUBSCRIPTION_REQUEST.getId());
		builder.append(VERSION);
		builder.append(toInternalId(getId()));
		appendMarketScannerFilter(builder);
		return builder;
	}

	private void appendMarketScannerFilter(final RequestBuilder builder) {
		builder.append(marketScannerFilter.getNumberOfRows());
		builder.append(marketScannerFilter.getInstrument());
		builder.append(marketScannerFilter.getLocationCode());
		builder.append(marketScannerFilter.getScannerCode());
		builder.append(marketScannerFilter.getAbovePrice());
		builder.append(marketScannerFilter.getBelowPrice());
		builder.append(marketScannerFilter.getAboveVolume());
		builder.append(marketScannerFilter.getAboveMarketCapitalization());
		builder.append(marketScannerFilter.getBelowMarketCapitalization());
		builder.append(marketScannerFilter.getAboveMoodyRating());
		builder.append(marketScannerFilter.getBelowMoodyRating());
		builder.append(marketScannerFilter.getAboveSpRating());
		builder.append(marketScannerFilter.getBelowSpRating());
		builder.append(marketScannerFilter.getAboveMaturityDate());
		builder.append(marketScannerFilter.getBelowMaturityDate());
		builder.append(marketScannerFilter.getAboveCouponRate());
		builder.append(marketScannerFilter.getBelowCouponRate());
		builder.append(marketScannerFilter.getExcludeConvertible());
		builder.append(marketScannerFilter.getAboveAverageVolumeOption());
		builder.append(marketScannerFilter.getScannerSettingPairs());
		builder.append(marketScannerFilter.getStockType().getLabel());
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(marketScannerFilter).toHashCode();
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
		final MarketScannerSubscriptionRequest rhs = (MarketScannerSubscriptionRequest) obj;
		return new EqualsBuilder().append(marketScannerFilter, rhs.marketScannerFilter).isEquals();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
