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

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ch.aonyx.broker.ib.api.AbstractEventSupport;
import ch.aonyx.broker.ib.api.Id;

/**
 * @author Christophe Marcourt
 * @version 1.0.0
 */
public final class OrderStateUpdateEvent extends AbstractEventSupport {

	private final OrderStatus orderStatus;
	private final int filledQuantity;
	private final int remainingQuantity;
	private final double averageFilledPrice;
	private final int permanentId;
	private final Id parentOrderId;
	private final double lastFilledPrice;
	private final int clientId;
	private final String heldCause;

	public OrderStateUpdateEvent(final Id orderId, final OrderStatus orderStatus, final int filledQuantity,
			final int remainingQuantity, final double averageFilledPrice, final int permanentId,
			final Id parentOrderId, final double lastFilledPrice, final int clientId, final String heldCause) {
		super(orderId);
		this.orderStatus = orderStatus;
		this.filledQuantity = filledQuantity;
		this.remainingQuantity = remainingQuantity;
		this.averageFilledPrice = averageFilledPrice;
		this.permanentId = permanentId;
		this.parentOrderId = parentOrderId;
		this.lastFilledPrice = lastFilledPrice;
		this.clientId = clientId;
		this.heldCause = heldCause;
	}

	public final OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public final int getFilledQuantity() {
		return filledQuantity;
	}

	public final int getRemainingQuantity() {
		return remainingQuantity;
	}

	public final double getAverageFilledPrice() {
		return averageFilledPrice;
	}

	public final int getPermanentId() {
		return permanentId;
	}

	public final Id getParentOrderId() {
		return parentOrderId;
	}

	public final double getLastFilledPrice() {
		return lastFilledPrice;
	}

	public final int getClientId() {
		return clientId;
	}

	public final String getHeldCause() {
		return heldCause;
	}

	@Override
	public Class<?> getAssignableListenerType() {
		return OrderStateUpdateEventListener.class;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
