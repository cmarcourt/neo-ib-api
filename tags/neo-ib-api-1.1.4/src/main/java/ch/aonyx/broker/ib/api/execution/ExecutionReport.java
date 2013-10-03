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
package ch.aonyx.broker.ib.api.execution;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class ExecutionReport {

    private static final String EMPTY = "";
    private String accountNumber = EMPTY;
    private double averageFilledPrice;
    private int clientId;
    private int cumulativeQuantity;
    private String exchange = EMPTY;
    private String executionId = EMPTY;
    private int liquidation;
    private int orderId;
    private String orderRef = EMPTY;
    private int permanentId;
    private double filledPrice;
    private int filledQuantity;
    private Side side = Side.EMPTY;
    private String time = EMPTY;
    private String economicValueRule = EMPTY;
    private double economicValueMultiplier;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(final String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAverageFilledPrice() {
        return averageFilledPrice;
    }

    public void setAverageFilledPrice(final double averageFilledPrice) {
        this.averageFilledPrice = averageFilledPrice;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(final int clientId) {
        this.clientId = clientId;
    }

    public int getCumulativeQuantity() {
        return cumulativeQuantity;
    }

    public void setCumulativeQuantity(final int cumulativeQuantity) {
        this.cumulativeQuantity = cumulativeQuantity;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(final String exchange) {
        this.exchange = exchange;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(final String executionId) {
        this.executionId = executionId;
    }

    public int getLiquidation() {
        return liquidation;
    }

    public void setLiquidation(final int liquidation) {
        this.liquidation = liquidation;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(final int orderId) {
        this.orderId = orderId;
    }

    public String getOrderRef() {
        return orderRef;
    }

    public void setOrderRef(final String orderRef) {
        this.orderRef = orderRef;
    }

    public int getPermanentId() {
        return permanentId;
    }

    public void setPermanentId(final int permanentId) {
        this.permanentId = permanentId;
    }

    public double getFilledPrice() {
        return filledPrice;
    }

    public void setFilledPrice(final double filledPrice) {
        this.filledPrice = filledPrice;
    }

    public int getFilledQuantity() {
        return filledQuantity;
    }

    public void setFilledQuantity(final int filledQuantity) {
        this.filledQuantity = filledQuantity;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(final Side side) {
        this.side = side;
    }

    public String getTime() {
        return time;
    }

    public void setTime(final String time) {
        this.time = time;
    }

    public String getEconomicValueRule() {
        return economicValueRule;
    }

    public void setEconomicValueRule(final String economicValueRule) {
        this.economicValueRule = economicValueRule;
    }

    public double getEconomicValueMultiplier() {
        return economicValueMultiplier;
    }

    public void setEconomicValueMultiplier(final double economicValueMultiplier) {
        this.economicValueMultiplier = economicValueMultiplier;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(accountNumber).append(averageFilledPrice).append(clientId)
                .append(cumulativeQuantity).append(economicValueMultiplier).append(economicValueRule).append(exchange)
                .append(executionId).append(filledPrice).append(filledQuantity).append(liquidation).append(orderId)
                .append(orderRef).append(permanentId).append(side).append(time).toHashCode();
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
        final ExecutionReport rhs = (ExecutionReport) obj;
        return new EqualsBuilder().append(accountNumber, rhs.accountNumber)
                .append(averageFilledPrice, rhs.averageFilledPrice).append(clientId, rhs.clientId)
                .append(cumulativeQuantity, rhs.cumulativeQuantity)
                .append(economicValueMultiplier, rhs.economicValueMultiplier)
                .append(economicValueRule, rhs.economicValueRule).append(exchange, rhs.exchange)
                .append(executionId, rhs.executionId).append(filledPrice, rhs.filledPrice)
                .append(filledQuantity, rhs.filledQuantity).append(liquidation, rhs.liquidation)
                .append(orderId, rhs.orderId).append(orderRef, rhs.orderRef).append(permanentId, rhs.permanentId)
                .append(side, rhs.side).append(time, rhs.time).isEquals();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
