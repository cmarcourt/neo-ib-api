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

import java.text.ParseException;
import java.util.Collections;
import java.util.Currency;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.time.DateUtils;

import ch.aonyx.broker.ib.api.NeoIbApiClientException;
import ch.aonyx.broker.ib.api.order.ComboLeg;

import com.google.common.collect.Lists;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class Contract {

	private static final String EMPTY = "";
	private int id;
	private final List<ComboLeg> comboLegs = Lists.newArrayList();
	private String comboLegsDescription = EMPTY;
	private String currencyCode = EMPTY;
	private String exchange = EMPTY;
	private String expiry = EMPTY;
	private boolean includeExpired;
	private String localSymbol = EMPTY;
	private String multiplier = EMPTY;
	private String primaryExchange = EMPTY;
	private OptionRight optionRight = OptionRight.EMPTY;
	private SecurityIdentifierCode securityIdentifierCode = SecurityIdentifierCode.EMPTY;
	private String securityId = EMPTY;
	private SecurityType securityType = SecurityType.EMPTY;
	private double strike;
	private String symbol = EMPTY;
	private UnderlyingCombo underlyingCombo;

	public final int getId() {
		return id;
	}

	public final void setId(final int id) {
		this.id = id;
	}

	public final String getComboLegsDescription() {
		return comboLegsDescription;
	}

	public final void setComboLegsDescription(final String comboLegsDescription) {
		this.comboLegsDescription = comboLegsDescription;
	}

	public final String getCurrencyCode() {
		return currencyCode;
	}

	public final Currency getCurrency() {
		return Currency.getInstance(currencyCode);
	}

	public final void setCurrencyCode(final String currency) {
		currencyCode = currency;
	}

	public final void setCurrency(final Currency currency) {
		currencyCode = currency.getCurrencyCode();
	}

	public final String getExchange() {
		return exchange;
	}

	public final void setExchange(final String exchange) {
		this.exchange = exchange;
	}

	public final String getExpiry() {
		return expiry;
	}

	public final Date getExpirationDate() {
		if (StringUtils.isNotEmpty(expiry)) {
			try {
				return DateUtils.parseDate(expiry, "YYYYMM");
			} catch (final ParseException e) {
				throw new NeoIbApiClientException(null, e.getMessage(), e);
			}
		}
		return null;
	}

	public final void setExpiry(final String expiry) {
		this.expiry = expiry;
	}

	public final boolean isIncludeExpired() {
		return includeExpired;
	}

	public final void setIncludeExpired(final boolean includeExpired) {
		this.includeExpired = includeExpired;
	}

	public final String getLocalSymbol() {
		return localSymbol;
	}

	public final void setLocalSymbol(final String localSymbol) {
		this.localSymbol = localSymbol;
	}

	public final String getMultiplier() {
		return multiplier;
	}

	public final void setMultiplier(final String multiplier) {
		this.multiplier = multiplier;
	}

	public final String getPrimaryExchange() {
		return primaryExchange;
	}

	public final void setPrimaryExchange(final String primaryExchange) {
		this.primaryExchange = primaryExchange;
	}

	public final OptionRight getOptionRight() {
		return optionRight;
	}

	public final void setOptionRight(final OptionRight optionRight) {
		this.optionRight = optionRight;
	}

	public final SecurityIdentifierCode getSecurityIdentifierCode() {
		return securityIdentifierCode;
	}

	public final void setSecurityIdentifierCode(final SecurityIdentifierCode securityIdentifierCode) {
		this.securityIdentifierCode = securityIdentifierCode;
	}

	public final String getSecurityId() {
		return securityId;
	}

	public final void setSecurityId(final String securityId) {
		this.securityId = securityId;
	}

	public final SecurityType getSecurityType() {
		return securityType;
	}

	public final void setSecurityType(final SecurityType securityType) {
		this.securityType = securityType;
	}

	public final double getStrike() {
		return strike;
	}

	public final void setStrike(final double strike) {
		this.strike = strike;
	}

	public final String getSymbol() {
		return symbol;
	}

	public final void setSymbol(final String symbol) {
		this.symbol = symbol;
	}

	public final UnderlyingCombo getUnderlyingCombo() {
		return underlyingCombo;
	}

	public final void setUnderlyingCombo(final UnderlyingCombo underlyingCombo) {
		this.underlyingCombo = underlyingCombo;
	}

	public final List<ComboLeg> getComboLegs() {
		return Collections.unmodifiableList(comboLegs);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).append(comboLegsDescription).append(currencyCode).append(exchange)
				.append(expiry).append(includeExpired).append(localSymbol).append(multiplier).append(primaryExchange)
				.append(optionRight).append(securityId).append(securityIdentifierCode).append(securityType)
				.append(strike).append(symbol).append(underlyingCombo).toHashCode();
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
		final Contract rhs = (Contract) obj;
		return new EqualsBuilder().append(id, rhs.id).append(comboLegsDescription, rhs.comboLegs)
				.append(currencyCode, rhs.currencyCode).append(expiry, rhs.expiry)
				.append(includeExpired, rhs.includeExpired).append(localSymbol, rhs.localSymbol)
				.append(multiplier, rhs.multiplier).append(primaryExchange, rhs.primaryExchange)
				.append(optionRight, rhs.optionRight).append(securityId, rhs.securityId)
				.append(securityIdentifierCode, rhs.securityIdentifierCode).append(securityType, rhs.securityType)
				.append(strike, rhs.strike).append(symbol, rhs.symbol).append(underlyingCombo, rhs.underlyingCombo)
				.isEquals();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
