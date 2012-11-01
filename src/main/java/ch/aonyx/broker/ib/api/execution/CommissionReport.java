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
import java.util.Currency;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class CommissionReport {

    private static final String DATE_PATTERN = "yyyyMMdd";
    private double commission;
    private String currencyCode;
    private String executionId;
    private double realizedProfitAndLoss;
    private double yield;
    private int yieldRedemptionDate;

    public double getCommission() {
        return commission;
    }

    public void setCommission(final double commission) {
        this.commission = commission;
    }

    public String getCurrencyCode() {
        return StringUtils.trimToNull(currencyCode);
    }

    public Currency getCurrency() {
        return Currency.getInstance(currencyCode);
    }

    public void setCurrencyCode(final String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void setCurrency(final Currency currency) {
        currencyCode = currency.getCurrencyCode();
    }

    public String getExecutionId() {
        return StringUtils.trimToNull(executionId);
    }

    public void setExecutionId(final String executionId) {
        this.executionId = executionId;
    }

    public double getRealizedProfitAndLoss() {
        return realizedProfitAndLoss;
    }

    public void setRealizedProfitAndLoss(final double realizedProfitAndLoss) {
        this.realizedProfitAndLoss = realizedProfitAndLoss;
    }

    public double getYield() {
        return yield;
    }

    public void setYield(final double yield) {
        this.yield = yield;
    }

    public int getYieldRedemptionDate() {
        return yieldRedemptionDate;
    }

    public DateTime getYieldRedemptionDateTime() {
        try {
            return new DateTime(DateUtils.parseDate(String.valueOf(yieldRedemptionDate), DATE_PATTERN).getTime());
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setYieldRedemptionDate(final int yieldRedemptionDate) {
        this.yieldRedemptionDate = yieldRedemptionDate;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(commission).append(currencyCode).append(executionId)
                .append(realizedProfitAndLoss).append(yield).append(yieldRedemptionDate).toHashCode();
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
        final CommissionReport rhs = (CommissionReport) obj;
        return new EqualsBuilder().append(commission, rhs.commission).append(currencyCode, rhs.currencyCode)
                .append(executionId, rhs.executionId).append(realizedProfitAndLoss, rhs.realizedProfitAndLoss)
                .append(yield, rhs.yield).append(yieldRedemptionDate, rhs.yieldRedemptionDate).isEquals();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
