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

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ch.aonyx.broker.ib.api.AbstractEventSupport;
import ch.aonyx.broker.ib.api.contract.Contract;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class PortfolioUpdateEvent extends AbstractEventSupport {

	private final Contract contract;
	private final int marketPosition;
	private final double marketPrice;
	private final double marketValue;
	private final double averageCost;
	private final double unrealizedProfitAndLoss;
	private final double realizedProfitAndLoss;
	private final String accountName;

	public PortfolioUpdateEvent(final Contract contract, final int marketPosition, final double marketPrice,
			final double marketValue, final double averageCost, final double unrealizedProfitAndLoss,
			final double realizedProfitAndLoss, final String accountName) {
		this.contract = contract;
		this.marketPosition = marketPosition;
		this.marketPrice = marketPrice;
		this.marketValue = marketValue;
		this.averageCost = averageCost;
		this.unrealizedProfitAndLoss = unrealizedProfitAndLoss;
		this.realizedProfitAndLoss = realizedProfitAndLoss;
		this.accountName = accountName;
	}

	public final Contract getContract() {
		return contract;
	}

	public final int getMarketPosition() {
		return marketPosition;
	}

	public final double getMarketPrice() {
		return marketPrice;
	}

	public final double getMarketValue() {
		return marketValue;
	}

	public final double getAverageCost() {
		return averageCost;
	}

	public final double getUnrealizedProfitAndLoss() {
		return unrealizedProfitAndLoss;
	}

	public final double getRealizedProfitAndLoss() {
		return realizedProfitAndLoss;
	}

	public final String getAccountName() {
		return accountName;
	}

	@Override
	public Class<?> getAssignableListenerType() {
		return PortfolioUpdateEventListener.class;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
