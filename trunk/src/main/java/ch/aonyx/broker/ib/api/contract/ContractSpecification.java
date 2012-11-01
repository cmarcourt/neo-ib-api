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

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ch.aonyx.broker.ib.api.order.OrderType;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class ContractSpecification {

    private static final String EMPTY = "";
    private static final char SEMI_COLON_SEPARATOR = ';';
    private static final char COMMA_SEPARATOR = ',';
    private String bondType = EMPTY;
    private boolean callable;
    private String category = EMPTY;
    private String contractMonth = EMPTY;
    private boolean convertible;
    private double coupon;
    private String couponType = EMPTY;
    private String cusip = EMPTY;
    private String description = EMPTY;
    private String industry = EMPTY;
    private String issueDate = EMPTY;
    private String liquidHours = EMPTY;
    private String longName = EMPTY;
    private String marketName = EMPTY;
    private String maturity = EMPTY;
    private double minimumFluctuation;
    private String nextOptionDate = EMPTY;
    private boolean nextOptionPartial;
    private String nextOptionType = EMPTY;
    private String notes = EMPTY;
    private String validOrderTypes = EMPTY;
    private int priceMagnifier;
    private boolean putable;
    private String ratings = EMPTY;
    private String subcategory = EMPTY;
    private Contract contract;
    private String timeZoneId = EMPTY;
    private String tradingClass = EMPTY;
    private String tradingHours = EMPTY;
    private int underlyingContractId;
    private String validExchanges = EMPTY;
    private static final Function<String, OrderType> FROM_STRING_TO_ORDER_TYPE_FUNCTION = new Function<String, OrderType>() {
        @Override
        public OrderType apply(final String input) {
            return OrderType.fromAbbreviation(input);
        }
    };

    public String getBondType() {
        return bondType;
    }

    public void setBondType(final String bondType) {
        this.bondType = bondType;
    }

    public boolean isCallable() {
        return callable;
    }

    public void setCallable(final boolean callable) {
        this.callable = callable;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public String getContractMonth() {
        return contractMonth;
    }

    public void setContractMonth(final String contractMonth) {
        this.contractMonth = contractMonth;
    }

    public boolean isConvertible() {
        return convertible;
    }

    public void setConvertible(final boolean convertible) {
        this.convertible = convertible;
    }

    public double getCoupon() {
        return coupon;
    }

    public void setCoupon(final double coupon) {
        this.coupon = coupon;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(final String couponType) {
        this.couponType = couponType;
    }

    public String getCusip() {
        return cusip;
    }

    public void setCusip(final String cusip) {
        this.cusip = cusip;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(final String industry) {
        this.industry = industry;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(final String issueDate) {
        this.issueDate = issueDate;
    }

    public String getLiquidHours() {
        return liquidHours;
    }

    public List<String> getLiquidHoursList() {
        if (StringUtils.isNotEmpty(liquidHours)) {
            return Lists.newArrayList(StringUtils.split(liquidHours, SEMI_COLON_SEPARATOR));
        }
        return Lists.newArrayList();
    }

    public void setLiquidHours(final String liquidHours) {
        this.liquidHours = liquidHours;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(final String longName) {
        this.longName = longName;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(final String marketName) {
        this.marketName = marketName;
    }

    public String getMaturity() {
        return maturity;
    }

    public void setMaturity(final String maturity) {
        this.maturity = maturity;
    }

    public double getMinimumFluctuation() {
        return minimumFluctuation;
    }

    public void setMinimumFluctuation(final double minimumFluctuation) {
        this.minimumFluctuation = minimumFluctuation;
    }

    public String getNextOptionDate() {
        return nextOptionDate;
    }

    public void setNextOptionDate(final String nextOptionDate) {
        this.nextOptionDate = nextOptionDate;
    }

    public boolean isNextOptionPartial() {
        return nextOptionPartial;
    }

    public void setNextOptionPartial(final boolean nextOptionPartial) {
        this.nextOptionPartial = nextOptionPartial;
    }

    public String getNextOptionType() {
        return nextOptionType;
    }

    public void setNextOptionType(final String nextOptionType) {
        this.nextOptionType = nextOptionType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(final String notes) {
        this.notes = notes;
    }

    public String getValidOrderTypes() {
        return validOrderTypes;
    }

    public List<OrderType> getValidOrderTypesList() {
        if (StringUtils.isNotEmpty(validOrderTypes)) {
            final List<String> orderTypeList = Lists.newArrayList(StringUtils.split(validOrderTypes, COMMA_SEPARATOR));
            return Lists.transform(orderTypeList, FROM_STRING_TO_ORDER_TYPE_FUNCTION);
        }
        return Lists.newArrayList();
    }

    public void setValidOrderTypes(final String validOrderTypes) {
        this.validOrderTypes = validOrderTypes;
    }

    public int getPriceMagnifier() {
        return priceMagnifier;
    }

    public void setPriceMagnifier(final int priceMagnifier) {
        this.priceMagnifier = priceMagnifier;
    }

    public boolean isPutable() {
        return putable;
    }

    public void setPutable(final boolean putable) {
        this.putable = putable;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(final String ratings) {
        this.ratings = ratings;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(final String subcategory) {
        this.subcategory = subcategory;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(final Contract contract) {
        this.contract = contract;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(final String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    public String getTradingClass() {
        return tradingClass;
    }

    public void setTradingClass(final String tradingClass) {
        this.tradingClass = tradingClass;
    }

    public String getTradingHours() {
        return tradingHours;
    }

    public List<String> getTradingHoursList() {
        if (StringUtils.isNotEmpty(tradingHours)) {
            return Lists.newArrayList(StringUtils.split(tradingHours, SEMI_COLON_SEPARATOR));
        }
        return Lists.newArrayList();
    }

    public void setTradingHours(final String tradingHours) {
        this.tradingHours = tradingHours;
    }

    public int getUnderlyingContractId() {
        return underlyingContractId;
    }

    public void setUnderlyingContractId(final int underlyingContractId) {
        this.underlyingContractId = underlyingContractId;
    }

    public String getValidExchanges() {
        return validExchanges;
    }

    public List<String> getValidExchangesList() {
        if (StringUtils.isNotEmpty(validExchanges)) {
            return Lists.newArrayList(StringUtils.split(validExchanges, COMMA_SEPARATOR));
        }
        return Lists.newArrayList();
    }

    public void setValidExchanges(final String validExchanges) {
        this.validExchanges = validExchanges;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(bondType).append(callable).append(category).append(contract)
                .append(contractMonth).append(convertible).append(coupon).append(couponType).append(cusip)
                .append(description).append(industry).append(issueDate).append(liquidHours).append(longName)
                .append(marketName).append(maturity).append(minimumFluctuation).append(nextOptionDate)
                .append(nextOptionPartial).append(nextOptionType).append(notes).append(validOrderTypes)
                .append(priceMagnifier).append(putable).append(ratings).append(subcategory).append(timeZoneId)
                .append(tradingClass).append(tradingHours).append(underlyingContractId).append(validExchanges)
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
        final ContractSpecification rhs = (ContractSpecification) obj;
        return new EqualsBuilder().append(bondType, rhs.bondType).append(callable, rhs.callable)
                .append(category, rhs.category).append(contract, rhs.contract).append(contractMonth, rhs.contractMonth)
                .append(convertible, rhs.convertible).append(coupon, rhs.coupon).append(couponType, rhs.couponType)
                .append(cusip, rhs.cusip).append(description, rhs.description).append(industry, rhs.industry)
                .append(issueDate, rhs.issueDate).append(liquidHours, rhs.liquidHours).append(longName, rhs.longName)
                .append(marketName, rhs.marketName).append(maturity, rhs.maturity)
                .append(minimumFluctuation, rhs.minimumFluctuation).append(nextOptionDate, rhs.nextOptionDate)
                .append(nextOptionPartial, rhs.nextOptionPartial).append(nextOptionType, rhs.nextOptionType)
                .append(notes, rhs.notes).append(validOrderTypes, rhs.validOrderTypes)
                .append(priceMagnifier, rhs.priceMagnifier).append(putable, rhs.putable).append(ratings, rhs.ratings)
                .append(subcategory, rhs.subcategory).append(timeZoneId, rhs.timeZoneId)
                .append(tradingClass, rhs.tradingClass).append(tradingHours, rhs.tradingHours)
                .append(underlyingContractId, rhs.underlyingContractId).append(validExchanges, rhs.validExchanges)
                .isEquals();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
