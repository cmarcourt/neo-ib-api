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
	private boolean transmit;
	private StopTriggerMethod stopTriggerMethod = StopTriggerMethod.DEFAULT;
	private double volatility;
	private VolatilityType volatilityType = VolatilityType.INAPPLICABLE;
	private boolean requestPreTradeInformation;

	public Order() {
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
		basisPoint = Double.MAX_VALUE;
		basisPointType = Integer.MAX_VALUE;
		scaleInitialLevelSize = Integer.MAX_VALUE;
		scaleSubsequentLevelSize = Integer.MAX_VALUE;
		scalePriceIncrement = Double.MAX_VALUE;
	}

	public final String getAccountName() {
		return accountName;
	}

	public final void setAccountName(final String accountName) {
		this.accountName = accountName;
	}

	public final OrderAction getAction() {
		return action;
	}

	public final void setAction(final OrderAction action) {
		this.action = action;
	}

	public final List<PairTagValue> getAlgorithmParameters() {
		return algorithmParameters;
	}

	public final void setAlgorithmParameters(final List<PairTagValue> algorithmParameters) {
		this.algorithmParameters = algorithmParameters;
	}

	public final String getAlgorithmStrategy() {
		return algorithmStrategy;
	}

	public final void setAlgorithmStrategy(final String algorithmStrategy) {
		this.algorithmStrategy = algorithmStrategy;
	}

	public final boolean isAllOrNone() {
		return allOrNone;
	}

	public final void setAllOrNone(final boolean allOrNone) {
		this.allOrNone = allOrNone;
	}

	public final AuctionStrategy getAuctionStrategy() {
		return auctionStrategy;
	}

	public final void setAuctionStrategy(final AuctionStrategy auctionStrategy) {
		this.auctionStrategy = auctionStrategy;
	}

	public final double getStopPrice() {
		return stopPrice;
	}

	public final void setStopPrice(final double stopPrice) {
		this.stopPrice = stopPrice;
	}

	public final double getBasisPoint() {
		return basisPoint;
	}

	public final void setBasisPoint(final double basisPoint) {
		this.basisPoint = basisPoint;
	}

	public final int getBasisPointType() {
		return basisPointType;
	}

	public final void setBasisPointType(final int basisPointType) {
		this.basisPointType = basisPointType;
	}

	public final boolean isBlockOrder() {
		return blockOrder;
	}

	public final void setBlockOrder(final boolean blockOrder) {
		this.blockOrder = blockOrder;
	}

	public final String getClearingAccount() {
		return clearingAccount;
	}

	public final void setClearingAccount(final String clearingAccount) {
		this.clearingAccount = clearingAccount;
	}

	public final String getClearingIntent() {
		return clearingIntent;
	}

	public final void setClearingIntent(final String clearingIntent) {
		this.clearingIntent = clearingIntent;
	}

	public final int getClientId() {
		return clientId;
	}

	public final void setClientId(final int clientId) {
		this.clientId = clientId;
	}

	public final int getContinuouslyUpdate() {
		return continuouslyUpdate;
	}

	public final void setContinuouslyUpdate(final int continuouslyUpdate) {
		this.continuouslyUpdate = continuouslyUpdate;
	}

	public final double getDelta() {
		return delta;
	}

	public final void setDelta(final double delta) {
		this.delta = delta;
	}

	public final double getDeltaNeutralAuxPrice() {
		return deltaNeutralAuxPrice;
	}

	public final void setDeltaNeutralAuxPrice(final double deltaNeutralAuxPrice) {
		this.deltaNeutralAuxPrice = deltaNeutralAuxPrice;
	}

	public final String getDeltaNeutralClearingAccount() {
		return deltaNeutralClearingAccount;
	}

	public final void setDeltaNeutralClearingAccount(final String deltaNeutralClearingAccount) {
		this.deltaNeutralClearingAccount = deltaNeutralClearingAccount;
	}

	public final String getDeltaNeutralClearingIntent() {
		return deltaNeutralClearingIntent;
	}

	public final void setDeltaNeutralClearingIntent(final String deltaNeutralClearingIntent) {
		this.deltaNeutralClearingIntent = deltaNeutralClearingIntent;
	}

	public final int getDeltaNeutralContractId() {
		return deltaNeutralContractId;
	}

	public final void setDeltaNeutralContractId(final int deltaNeutralContractId) {
		this.deltaNeutralContractId = deltaNeutralContractId;
	}

	public final String getDeltaNeutralOrderType() {
		return deltaNeutralOrderType;
	}

	public final void setDeltaNeutralOrderType(final String deltaNeutralOrderType) {
		this.deltaNeutralOrderType = deltaNeutralOrderType;
	}

	public final String getDeltaNeutralSettlingFirm() {
		return deltaNeutralSettlingFirm;
	}

	public final void setDeltaNeutralSettlingFirm(final String deltaNeutralSettlingFirm) {
		this.deltaNeutralSettlingFirm = deltaNeutralSettlingFirm;
	}

	public final String getDesignatedLocation() {
		return designatedLocation;
	}

	public final void setDesignatedLocation(final String designatedLocation) {
		this.designatedLocation = designatedLocation;
	}

	public final double getDiscretionaryAmount() {
		return discretionaryAmount;
	}

	public final void setDiscretionaryAmount(final double discretionaryAmount) {
		this.discretionaryAmount = discretionaryAmount;
	}

	public final int getDisplaySize() {
		return displaySize;
	}

	public final void setDisplaySize(final int displaySize) {
		this.displaySize = displaySize;
	}

	public final boolean isElectronicTradeOnly() {
		return electronicTradeOnly;
	}

	public final void setElectronicTradeOnly(final boolean electronicTradeOnly) {
		this.electronicTradeOnly = electronicTradeOnly;
	}

	public final int getExemptionCode() {
		return exemptionCode;
	}

	public final void setExemptionCode(final int exemptCode) {
		exemptionCode = exemptCode;
	}

	public final String getFinancialAdvisorGroup() {
		return financialAdvisorGroup;
	}

	public final void setFinancialAdvisorGroup(final String financialAdvisorGroup) {
		this.financialAdvisorGroup = financialAdvisorGroup;
	}

	public final String getFinancialAdvisorMethod() {
		return financialAdvisorMethod;
	}

	public final void setFinancialAdvisorMethod(final String financialAdvisorMethod) {
		this.financialAdvisorMethod = financialAdvisorMethod;
	}

	public final String getFinancialAdvisorPercentage() {
		return financialAdvisorPercentage;
	}

	public final void setFinancialAdvisorPercentage(final String financialAdvisorPercentage) {
		this.financialAdvisorPercentage = financialAdvisorPercentage;
	}

	public final String getFinancialAdvisorProfile() {
		return financialAdvisorProfile;
	}

	public final void setFinancialAdvisorProfile(final String financialAdvisorProfile) {
		this.financialAdvisorProfile = financialAdvisorProfile;
	}

	public final boolean isFirmQuoteOnly() {
		return firmQuoteOnly;
	}

	public final void setFirmQuoteOnly(final boolean firmQuoteOnly) {
		this.firmQuoteOnly = firmQuoteOnly;
	}

	public final String getGoodAfterDateTime() {
		return goodAfterDateTime;
	}

	public final void setGoodAfterDateTime(final String goodAfterDateTime) {
		this.goodAfterDateTime = goodAfterDateTime;
	}

	public final String getGoodTillDateTime() {
		return goodTillDateTime;
	}

	public final void setGoodTillDateTime(final String goodTillDateTime) {
		this.goodTillDateTime = goodTillDateTime;
	}

	public final String getHedgeParameter() {
		return hedgeParameter;
	}

	public final void setHedgeParameter(final String hedgeParameter) {
		this.hedgeParameter = hedgeParameter;
	}

	public final HedgeType getHedgeType() {
		return hedgeType;
	}

	public final void setHedgeType(final HedgeType hedgeType) {
		this.hedgeType = hedgeType;
	}

	public final boolean isHidden() {
		return hidden;
	}

	public final void setHidden(final boolean hidden) {
		this.hidden = hidden;
	}

	public final double getLimitPrice() {
		return limitPrice;
	}

	public final void setLimitPrice(final double limitPrice) {
		this.limitPrice = limitPrice;
	}

	public final int getMinimumQuantity() {
		return minimumQuantity;
	}

	public final void setMinimumQuantity(final int minimumQuantity) {
		this.minimumQuantity = minimumQuantity;
	}

	public final double getNbboPriceCap() {
		return nbboPriceCap;
	}

	public final void setNbboPriceCap(final double nbboPriceCap) {
		this.nbboPriceCap = nbboPriceCap;
	}

	public final boolean isNotHeld() {
		return notHeld;
	}

	public final void setNotHeld(final boolean notHeld) {
		this.notHeld = notHeld;
	}

	public final String getOcaGroupName() {
		return ocaGroupName;
	}

	public final void setOcaGroupName(final String ocaGroupName) {
		this.ocaGroupName = ocaGroupName;
	}

	public final OcaType getOcaType() {
		return ocaType;
	}

	public final void setOcaType(final OcaType ocaType) {
		this.ocaType = ocaType;
	}

	public final OpenCloseInstitutional getOpenClose() {
		return openClose;
	}

	public final void setOpenClose(final OpenCloseInstitutional openClose) {
		this.openClose = openClose;
	}

	public final boolean isOptOutSmartRouting() {
		return optOutSmartRouting;
	}

	public final void setOptOutSmartRouting(final boolean optOutSmartRouting) {
		this.optOutSmartRouting = optOutSmartRouting;
	}

	public final OrderId getId() {
		return id;
	}

	public final void setId(final OrderId id) {
		this.id = id;
	}

	public final String getOrderReference() {
		return orderReference;
	}

	public final void setOrderReference(final String orderReference) {
		this.orderReference = orderReference;
	}

	public final OrderType getOrderType() {
		return orderType;
	}

	public final void setOrderType(final OrderType orderType) {
		this.orderType = orderType;
	}

	public final OriginInstitutional getOrigin() {
		return origin;
	}

	public final void setOrigin(final OriginInstitutional origin) {
		this.origin = origin;
	}

	public final boolean isOutsideRegularTradingHours() {
		return outsideRegularTradingHours;
	}

	public final void setOutsideRegularTradingHours(final boolean outsideRegularTradingHours) {
		this.outsideRegularTradingHours = outsideRegularTradingHours;
	}

	public final boolean isOverridePercentageConstraints() {
		return overridePercentageConstraints;
	}

	public final void setOverridePercentageConstraints(final boolean overridePercentageConstraints) {
		this.overridePercentageConstraints = overridePercentageConstraints;
	}

	public final OrderId getParentId() {
		return parentId;
	}

	public final void setParentId(final OrderId parentId) {
		this.parentId = parentId;
	}

	public final double getPercentageOffset() {
		return percentageOffset;
	}

	public final void setPercentageOffset(final double percentOffset) {
		percentageOffset = percentOffset;
	}

	public final int getPermanentId() {
		return permanentId;
	}

	public final void setPermanentId(final int permanentId) {
		this.permanentId = permanentId;
	}

	public final ReferencePriceType getReferencePriceType() {
		return referencePriceType;
	}

	public final void setReferencePriceType(final ReferencePriceType referencePriceType) {
		this.referencePriceType = referencePriceType;
	}

	public final Rule80A getRule80A() {
		return rule80A;
	}

	public final void setRule80A(final Rule80A rule80a) {
		rule80A = rule80a;
	}

	public final int getScaleInitialLevelSize() {
		return scaleInitialLevelSize;
	}

	public final void setScaleInitialLevelSize(final int scaleInitialLevelSize) {
		this.scaleInitialLevelSize = scaleInitialLevelSize;
	}

	public final double getScalePriceIncrement() {
		return scalePriceIncrement;
	}

	public final void setScalePriceIncrement(final double scalePriceIncrement) {
		this.scalePriceIncrement = scalePriceIncrement;
	}

	public final int getScaleSubsequentLevelSize() {
		return scaleSubsequentLevelSize;
	}

	public final void setScaleSubsequentLevelSize(final int scaleSubsequentLevelSize) {
		this.scaleSubsequentLevelSize = scaleSubsequentLevelSize;
	}

	public final String getSettlingFirm() {
		return settlingFirm;
	}

	public final void setSettlingFirm(final String settlingFirm) {
		this.settlingFirm = settlingFirm;
	}

	public final ShortSaleSlotInstitutional getShortSaleSlot() {
		return shortSaleSlot;
	}

	public final void setShortSaleSlot(final ShortSaleSlotInstitutional shortSaleSlot) {
		this.shortSaleSlot = shortSaleSlot;
	}

	public final List<PairTagValue> getSmartComboRoutingParameters() {
		return smartComboRoutingParameters;
	}

	public final void setSmartComboRoutingParameters(final List<PairTagValue> smartComboRoutingParameters) {
		this.smartComboRoutingParameters = smartComboRoutingParameters;
	}

	public final double getStartingPrice() {
		return startingPrice;
	}

	public final void setStartingPrice(final double startingPrice) {
		this.startingPrice = startingPrice;
	}

	public final double getLowerStockPriceRange() {
		return lowerStockPriceRange;
	}

	public final void setLowerStockPriceRange(final double lowerStockPriceRange) {
		this.lowerStockPriceRange = lowerStockPriceRange;
	}

	public final double getUpperStockPriceRange() {
		return upperStockPriceRange;
	}

	public final void setUpperStockPriceRange(final double upperStockPriceRange) {
		this.upperStockPriceRange = upperStockPriceRange;
	}

	public final double getStockReferencePrice() {
		return stockReferencePrice;
	}

	public final void setStockReferencePrice(final double stockReferencePrice) {
		this.stockReferencePrice = stockReferencePrice;
	}

	public final boolean isSweepToFill() {
		return sweepToFill;
	}

	public final void setSweepToFill(final boolean sweepToFill) {
		this.sweepToFill = sweepToFill;
	}

	public final TimeInForce getTimeInForce() {
		return timeInForce;
	}

	public final void setTimeInForce(final TimeInForce timeInForce) {
		this.timeInForce = timeInForce;
	}

	public final int getTotalQuantity() {
		return totalQuantity;
	}

	public final void setTotalQuantity(final int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public final double getTrailingStopPrice() {
		return trailingStopPrice;
	}

	public final void setTrailingStopPrice(final double trailingStopPrice) {
		this.trailingStopPrice = trailingStopPrice;
	}

	public final boolean isTransmit() {
		return transmit;
	}

	public final void setTransmit(final boolean transmit) {
		this.transmit = transmit;
	}

	public final StopTriggerMethod getStopTriggerMethod() {
		return stopTriggerMethod;
	}

	public final void setStopTriggerMethod(final StopTriggerMethod stopTriggerMethod) {
		this.stopTriggerMethod = stopTriggerMethod;
	}

	public final double getVolatility() {
		return volatility;
	}

	public final void setVolatility(final double volatility) {
		this.volatility = volatility;
	}

	public final VolatilityType getVolatilityType() {
		return volatilityType;
	}

	public final void setVolatilityType(final VolatilityType volatilityType) {
		this.volatilityType = volatilityType;
	}

	public final boolean isRequestPreTradeInformation() {
		return requestPreTradeInformation;
	}

	public final void setRequestPreTradeInformation(final boolean requestPreTradeInformation) {
		this.requestPreTradeInformation = requestPreTradeInformation;
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
				.append(ocaGroupName).append(ocaType).append(openClose).append(optOutSmartRouting).append(id)
				.append(orderReference).append(orderType).append(origin).append(outsideRegularTradingHours)
				.append(overridePercentageConstraints).append(parentId).append(percentageOffset).append(permanentId)
				.append(referencePriceType).append(requestPreTradeInformation).append(rule80A)
				.append(scaleInitialLevelSize).append(scalePriceIncrement).append(scaleSubsequentLevelSize)
				.append(settlingFirm).append(shortSaleSlot).append(smartComboRoutingParameters).append(startingPrice)
				.append(stockReferencePrice).append(stopPrice).append(stopTriggerMethod).append(sweepToFill)
				.append(timeInForce).append(totalQuantity).append(trailingStopPrice).append(transmit)
				.append(upperStockPriceRange).append(volatility).append(volatilityType).toHashCode();
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
				.append(openClose, rhs.openClose).append(optOutSmartRouting, rhs.optOutSmartRouting).append(id, rhs.id)
				.append(orderReference, rhs.orderReference).append(orderType, rhs.orderType).append(origin, rhs.origin)
				.append(outsideRegularTradingHours, rhs.outsideRegularTradingHours)
				.append(overridePercentageConstraints, rhs.overridePercentageConstraints)
				.append(parentId, rhs.parentId).append(percentageOffset, rhs.percentageOffset)
				.append(permanentId, rhs.permanentId).append(referencePriceType, rhs.referencePriceType)
				.append(requestPreTradeInformation, rhs.requestPreTradeInformation).append(rule80A, rhs.rule80A)
				.append(scaleInitialLevelSize, rhs.scaleInitialLevelSize)
				.append(scalePriceIncrement, rhs.scalePriceIncrement)
				.append(scaleSubsequentLevelSize, rhs.scaleSubsequentLevelSize).append(settlingFirm, rhs.settlingFirm)
				.append(shortSaleSlot, rhs.shortSaleSlot)
				.append(smartComboRoutingParameters, rhs.smartComboRoutingParameters)
				.append(startingPrice, rhs.startingPrice).append(stockReferencePrice, rhs.stockReferencePrice)
				.append(stopPrice, rhs.stopPrice).append(stopTriggerMethod, rhs.stopTriggerMethod)
				.append(sweepToFill, rhs.sweepToFill).append(timeInForce, rhs.timeInForce)
				.append(totalQuantity, rhs.totalQuantity).append(trailingStopPrice, rhs.trailingStopPrice)
				.append(transmit, rhs.transmit).append(upperStockPriceRange, rhs.upperStockPriceRange)
				.append(volatility, rhs.volatility).append(volatilityType, rhs.volatilityType).isEquals();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
