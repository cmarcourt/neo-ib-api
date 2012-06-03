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

/**
 * @author Christophe Marcourt
 * @version 1.0.0
 */
public final class OrderExecution {

	private static final String EMTPY = "";
	private double commission;
	private String commissionCurrencyCode = EMTPY;
	private String equityWithLoan = EMTPY;
	private String initialMargin = EMTPY;
	private String maintenanceMargin = EMTPY;
	private double maxCommission;
	private double minCommission;
	private OrderStatus orderStatus = OrderStatus.EMPTY;
	private String warningText = EMTPY;

	public final double getCommission() {
		return commission;
	}

	public final void setCommission(final double commission) {
		this.commission = commission;
	}

	public final String getCommissionCurrencyCode() {
		return commissionCurrencyCode;
	}

	public final void setCommissionCurrencyCode(final String commissionCurrencyCode) {
		this.commissionCurrencyCode = commissionCurrencyCode;
	}

	public final String getEquityWithLoan() {
		return equityWithLoan;
	}

	public final void setEquityWithLoan(final String equityWithLoan) {
		this.equityWithLoan = equityWithLoan;
	}

	public final String getInitialMargin() {
		return initialMargin;
	}

	public final void setInitialMargin(final String initialMargin) {
		this.initialMargin = initialMargin;
	}

	public final String getMaintenanceMargin() {
		return maintenanceMargin;
	}

	public final void setMaintenanceMargin(final String maintenanceMargin) {
		this.maintenanceMargin = maintenanceMargin;
	}

	public final double getMaxCommission() {
		return maxCommission;
	}

	public final void setMaxCommission(final double maxCommission) {
		this.maxCommission = maxCommission;
	}

	public final double getMinCommission() {
		return minCommission;
	}

	public final void setMinCommission(final double minCommission) {
		this.minCommission = minCommission;
	}

	public final OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public final void setOrderStatus(final OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public final String getWarningText() {
		return warningText;
	}

	public final void setWarningText(final String warningText) {
		this.warningText = warningText;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(commission).append(commissionCurrencyCode).append(equityWithLoan)
				.append(initialMargin).append(maintenanceMargin).append(maxCommission).append(minCommission)
				.append(orderStatus).append(warningText).toHashCode();
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
		final OrderExecution rhs = (OrderExecution) obj;
		return new EqualsBuilder().append(commission, rhs.commission)
				.append(commissionCurrencyCode, rhs.commissionCurrencyCode).append(equityWithLoan, rhs.equityWithLoan)
				.append(initialMargin, rhs.initialMargin).append(maintenanceMargin, rhs.maintenanceMargin)
				.append(maxCommission, rhs.maxCommission).append(minCommission, rhs.minCommission)
				.append(orderStatus, rhs.orderStatus).append(warningText, rhs.warningText).isEquals();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
