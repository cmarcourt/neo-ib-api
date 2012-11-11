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

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ch.aonyx.broker.ib.api.OrderId;

import com.google.common.collect.Lists;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class Order {

    private static final String EMPTY = "";
    private String accountName = EMPTY;
    private OrderAction action;
    private List<PairTagValue> algorithmParameters = Lists.newArrayList();
    private String algorithmStrategy = EMPTY;
    private boolean allOrNone;
    private AuctionStrategy auctionStrategy = AuctionStrategy.INAPPLICABLE;
    private double stopPrice;
    private double basisPoint;
    private int basisPointType;
    private boolean blockOrder;
    private String clearingAccount = EMPTY;
    private String clearingIntent = EMPTY;
    private int clientId;
    private int continuouslyUpdate;
    private double delta;
    private double deltaNeutralAuxPrice;
    private String deltaNeutralClearingAccount = EMPTY;
    private String deltaNeutralClearingIntent = EMPTY;
    private int deltaNeutralContractId;
    private String deltaNeutralOrderType = EMPTY;
    private String deltaNeutralSettlingFirm = EMPTY;
    private String designatedLocation = EMPTY;
    private double discretionaryAmount;
    private int displaySize;
    private boolean electronicTradeOnly;
    private int exemptionCode;
    private String financialAdvisorGroup = EMPTY;
    private String financialAdvisorMethod = EMPTY;
    private String financialAdvisorPercentage = EMPTY;
    private String financialAdvisorProfile = EMPTY;
    private boolean firmQuoteOnly;
    private String goodAfterDateTime = EMPTY;
    private String goodTillDateTime = EMPTY;
    private String hedgeParameter = EMPTY;
    private HedgeType hedgeType = HedgeType.EMPTY;
    private boolean hidden;
    private double limitPrice;
    private int minimumQuantity;
    private double nbboPriceCap;
    private boolean notHeld;
    private String ocaGroupName = EMPTY;
    private OcaType ocaType = OcaType.EMPTY;
    private OpenCloseInstitutional openClose = OpenCloseInstitutional.OPEN;
    private boolean optOutSmartRouting;
    private OrderId id;
    private String orderReference = EMPTY;
    private OrderType orderType;
    private OriginInstitutional origin = OriginInstitutional.CUSTOMER;
    private boolean outsideRegularTradingHours;
    private boolean overridePercentageConstraints;
    private OrderId parentId;
    private double percentageOffset;
    private int permanentId;
    private ReferencePriceType referencePriceType = ReferencePriceType.INAPPLICABLE;
    private Rule80A rule80A = Rule80A.EMPTY;
    private int scaleInitialLevelSize;
    private double scalePriceIncrement;
    private int scaleSubsequentLevelSize;
    private String settlingFirm = EMPTY;
    private ShortSaleSlotInstitutional shortSaleSlot = ShortSaleSlotInstitutional.INAPPLICABLE;
    private List<PairTagValue> smartComboRoutingParameters = Lists.newArrayList();
    private double startingPrice;
    private double lowerStockPriceRange;
    private double upperStockPriceRange;
    private double stockReferencePrice;
    private boolean sweepToFill;
    private TimeInForce timeInForce = TimeInForce.EMPTY;
    private int totalQuantity;
    private double trailingStopPrice;
    private double trailingPercent;
    private boolean transmit;
    private StopTriggerMethod stopTriggerMethod = StopTriggerMethod.DEFAULT;
    private double volatility;
    private VolatilityType volatilityType = VolatilityType.INAPPLICABLE;
    private boolean requestPreTradeInformation;
    private double scalePriceAdjustValue;
    private int scalePriceAdjustInterval;
    private double scaleProfitOffset;
    private boolean scaleAutoReset;
    private int scaleInitPosition;
    private int scaleInitFillQuantity;
    private boolean scaleRandomPercent;
    private List<OrderComboLeg> orderComboLegs = Lists.newArrayList();

    public Order() {
        limitPrice = Double.MAX_VALUE;
        stopPrice = Double.MAX_VALUE;
        transmit = true;
        designatedLocation = EMPTY;
        exemptionCode = -1;
        minimumQuantity = Integer.MAX_VALUE;
        percentageOffset = Double.MAX_VALUE;
        nbboPriceCap = Double.MAX_VALUE;
        startingPrice = Double.MAX_VALUE;
        stockReferencePrice = Double.MAX_VALUE;
        delta = Double.MAX_VALUE;
        lowerStockPriceRange = Double.MAX_VALUE;
        upperStockPriceRange = Double.MAX_VALUE;
        volatility = Double.MAX_VALUE;
        deltaNeutralOrderType = EMPTY;
        deltaNeutralAuxPrice = Double.MAX_VALUE;
        deltaNeutralSettlingFirm = EMPTY;
        deltaNeutralClearingAccount = EMPTY;
        deltaNeutralClearingIntent = EMPTY;
        trailingStopPrice = Double.MAX_VALUE;
        trailingPercent = Double.MAX_VALUE;
        basisPoint = Double.MAX_VALUE;
        basisPointType = Integer.MAX_VALUE;
        scaleInitialLevelSize = Integer.MAX_VALUE;
        scaleSubsequentLevelSize = Integer.MAX_VALUE;
        scalePriceIncrement = Double.MAX_VALUE;
        scalePriceAdjustValue = Double.MAX_VALUE;
        scalePriceAdjustInterval = Integer.MAX_VALUE;
        scaleProfitOffset = Double.MAX_VALUE;
        scaleInitPosition = Integer.MAX_VALUE;
        scaleInitFillQuantity = Integer.MAX_VALUE;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(final String accountName) {
        this.accountName = accountName;
    }

    public OrderAction getAction() {
        return action;
    }

    public void setAction(final OrderAction action) {
        this.action = action;
    }

    public List<PairTagValue> getAlgorithmParameters() {
        return algorithmParameters;
    }

    public void setAlgorithmParameters(final List<PairTagValue> algorithmParameters) {
        this.algorithmParameters = algorithmParameters;
    }

    public String getAlgorithmStrategy() {
        return algorithmStrategy;
    }

    public void setAlgorithmStrategy(final String algorithmStrategy) {
        this.algorithmStrategy = algorithmStrategy;
    }

    public boolean isAllOrNone() {
        return allOrNone;
    }

    public void setAllOrNone(final boolean allOrNone) {
        this.allOrNone = allOrNone;
    }

    public AuctionStrategy getAuctionStrategy() {
        return auctionStrategy;
    }

    public void setAuctionStrategy(final AuctionStrategy auctionStrategy) {
        this.auctionStrategy = auctionStrategy;
    }

    public double getStopPrice() {
        return stopPrice;
    }

    public void setStopPrice(final double stopPrice) {
        this.stopPrice = stopPrice;
    }

    public double getBasisPoint() {
        return basisPoint;
    }

    public void setBasisPoint(final double basisPoint) {
        this.basisPoint = basisPoint;
    }

    public int getBasisPointType() {
        return basisPointType;
    }

    public void setBasisPointType(final int basisPointType) {
        this.basisPointType = basisPointType;
    }

    public boolean isBlockOrder() {
        return blockOrder;
    }

    public void setBlockOrder(final boolean blockOrder) {
        this.blockOrder = blockOrder;
    }

    public String getClearingAccount() {
        return clearingAccount;
    }

    public void setClearingAccount(final String clearingAccount) {
        this.clearingAccount = clearingAccount;
    }

    public String getClearingIntent() {
        return clearingIntent;
    }

    public void setClearingIntent(final String clearingIntent) {
        this.clearingIntent = clearingIntent;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(final int clientId) {
        this.clientId = clientId;
    }

    public int getContinuouslyUpdate() {
        return continuouslyUpdate;
    }

    public void setContinuouslyUpdate(final int continuouslyUpdate) {
        this.continuouslyUpdate = continuouslyUpdate;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(final double delta) {
        this.delta = delta;
    }

    public double getDeltaNeutralAuxPrice() {
        return deltaNeutralAuxPrice;
    }

    public void setDeltaNeutralAuxPrice(final double deltaNeutralAuxPrice) {
        this.deltaNeutralAuxPrice = deltaNeutralAuxPrice;
    }

    public String getDeltaNeutralClearingAccount() {
        return deltaNeutralClearingAccount;
    }

    public void setDeltaNeutralClearingAccount(final String deltaNeutralClearingAccount) {
        this.deltaNeutralClearingAccount = deltaNeutralClearingAccount;
    }

    public String getDeltaNeutralClearingIntent() {
        return deltaNeutralClearingIntent;
    }

    public void setDeltaNeutralClearingIntent(final String deltaNeutralClearingIntent) {
        this.deltaNeutralClearingIntent = deltaNeutralClearingIntent;
    }

    public int getDeltaNeutralContractId() {
        return deltaNeutralContractId;
    }

    public void setDeltaNeutralContractId(final int deltaNeutralContractId) {
        this.deltaNeutralContractId = deltaNeutralContractId;
    }

    public String getDeltaNeutralOrderType() {
        return deltaNeutralOrderType;
    }

    public void setDeltaNeutralOrderType(final String deltaNeutralOrderType) {
        this.deltaNeutralOrderType = deltaNeutralOrderType;
    }

    public String getDeltaNeutralSettlingFirm() {
        return deltaNeutralSettlingFirm;
    }

    public void setDeltaNeutralSettlingFirm(final String deltaNeutralSettlingFirm) {
        this.deltaNeutralSettlingFirm = deltaNeutralSettlingFirm;
    }

    public String getDesignatedLocation() {
        return designatedLocation;
    }

    public void setDesignatedLocation(final String designatedLocation) {
        this.designatedLocation = designatedLocation;
    }

    public double getDiscretionaryAmount() {
        return discretionaryAmount;
    }

    public void setDiscretionaryAmount(final double discretionaryAmount) {
        this.discretionaryAmount = discretionaryAmount;
    }

    public int getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(final int displaySize) {
        this.displaySize = displaySize;
    }

    public boolean isElectronicTradeOnly() {
        return electronicTradeOnly;
    }

    public void setElectronicTradeOnly(final boolean electronicTradeOnly) {
        this.electronicTradeOnly = electronicTradeOnly;
    }

    public int getExemptionCode() {
        return exemptionCode;
    }

    public void setExemptionCode(final int exemptCode) {
        exemptionCode = exemptCode;
    }

    public String getFinancialAdvisorGroup() {
        return financialAdvisorGroup;
    }

    public void setFinancialAdvisorGroup(final String financialAdvisorGroup) {
        this.financialAdvisorGroup = financialAdvisorGroup;
    }

    public String getFinancialAdvisorMethod() {
        return financialAdvisorMethod;
    }

    public void setFinancialAdvisorMethod(final String financialAdvisorMethod) {
        this.financialAdvisorMethod = financialAdvisorMethod;
    }

    public String getFinancialAdvisorPercentage() {
        return financialAdvisorPercentage;
    }

    public void setFinancialAdvisorPercentage(final String financialAdvisorPercentage) {
        this.financialAdvisorPercentage = financialAdvisorPercentage;
    }

    public String getFinancialAdvisorProfile() {
        return financialAdvisorProfile;
    }

    public void setFinancialAdvisorProfile(final String financialAdvisorProfile) {
        this.financialAdvisorProfile = financialAdvisorProfile;
    }

    public boolean isFirmQuoteOnly() {
        return firmQuoteOnly;
    }

    public void setFirmQuoteOnly(final boolean firmQuoteOnly) {
        this.firmQuoteOnly = firmQuoteOnly;
    }

    public String getGoodAfterDateTime() {
        return goodAfterDateTime;
    }

    public void setGoodAfterDateTime(final String goodAfterDateTime) {
        this.goodAfterDateTime = goodAfterDateTime;
    }

    public String getGoodTillDateTime() {
        return goodTillDateTime;
    }

    public void setGoodTillDateTime(final String goodTillDateTime) {
        this.goodTillDateTime = goodTillDateTime;
    }

    public String getHedgeParameter() {
        return hedgeParameter;
    }

    public void setHedgeParameter(final String hedgeParameter) {
        this.hedgeParameter = hedgeParameter;
    }

    public HedgeType getHedgeType() {
        return hedgeType;
    }

    public void setHedgeType(final HedgeType hedgeType) {
        this.hedgeType = hedgeType;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(final boolean hidden) {
        this.hidden = hidden;
    }

    public double getLimitPrice() {
        return limitPrice;
    }

    public void setLimitPrice(final double limitPrice) {
        this.limitPrice = limitPrice;
    }

    public int getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(final int minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    public double getNbboPriceCap() {
        return nbboPriceCap;
    }

    public void setNbboPriceCap(final double nbboPriceCap) {
        this.nbboPriceCap = nbboPriceCap;
    }

    public boolean isNotHeld() {
        return notHeld;
    }

    public void setNotHeld(final boolean notHeld) {
        this.notHeld = notHeld;
    }

    public String getOcaGroupName() {
        return ocaGroupName;
    }

    public void setOcaGroupName(final String ocaGroupName) {
        this.ocaGroupName = ocaGroupName;
    }

    public OcaType getOcaType() {
        return ocaType;
    }

    public void setOcaType(final OcaType ocaType) {
        this.ocaType = ocaType;
    }

    public OpenCloseInstitutional getOpenClose() {
        return openClose;
    }

    public void setOpenClose(final OpenCloseInstitutional openClose) {
        this.openClose = openClose;
    }

    public boolean isOptOutSmartRouting() {
        return optOutSmartRouting;
    }

    public void setOptOutSmartRouting(final boolean optOutSmartRouting) {
        this.optOutSmartRouting = optOutSmartRouting;
    }

    public OrderId getId() {
        return id;
    }

    public void setId(final OrderId id) {
        this.id = id;
    }

    public String getOrderReference() {
        return orderReference;
    }

    public void setOrderReference(final String orderReference) {
        this.orderReference = orderReference;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(final OrderType orderType) {
        this.orderType = orderType;
    }

    public OriginInstitutional getOrigin() {
        return origin;
    }

    public void setOrigin(final OriginInstitutional origin) {
        this.origin = origin;
    }

    public boolean isOutsideRegularTradingHours() {
        return outsideRegularTradingHours;
    }

    public void setOutsideRegularTradingHours(final boolean outsideRegularTradingHours) {
        this.outsideRegularTradingHours = outsideRegularTradingHours;
    }

    public boolean isOverridePercentageConstraints() {
        return overridePercentageConstraints;
    }

    public void setOverridePercentageConstraints(final boolean overridePercentageConstraints) {
        this.overridePercentageConstraints = overridePercentageConstraints;
    }

    public OrderId getParentId() {
        return parentId;
    }

    public void setParentId(final OrderId parentId) {
        this.parentId = parentId;
    }

    public double getPercentageOffset() {
        return percentageOffset;
    }

    public void setPercentageOffset(final double percentOffset) {
        percentageOffset = percentOffset;
    }

    public int getPermanentId() {
        return permanentId;
    }

    public void setPermanentId(final int permanentId) {
        this.permanentId = permanentId;
    }

    public ReferencePriceType getReferencePriceType() {
        return referencePriceType;
    }

    public void setReferencePriceType(final ReferencePriceType referencePriceType) {
        this.referencePriceType = referencePriceType;
    }

    public Rule80A getRule80A() {
        return rule80A;
    }

    public void setRule80A(final Rule80A rule80a) {
        rule80A = rule80a;
    }

    public int getScaleInitialLevelSize() {
        return scaleInitialLevelSize;
    }

    public void setScaleInitialLevelSize(final int scaleInitialLevelSize) {
        this.scaleInitialLevelSize = scaleInitialLevelSize;
    }

    public double getScalePriceIncrement() {
        return scalePriceIncrement;
    }

    public void setScalePriceIncrement(final double scalePriceIncrement) {
        this.scalePriceIncrement = scalePriceIncrement;
    }

    public int getScaleSubsequentLevelSize() {
        return scaleSubsequentLevelSize;
    }

    public void setScaleSubsequentLevelSize(final int scaleSubsequentLevelSize) {
        this.scaleSubsequentLevelSize = scaleSubsequentLevelSize;
    }

    public String getSettlingFirm() {
        return settlingFirm;
    }

    public void setSettlingFirm(final String settlingFirm) {
        this.settlingFirm = settlingFirm;
    }

    public ShortSaleSlotInstitutional getShortSaleSlot() {
        return shortSaleSlot;
    }

    public void setShortSaleSlot(final ShortSaleSlotInstitutional shortSaleSlot) {
        this.shortSaleSlot = shortSaleSlot;
    }

    public List<PairTagValue> getSmartComboRoutingParameters() {
        return smartComboRoutingParameters;
    }

    public void setSmartComboRoutingParameters(final List<PairTagValue> smartComboRoutingParameters) {
        this.smartComboRoutingParameters = smartComboRoutingParameters;
    }

    public double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(final double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public double getLowerStockPriceRange() {
        return lowerStockPriceRange;
    }

    public void setLowerStockPriceRange(final double lowerStockPriceRange) {
        this.lowerStockPriceRange = lowerStockPriceRange;
    }

    public double getUpperStockPriceRange() {
        return upperStockPriceRange;
    }

    public void setUpperStockPriceRange(final double upperStockPriceRange) {
        this.upperStockPriceRange = upperStockPriceRange;
    }

    public double getStockReferencePrice() {
        return stockReferencePrice;
    }

    public void setStockReferencePrice(final double stockReferencePrice) {
        this.stockReferencePrice = stockReferencePrice;
    }

    public boolean isSweepToFill() {
        return sweepToFill;
    }

    public void setSweepToFill(final boolean sweepToFill) {
        this.sweepToFill = sweepToFill;
    }

    public TimeInForce getTimeInForce() {
        return timeInForce;
    }

    public void setTimeInForce(final TimeInForce timeInForce) {
        this.timeInForce = timeInForce;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(final int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getTrailingStopPrice() {
        return trailingStopPrice;
    }

    public void setTrailingStopPrice(final double trailingStopPrice) {
        this.trailingStopPrice = trailingStopPrice;
    }

    public double getTrailingPercent() {
        return trailingPercent;
    }

    public void setTrailingPercent(final double trailingPercent) {
        this.trailingPercent = trailingPercent;
    }

    public boolean isTransmit() {
        return transmit;
    }

    public void setTransmit(final boolean transmit) {
        this.transmit = transmit;
    }

    public StopTriggerMethod getStopTriggerMethod() {
        return stopTriggerMethod;
    }

    public void setStopTriggerMethod(final StopTriggerMethod stopTriggerMethod) {
        this.stopTriggerMethod = stopTriggerMethod;
    }

    public double getVolatility() {
        return volatility;
    }

    public void setVolatility(final double volatility) {
        this.volatility = volatility;
    }

    public VolatilityType getVolatilityType() {
        return volatilityType;
    }

    public void setVolatilityType(final VolatilityType volatilityType) {
        this.volatilityType = volatilityType;
    }

    public boolean isRequestPreTradeInformation() {
        return requestPreTradeInformation;
    }

    public void setRequestPreTradeInformation(final boolean requestPreTradeInformation) {
        this.requestPreTradeInformation = requestPreTradeInformation;
    }

    public double getScalePriceAdjustValue() {
        return scalePriceAdjustValue;
    }

    public void setScalePriceAdjustValue(final double scalePriceAdjustValue) {
        this.scalePriceAdjustValue = scalePriceAdjustValue;
    }

    public int getScalePriceAdjustInterval() {
        return scalePriceAdjustInterval;
    }

    public void setScalePriceAdjustInterval(final int scalePriceAdjustInterval) {
        this.scalePriceAdjustInterval = scalePriceAdjustInterval;
    }

    public double getScaleProfitOffset() {
        return scaleProfitOffset;
    }

    public void setScaleProfitOffset(final double scaleProfitOffset) {
        this.scaleProfitOffset = scaleProfitOffset;
    }

    public boolean isScaleAutoReset() {
        return scaleAutoReset;
    }

    public void setScaleAutoReset(final boolean scaleAutoReset) {
        this.scaleAutoReset = scaleAutoReset;
    }

    public int getScaleInitPosition() {
        return scaleInitPosition;
    }

    public void setScaleInitPosition(final int scaleInitPosition) {
        this.scaleInitPosition = scaleInitPosition;
    }

    public int getScaleInitFillQuantity() {
        return scaleInitFillQuantity;
    }

    public void setScaleInitFillQuantity(final int scaleInitFillQuantity) {
        this.scaleInitFillQuantity = scaleInitFillQuantity;
    }

    public boolean isScaleRandomPercent() {
        return scaleRandomPercent;
    }

    public void setScaleRandomPercent(final boolean scaleRandomPercent) {
        this.scaleRandomPercent = scaleRandomPercent;
    }

    public List<OrderComboLeg> getOrderComboLegs() {
        return orderComboLegs;
    }

    public void setOrderComboLegs(final List<OrderComboLeg> orderComboLegs) {
        this.orderComboLegs = orderComboLegs;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(accountName).append(action).append(algorithmParameters)
                .append(algorithmStrategy).append(allOrNone).append(auctionStrategy).append(basisPoint)
                .append(basisPointType).append(blockOrder).append(clearingAccount).append(clearingIntent)
                .append(clientId).append(continuouslyUpdate).append(delta).append(deltaNeutralAuxPrice)
                .append(deltaNeutralClearingAccount).append(deltaNeutralClearingIntent).append(deltaNeutralContractId)
                .append(deltaNeutralOrderType).append(deltaNeutralSettlingFirm).append(designatedLocation)
                .append(discretionaryAmount).append(displaySize).append(electronicTradeOnly).append(exemptionCode)
                .append(financialAdvisorGroup).append(financialAdvisorMethod).append(financialAdvisorPercentage)
                .append(financialAdvisorProfile).append(firmQuoteOnly).append(goodAfterDateTime)
                .append(goodTillDateTime).append(hedgeParameter).append(hedgeType).append(hidden).append(limitPrice)
                .append(lowerStockPriceRange).append(minimumQuantity).append(nbboPriceCap).append(notHeld)
                .append(ocaGroupName).append(ocaType).append(openClose).append(optOutSmartRouting)
                .append(orderComboLegs).append(id).append(orderReference).append(orderType).append(origin)
                .append(outsideRegularTradingHours).append(overridePercentageConstraints).append(parentId)
                .append(percentageOffset).append(permanentId).append(referencePriceType)
                .append(requestPreTradeInformation).append(rule80A).append(scaleAutoReset)
                .append(scaleInitFillQuantity).append(scaleInitialLevelSize).append(scaleInitPosition)
                .append(scalePriceAdjustInterval).append(scalePriceAdjustValue).append(scalePriceIncrement)
                .append(scaleProfitOffset).append(scaleRandomPercent).append(scaleSubsequentLevelSize)
                .append(settlingFirm).append(shortSaleSlot).append(smartComboRoutingParameters).append(startingPrice)
                .append(stockReferencePrice).append(stopPrice).append(stopTriggerMethod).append(sweepToFill)
                .append(timeInForce).append(totalQuantity).append(trailingStopPrice).append(trailingPercent)
                .append(transmit).append(upperStockPriceRange).append(volatility).append(volatilityType).toHashCode();
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
        final Order rhs = (Order) obj;
        return new EqualsBuilder().append(accountName, rhs.accountName).append(action, rhs.action)
                .append(algorithmParameters, rhs.algorithmParameters).append(algorithmStrategy, rhs.algorithmStrategy)
                .append(allOrNone, rhs.allOrNone).append(auctionStrategy, rhs.auctionStrategy)
                .append(basisPoint, rhs.basisPoint).append(basisPointType, rhs.basisPointType)
                .append(blockOrder, rhs.blockOrder).append(clearingAccount, rhs.clearingAccount)
                .append(clearingIntent, rhs.clearingIntent).append(clientId, rhs.clientId)
                .append(continuouslyUpdate, rhs.continuouslyUpdate).append(delta, rhs.delta)
                .append(deltaNeutralAuxPrice, rhs.deltaNeutralAuxPrice)
                .append(deltaNeutralClearingAccount, rhs.deltaNeutralClearingAccount)
                .append(deltaNeutralClearingIntent, rhs.deltaNeutralClearingIntent)
                .append(deltaNeutralContractId, rhs.deltaNeutralContractId)
                .append(deltaNeutralOrderType, rhs.deltaNeutralOrderType)
                .append(deltaNeutralSettlingFirm, rhs.deltaNeutralSettlingFirm)
                .append(designatedLocation, rhs.designatedLocation)
                .append(discretionaryAmount, rhs.discretionaryAmount).append(displaySize, rhs.displaySize)
                .append(electronicTradeOnly, rhs.electronicTradeOnly).append(exemptionCode, rhs.exemptionCode)
                .append(financialAdvisorGroup, rhs.financialAdvisorGroup)
                .append(financialAdvisorMethod, rhs.financialAdvisorMethod)
                .append(financialAdvisorPercentage, rhs.financialAdvisorPercentage)
                .append(financialAdvisorProfile, rhs.financialAdvisorProfile).append(firmQuoteOnly, rhs.firmQuoteOnly)
                .append(goodAfterDateTime, rhs.goodAfterDateTime).append(goodTillDateTime, rhs.goodTillDateTime)
                .append(hedgeParameter, rhs.hedgeParameter).append(hedgeType, rhs.hedgeType).append(hidden, rhs.hidden)
                .append(limitPrice, rhs.limitPrice).append(lowerStockPriceRange, rhs.lowerStockPriceRange)
                .append(minimumQuantity, rhs.minimumQuantity).append(nbboPriceCap, rhs.nbboPriceCap)
                .append(notHeld, rhs.notHeld).append(ocaGroupName, rhs.ocaGroupName).append(ocaType, rhs.ocaType)
                .append(openClose, rhs.openClose).append(optOutSmartRouting, rhs.optOutSmartRouting)
                .append(orderComboLegs, rhs.orderComboLegs).append(id, rhs.id)
                .append(orderReference, rhs.orderReference).append(orderType, rhs.orderType).append(origin, rhs.origin)
                .append(outsideRegularTradingHours, rhs.outsideRegularTradingHours)
                .append(overridePercentageConstraints, rhs.overridePercentageConstraints)
                .append(parentId, rhs.parentId).append(percentageOffset, rhs.percentageOffset)
                .append(permanentId, rhs.permanentId).append(referencePriceType, rhs.referencePriceType)
                .append(requestPreTradeInformation, rhs.requestPreTradeInformation).append(rule80A, rhs.rule80A)
                .append(scaleAutoReset, rhs.scaleAutoReset).append(scaleInitFillQuantity, rhs.scaleInitFillQuantity)
                .append(scaleInitialLevelSize, rhs.scaleInitialLevelSize)
                .append(scaleInitPosition, rhs.scaleInitPosition)
                .append(scalePriceAdjustInterval, rhs.scalePriceAdjustInterval)
                .append(scalePriceAdjustValue, rhs.scalePriceAdjustValue)
                .append(scalePriceIncrement, rhs.scalePriceIncrement).append(scaleProfitOffset, rhs.scaleProfitOffset)
                .append(scaleRandomPercent, rhs.scaleRandomPercent)
                .append(scaleSubsequentLevelSize, rhs.scaleSubsequentLevelSize).append(settlingFirm, rhs.settlingFirm)
                .append(shortSaleSlot, rhs.shortSaleSlot)
                .append(smartComboRoutingParameters, rhs.smartComboRoutingParameters)
                .append(startingPrice, rhs.startingPrice).append(stockReferencePrice, rhs.stockReferencePrice)
                .append(stopPrice, rhs.stopPrice).append(stopTriggerMethod, rhs.stopTriggerMethod)
                .append(sweepToFill, rhs.sweepToFill).append(timeInForce, rhs.timeInForce)
                .append(totalQuantity, rhs.totalQuantity).append(trailingStopPrice, rhs.trailingStopPrice)
                .append(trailingPercent, rhs.trailingPercent).append(transmit, rhs.transmit)
                .append(upperStockPriceRange, rhs.upperStockPriceRange).append(volatility, rhs.volatility)
                .append(volatilityType, rhs.volatilityType).isEquals();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
