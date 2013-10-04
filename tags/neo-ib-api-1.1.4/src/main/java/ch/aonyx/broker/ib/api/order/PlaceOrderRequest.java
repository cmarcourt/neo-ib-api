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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ch.aonyx.broker.ib.api.AbstractOrderRequestSupport;
import ch.aonyx.broker.ib.api.ClientMessageCode;
import ch.aonyx.broker.ib.api.Feature;
import ch.aonyx.broker.ib.api.OrderRequest;
import ch.aonyx.broker.ib.api.OutgoingMessageId;
import ch.aonyx.broker.ib.api.RequestException;
import ch.aonyx.broker.ib.api.contract.Contract;
import ch.aonyx.broker.ib.api.contract.SecurityType;
import ch.aonyx.broker.ib.api.contract.UnderlyingCombo;
import ch.aonyx.broker.ib.api.util.ByteArrayRequestBuilder;
import ch.aonyx.broker.ib.api.util.RequestBuilder;
import ch.aonyx.broker.ib.api.util.StringIdUtils;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class PlaceOrderRequest extends AbstractOrderRequestSupport implements OrderRequest {

    private final Contract contract;
    private final Order order;

    public PlaceOrderRequest(final Order order, final Contract contract) {
        this(StringIdUtils.uniqueIdFromOrderAndContract(order, contract), order, contract);
    }

    public PlaceOrderRequest(final String id, final Order order, final Contract contract) {
        super(id);
        this.contract = contract;
        this.order = order;
    }

    public Contract getContract() {
        return contract;
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public byte[] getBytes() {
        final RequestBuilder builder = createRequestBuilder();
        return builder.toBytes();
    }

    private RequestBuilder createRequestBuilder() {
        final RequestBuilder builder = new ByteArrayRequestBuilder();
        checkDeltaNeutralComboOrdersSupport();
        checkScaleOrderSupport();
        checkAlgorithmOrderSupport();
        checkNotHeldSupport();
        checkSecurityIdTypeSupport();
        checkPlaceOrderByContractIdSupport();
        checkSaleShortSupport();
        checkHedgeOrderSupport();
        checkOptOutSmartRoutingSupport();
        checkDeltaNeutralOrderByContractIdSupport();
        checkScaleOrdersSupport();
        checkOrderComboLegsSupport();
        checkTrailingPriceSupport();
        builder.append(OutgoingMessageId.PLACE_ORDER_REQUEST.getId());
        builder.append(getVersion());
        builder.append(toInternalId(getId()));
        appendContract(builder);
        appendOrder(builder);
        return builder;
    }

    private void checkDeltaNeutralComboOrdersSupport() {
        if (contract.getUnderlyingCombo() != null) {
            if (!Feature.DELTA_NEUTRAL_COMBO_ORDER.isSupportedByVersion(getServerCurrentVersion())) {
                throw new RequestException(ClientMessageCode.UPDATE_TWS, "It does not support delta-neutral orders.",
                        this);
            }
        }
    }

    private void checkScaleOrderSupport() {
        if (order.getScaleSubsequentLevelSize() != Integer.MAX_VALUE) {
            if (!Feature.SCALE_ORDER.isSupportedByVersion(getServerCurrentVersion())) {
                throw new RequestException(ClientMessageCode.UPDATE_TWS,
                        "It does not support Subsequent Level Size for Scale orders.", this);
            }
        }
    }

    private void checkAlgorithmOrderSupport() {
        if (StringUtils.isNotEmpty(order.getAlgorithmStrategy())) {
            if (!Feature.ALGORITHM_ORDER.isSupportedByVersion(getServerCurrentVersion())) {
                throw new RequestException(ClientMessageCode.UPDATE_TWS, "It does not support algo orders.", this);
            }
        }
    }

    private void checkNotHeldSupport() {
        if (order.isNotHeld()) {
            if (!Feature.NOT_HELD.isSupportedByVersion(getServerCurrentVersion())) {
                throw new RequestException(ClientMessageCode.UPDATE_TWS, "It does not support notHeld parameter.", this);
            }
        }
    }

    private void checkSecurityIdTypeSupport() {
        if (StringUtils.isNotEmpty(contract.getSecurityIdentifierCode().getAcronym())
                || StringUtils.isNotEmpty(contract.getSecurityId())) {
            if (!Feature.SECURITY_ID_TYPE.isSupportedByVersion(getServerCurrentVersion())) {
                throw new RequestException(ClientMessageCode.UPDATE_TWS,
                        "It does not support secIdType and secId parameters.", this);
            }
        }
    }

    private void checkPlaceOrderByContractIdSupport() {
        if (contract.getId() > 0) {
            if (!Feature.PLACE_ORDER_BY_CONTRACT_ID.isSupportedByVersion(getServerCurrentVersion())) {
                throw new RequestException(ClientMessageCode.UPDATE_TWS, "It does not support conId parameter.", this);
            }
        }
    }

    private void checkSaleShortSupport() {
        if (!Feature.SHORT_SALE_EXEMPT_ORDER.isSupportedByVersion(getServerCurrentVersion())) {
            if (order.getExemptionCode() != -1) {
                throw new RequestException(ClientMessageCode.UPDATE_TWS, "It does not support exemptCode parameter.",
                        this);
            }
            if (!contract.getComboLegs().isEmpty()) {
                for (final ComboLeg comboLeg : contract.getComboLegs()) {
                    if (comboLeg.getExemptionCode() != -1) {
                        throw new RequestException(ClientMessageCode.UPDATE_TWS,
                                "It does not support exemptCode parameter.", this);
                    }
                }
            }
        }
    }

    private void checkHedgeOrderSupport() {
        if (StringUtils.isNotEmpty(order.getHedgeType().getInitial())) {
            if (!Feature.HEDGING_ORDER.isSupportedByVersion(getServerCurrentVersion())) {
                throw new RequestException(ClientMessageCode.UPDATE_TWS, "It does not support hedge orders.", this);
            }
        }
    }

    private void checkOptOutSmartRoutingSupport() {
        if (order.isOptOutSmartRouting()) {
            if (!Feature.OPT_OUT_DEFAULT_SMART_ROUTING_ASX_ORDER.isSupportedByVersion(getServerCurrentVersion())) {
                throw new RequestException(ClientMessageCode.UPDATE_TWS,
                        "It does not support optOutSmartRouting parameter.", this);
            }
        }
    }

    private void checkDeltaNeutralOrderByContractIdSupport() {
        if ((order.getDeltaNeutralContractId() > 0) || StringUtils.isNotEmpty(order.getDeltaNeutralSettlingFirm())
                || StringUtils.isNotEmpty(order.getDeltaNeutralClearingAccount())
                || StringUtils.isNotEmpty(order.getDeltaNeutralClearingIntent())) {
            if (!Feature.DELTA_NEUTRAL_COMBO_ORDER_BY_CONTRACT_ID.isSupportedByVersion(getServerCurrentVersion())) {
                throw new RequestException(
                        ClientMessageCode.UPDATE_TWS,
                        "It does not support deltaNeutral parameters: ConId, SettlingFirm, ClearingAccount, ClearingIntent.",
                        this);
            }
        }
    }

    private void checkScaleOrdersSupport() {
        final double scalePriceIncrement = order.getScalePriceIncrement();
        if ((scalePriceIncrement > 0) && (scalePriceIncrement != Double.MAX_VALUE)) {
            if ((order.getScalePriceAdjustValue() != Double.MAX_VALUE)
                    || (order.getScalePriceAdjustInterval() != Integer.MAX_VALUE)
                    || (order.getScaleProfitOffset() != Double.MAX_VALUE) || order.isScaleAutoReset()
                    || (order.getScaleInitPosition() != Integer.MAX_VALUE)
                    || (order.getScaleInitFillQuantity() != Integer.MAX_VALUE) || order.isScaleRandomPercent()) {
                if (!Feature.SCALE_ORDERS.isSupportedByVersion(getServerCurrentVersion())) {
                    throw new RequestException(ClientMessageCode.UPDATE_TWS,
                            "It does not support Scale order parameters: PriceAdjustValue, PriceAdjustInterval, "
                                    + "ProfitOffset, AutoReset, InitPosition, InitFillQty and RandomPercent.", this);
                }
            }
        }
    }

    private void checkOrderComboLegsSupport() {
        if (contract.getSecurityType().equals(SecurityType.COMBO)) {
            final List<OrderComboLeg> orderComboLegs = order.getOrderComboLegs();
            if (!orderComboLegs.isEmpty()) {
                for (final OrderComboLeg orderComboLeg : orderComboLegs) {
                    if (orderComboLeg.getPrice() != Double.MAX_VALUE) {
                        if (!Feature.ORDER_COMBO_LEGS_PRICE.isSupportedByVersion(getServerCurrentVersion())) {
                            throw new RequestException(ClientMessageCode.UPDATE_TWS,
                                    "It does not support per-leg prices for order combo legs.", this);
                        }
                    }
                }
            }
        }
    }

    private void checkTrailingPriceSupport() {
        if (order.getTrailingPercent() != Double.MAX_VALUE) {
            if (!Feature.TRAILING_PERCENT.isSupportedByVersion(getServerCurrentVersion())) {
                throw new RequestException(ClientMessageCode.UPDATE_TWS,
                        "It does not support trailing percent parameter.", this);
            }
        }
    }

    private int getVersion() {
        if (!Feature.NOT_HELD.isSupportedByVersion(getServerCurrentVersion())) {
            return 27;
        }
        return 38;
    }

    private void appendContract(final RequestBuilder builder) {
        if (Feature.PLACE_ORDER_BY_CONTRACT_ID.isSupportedByVersion(getServerCurrentVersion())) {
            builder.append(contract.getId());
        }
        builder.append(contract.getSymbol());
        builder.append(contract.getSecurityType().getAbbreviation());
        builder.append(contract.getExpiry());
        builder.append(contract.getStrike());
        builder.append(contract.getOptionRight().getName());
        builder.append(contract.getMultiplier());
        builder.append(contract.getExchange());
        builder.append(contract.getPrimaryExchange());
        builder.append(contract.getCurrencyCode());
        builder.append(contract.getLocalSymbol());
        if (Feature.SECURITY_ID_TYPE.isSupportedByVersion(getServerCurrentVersion())) {
            builder.append(contract.getSecurityIdentifierCode().getAcronym());
            builder.append(contract.getSecurityId());
        }
    }

    private void appendOrder(final RequestBuilder builder) {
        builder.append(order.getAction().getAbbreviation());
        builder.append(order.getTotalQuantity());
        builder.append(order.getOrderType().getAbbreviation());
        final double limitPrice = order.getLimitPrice();
        if (Feature.ORDER_COMBO_LEGS_PRICE.isSupportedByVersion(getServerCurrentVersion())) {
            builder.append(limitPrice);
        } else {
            builder.append(limitPrice == Double.MAX_VALUE ? 0 : limitPrice);
        }
        final double stopPrice = order.getStopPrice();
        if (Feature.TRAILING_PERCENT.isSupportedByVersion(getServerCurrentVersion())) {
            builder.append(stopPrice);
        } else {
            builder.append(stopPrice == Double.MAX_VALUE ? 0 : stopPrice);
        }
        builder.append(order.getTimeInForce().getAbbreviation());
        builder.append(order.getOcaGroupName());
        builder.append(order.getAccountName());
        builder.append(order.getOpenClose().getInitial());
        builder.append(order.getOrigin().getValue());
        builder.append(order.getOrderReference());
        builder.append(order.isTransmit());
        builder.append(toInternalId(order.getParentId()));
        builder.append(order.isBlockOrder());
        builder.append(order.isSweepToFill());
        builder.append(order.getDisplaySize());
        builder.append(order.getStopTriggerMethod().getValue());
        builder.append(order.isOutsideRegularTradingHours());
        builder.append(order.isHidden());
        appendComboLegs(builder);
        builder.append("");
        builder.append(order.getDiscretionaryAmount());
        builder.append(order.getGoodAfterDateTime());
        builder.append(order.getGoodTillDateTime());
        builder.append(order.getFinancialAdvisorGroup());
        builder.append(order.getFinancialAdvisorMethod());
        builder.append(order.getFinancialAdvisorPercentage());
        builder.append(order.getFinancialAdvisorProfile());
        builder.append(order.getShortSaleSlot().getValue());
        builder.append(order.getDesignatedLocation());
        if (Feature.SHORT_SALE_EXEMPT_ORDER_OLD.isSupportedByVersion(getServerCurrentVersion())) {
            builder.append(order.getExemptionCode());
        }
        builder.append(order.getOcaType().getValue());
        builder.append(order.getRule80A().getInitial());
        builder.append(order.getSettlingFirm());
        builder.append(order.isAllOrNone());
        builder.append(order.getMinimumQuantity());
        builder.append(order.getPercentageOffset());
        builder.append(order.isElectronicTradeOnly());
        builder.append(order.isFirmQuoteOnly());
        builder.append(order.getNbboPriceCap());
        builder.append(order.getAuctionStrategy().getValue());
        builder.append(order.getStartingPrice());
        builder.append(order.getStockReferencePrice());
        builder.append(order.getDelta());
        builder.append(order.getLowerStockPriceRange());
        builder.append(order.getUpperStockPriceRange());
        builder.append(order.isOverridePercentageConstraints());
        builder.append(order.getVolatility());
        builder.append(order.getVolatilityType().getValue());
        builder.append(order.getDeltaNeutralOrderType());
        builder.append(order.getDeltaNeutralAuxPrice());
        if (Feature.DELTA_NEUTRAL_COMBO_ORDER_BY_CONTRACT_ID.isSupportedByVersion(getServerCurrentVersion())) {
            if (StringUtils.isNotEmpty(order.getDeltaNeutralOrderType())) {
                builder.append(order.getDeltaNeutralContractId());
                builder.append(order.getDeltaNeutralSettlingFirm());
                builder.append(order.getDeltaNeutralClearingAccount());
                builder.append(order.getDeltaNeutralClearingIntent());
            }
        }
        builder.append(order.getContinuouslyUpdate());
        builder.append(order.getReferencePriceType().getValue());
        builder.append(order.getTrailingStopPrice());
        if (Feature.TRAILING_PERCENT.isSupportedByVersion(getServerCurrentVersion())) {
            builder.append(order.getTrailingPercent());
        }
        if (Feature.SCALE_ORDER.isSupportedByVersion(getServerCurrentVersion())) {
            builder.append(order.getScaleInitialLevelSize());
            builder.append(order.getScaleSubsequentLevelSize());
        } else {
            builder.append("");
            builder.append(order.getScaleInitialLevelSize());
        }
        builder.append(order.getScalePriceIncrement());
        if (Feature.SCALE_ORDERS.isSupportedByVersion(getServerCurrentVersion())
                && (order.getScalePriceIncrement() > 0) && (order.getScalePriceIncrement() != Double.MAX_VALUE)) {
            builder.append(order.getScalePriceAdjustValue());
            builder.append(order.getScalePriceAdjustInterval());
            builder.append(order.getScaleProfitOffset());
            builder.append(order.isScaleAutoReset());
            builder.append(order.getScaleInitPosition());
            builder.append(order.getScaleInitFillQuantity());
            builder.append(order.isScaleRandomPercent());
        }
        if (Feature.HEDGING_ORDER.isSupportedByVersion(getServerCurrentVersion())) {
            builder.append(order.getHedgeType().getInitial());
            if (StringUtils.isNotEmpty(order.getHedgeType().getInitial())) {
                builder.append(order.getHedgeParameter());
            }
        }
        if (Feature.OPT_OUT_DEFAULT_SMART_ROUTING_ASX_ORDER.isSupportedByVersion(getServerCurrentVersion())) {
            builder.append(order.isOptOutSmartRouting());
        }
        if (Feature.POST_TRADE_ALLOCATION.isSupportedByVersion(getServerCurrentVersion())) {
            builder.append(order.getClearingAccount());
            builder.append(order.getClearingIntent());
        }
        if (Feature.NOT_HELD.isSupportedByVersion(getServerCurrentVersion())) {
            builder.append(order.isNotHeld());
        }
        if (Feature.DELTA_NEUTRAL_COMBO_ORDER.isSupportedByVersion(getServerCurrentVersion())) {
            final UnderlyingCombo underlyingCombo = contract.getUnderlyingCombo();
            if (underlyingCombo != null) {
                builder.append(true);
                builder.append(underlyingCombo.getContractId());
                builder.append(underlyingCombo.getDelta());
                builder.append(underlyingCombo.getPrice());
            } else {
                builder.append(false);
            }
        }
        if (Feature.ALGORITHM_ORDER.isSupportedByVersion(getServerCurrentVersion())) {
            builder.append(order.getAlgorithmStrategy());
            if (StringUtils.isNotEmpty(order.getAlgorithmStrategy())) {
                builder.append(order.getAlgorithmParameters().size());
                for (final PairTagValue pairTagValue : order.getAlgorithmParameters()) {
                    builder.append(pairTagValue.getTagName());
                    builder.append(pairTagValue.getValue());
                }
            }
        }
        builder.append(order.isRequestPreTradeInformation());
    }

    private void appendComboLegs(final RequestBuilder builder) {
        if (SecurityType.COMBO.equals(contract.getSecurityType())) {
            builder.append(contract.getComboLegs().size());
            for (final ComboLeg comboLeg : contract.getComboLegs()) {
                builder.append(comboLeg.getContractId());
                builder.append(comboLeg.getRatio());
                builder.append(comboLeg.getOrderAction().getAbbreviation());
                builder.append(comboLeg.getExchange());
                builder.append(comboLeg.getOpenClose().getValue());
                builder.append(comboLeg.getShortSaleSlotValue().getValue());
                builder.append(comboLeg.getDesignatedLocation());
                if (Feature.SHORT_SALE_EXEMPT_ORDER_OLD.isSupportedByVersion(getServerCurrentVersion())) {
                    builder.append(comboLeg.getExemptionCode());
                }
            }

            if (Feature.ORDER_COMBO_LEGS_PRICE.isSupportedByVersion(getServerCurrentVersion())) {
                builder.append(order.getOrderComboLegs().size());
                for (final OrderComboLeg orderComboLeg : order.getOrderComboLegs()) {
                    builder.append(orderComboLeg.getPrice());
                }
            }

            if (Feature.SMART_COMBO_ROUTING_PARAMETER.isSupportedByVersion(getServerCurrentVersion())) {
                builder.append(order.getSmartComboRoutingParameters().size());
                for (final PairTagValue pairTagValue : order.getSmartComboRoutingParameters()) {
                    builder.append(pairTagValue.getTagName());
                    builder.append(pairTagValue.getValue());
                }
            }
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(contract).append(order).toHashCode();
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
        final PlaceOrderRequest rhs = (PlaceOrderRequest) obj;
        return new EqualsBuilder().append(contract, rhs.contract).append(order, rhs.order).isEquals();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
