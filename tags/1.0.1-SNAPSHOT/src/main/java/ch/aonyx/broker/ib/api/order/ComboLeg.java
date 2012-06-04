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
 * @since 1.0.0
 */
public final class ComboLeg {

	private static final String EMPTY = "";
	private OrderAction orderAction;
	private int contractId;
	private String designatedLocation = EMPTY;
	private String exchange = EMPTY;
	private int exemptionCode = -1;
	private OpenCloseComboLeg openClose = OpenCloseComboLeg.SAME;
	private int ratio;
	private ShortSaleSlotInstitutional shortSaleSlotValue = ShortSaleSlotInstitutional.INAPPLICABLE;

	public final OrderAction getOrderAction() {
		return orderAction;
	}

	public final void setOrderAction(final OrderAction action) {
		orderAction = action;
	}

	public final int getContractId() {
		return contractId;
	}

	public final void setContractId(final int contractId) {
		this.contractId = contractId;
	}

	public final String getDesignatedLocation() {
		return designatedLocation;
	}

	public final void setDesignatedLocation(final String designatedLocation) {
		this.designatedLocation = designatedLocation;
	}

	public final String getExchange() {
		return exchange;
	}

	public final void setExchange(final String exchange) {
		this.exchange = exchange;
	}

	public final int getExemptionCode() {
		return exemptionCode;
	}

	public final void setExemptionCode(final int exemptionCode) {
		this.exemptionCode = exemptionCode;
	}

	public final OpenCloseComboLeg getOpenClose() {
		return openClose;
	}

	public final void setOpenClose(final OpenCloseComboLeg openClose) {
		this.openClose = openClose;
	}

	public final int getRatio() {
		return ratio;
	}

	public final void setRatio(final int ratio) {
		this.ratio = ratio;
	}

	public final ShortSaleSlotInstitutional getShortSaleSlotValue() {
		return shortSaleSlotValue;
	}

	public final void setShortSaleSlotValue(final ShortSaleSlotInstitutional shortSaleSlotValue) {
		this.shortSaleSlotValue = shortSaleSlotValue;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(orderAction).append(contractId).append(designatedLocation)
				.append(exemptionCode).append(exchange).append(openClose).append(ratio).append(shortSaleSlotValue)
				.toHashCode();
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
		final ComboLeg rhs = (ComboLeg) obj;
		return new EqualsBuilder().append(orderAction, rhs.orderAction).append(contractId, rhs.contractId)
				.append(designatedLocation, rhs.designatedLocation).append(exemptionCode, rhs.exemptionCode)
				.append(exchange, rhs.exchange).append(openClose, rhs.openClose).append(ratio, rhs.ratio)
				.append(shortSaleSlotValue, rhs.shortSaleSlotValue).isEquals();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
