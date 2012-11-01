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

import java.text.ParseException;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;

import ch.aonyx.broker.ib.api.contract.SecurityType;
import ch.aonyx.broker.ib.api.order.OrderAction;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class ExecutionReportFilter {

    private static final String EMPTY = "";
    private String accountNumber = EMPTY;
    private int clientId;
    private String exchange = EMPTY;
    private SecurityType securityType = SecurityType.EMPTY;
    private OrderAction orderAction = OrderAction.EMPTY;
    private String symbol = EMPTY;
    private String time = EMPTY;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(final String accountCode) {
        accountNumber = accountCode;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(final int clientId) {
        this.clientId = clientId;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(final String exchange) {
        this.exchange = exchange;
    }

    public SecurityType getSecurityType() {
        return securityType;
    }

    public void setSecurityType(final SecurityType securityType) {
        this.securityType = securityType;
    }

    public OrderAction getOrderAction() {
        return orderAction;
    }

    public void setOrderAction(final OrderAction orderAction) {
        this.orderAction = orderAction;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(final String symbol) {
        this.symbol = symbol;
    }

    public String getTime() {
        return time;
    }

    public DateTime getDateTime() {
        try {
            return new DateTime(DateUtils.parseDate(time, "yyyyMMdd-HH:mm:ss"));
        } catch (final ParseException e) {
        }
        return null;
    }

    public void setTime(final String time) {
        this.time = time;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(accountNumber).append(clientId).append(exchange).append(securityType)
                .append(orderAction).append(symbol).append(time).toHashCode();
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
        final ExecutionReportFilter rhs = (ExecutionReportFilter) obj;
        return new EqualsBuilder().append(accountNumber, rhs.accountNumber).append(clientId, rhs.clientId)
                .append(exchange, rhs.exchange).append(securityType, rhs.securityType)
                .append(orderAction, rhs.orderAction).append(symbol, rhs.symbol).append(time, rhs.time).isEquals();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
