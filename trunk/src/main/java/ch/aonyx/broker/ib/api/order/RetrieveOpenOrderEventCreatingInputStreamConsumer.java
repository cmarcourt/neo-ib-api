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

import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readBoolean;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readDouble;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readDoubleMax;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readInt;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readIntMax;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readString;

import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;

import ch.aonyx.broker.ib.api.contract.Contract;
import ch.aonyx.broker.ib.api.contract.OptionRight;
import ch.aonyx.broker.ib.api.contract.SecurityType;
import ch.aonyx.broker.ib.api.contract.UnderlyingCombo;
import ch.aonyx.broker.ib.api.io.AbstractEventCreatingInputStreamConsumerSupport;

/**
 * @author Christophe Marcourt
 * @version 1.0.0
 */
public final class RetrieveOpenOrderEventCreatingInputStreamConsumer extends
		AbstractEventCreatingInputStreamConsumerSupport<RetrieveOpenOrderEvent> {

	public RetrieveOpenOrderEventCreatingInputStreamConsumer(final InputStream inputStream,
			final int serverCurrentVersion) {
		super(inputStream, serverCurrentVersion);
	}

	@Override
	protected RetrieveOpenOrderEvent consumeVersionLess(final InputStream inputStream) {
		final int orderId = readInt(inputStream);
		final Contract contract = consumeContract(inputStream);
		final Order order = consumerOrder(orderId, contract, inputStream);
		final OrderExecution orderExecution = consumeOrderExecution(inputStream);
		return createEvent(orderId, contract, order, orderExecution);
	}

	private Contract consumeContract(final InputStream inputStream) {
		final Contract contract = new Contract();
		if (getVersion() >= 17) {
			contract.setId(readInt(inputStream));
		}
		contract.setSymbol(readString(inputStream));
		contract.setSecurityType(SecurityType.fromAbbreviation(readString(inputStream)));
		contract.setExpiry(readString(inputStream));
		contract.setStrike(readDouble(inputStream));
		contract.setOptionRight(OptionRight.fromInitialOrName(readString(inputStream)));
		contract.setExchange(readString(inputStream));
		contract.setCurrencyCode(readString(inputStream));
		if (getVersion() >= 2) {
			contract.setLocalSymbol(readString(inputStream));
		}
		return contract;
	}

	private Order consumerOrder(final int id, final Contract contract, final InputStream inputStream) {
		final Order order = new Order();
		order.setId(toOrderId(id));
		order.setAction(OrderAction.fromAbbreviation(readString(inputStream)));
		order.setTotalQuantity(readInt(inputStream));
		order.setOrderType(OrderType.fromAbbreviation(readString(inputStream)));
		order.setLimitPrice(readDouble(inputStream));
		order.setStopPrice(readDouble(inputStream));
		order.setTimeInForce(TimeInForce.fromAbbreviation(readString(inputStream)));
		order.setOcaGroupName(readString(inputStream));
		order.setAccountName(readString(inputStream));
		order.setOpenClose(OpenCloseInstitutional.fromInitial(readString(inputStream)));
		order.setOrigin(OriginInstitutional.fromValue(readInt(inputStream)));
		order.setOrderReference(readString(inputStream));
		if (getVersion() >= 3) {
			order.setClientId(readInt(inputStream));
		}
		if (getVersion() >= 4) {
			order.setPermanentId(readInt(inputStream));
			if (getVersion() < 18) {
				readBoolean(inputStream);
			} else {
				order.setOutsideRegularTradingHours(readBoolean(inputStream));
			}
			order.setHidden(readBoolean(inputStream));
			order.setDiscretionaryAmount(readDouble(inputStream));
		}
		if (getVersion() >= 5) {
			order.setGoodAfterDateTime(readString(inputStream));
		}
		if (getVersion() >= 6) {
			readString(inputStream);
		}
		if (getVersion() >= 7) {
			order.setFinancialAdvisorGroup(readString(inputStream));
			order.setFinancialAdvisorMethod(readString(inputStream));
			order.setFinancialAdvisorPercentage(readString(inputStream));
			order.setFinancialAdvisorProfile(readString(inputStream));
		}
		if (getVersion() >= 8) {
			order.setGoodTillDateTime(readString(inputStream));
		}
		if (getVersion() >= 9) {
			order.setRule80A(Rule80A.fromInitial(readString(inputStream)));
			order.setPercentageOffset(readDouble(inputStream));
			order.setSettlingFirm(readString(inputStream));
			order.setShortSaleSlot(ShortSaleSlotInstitutional.fromValue(readInt(inputStream)));
			order.setDesignatedLocation(readString(inputStream));
			if (getServerCurrentVersion() == 51) {
				readInt(inputStream);
			} else if (getVersion() >= 23) {
				order.setExemptionCode(readInt(inputStream));
			}
			order.setAuctionStrategy(AuctionStrategy.fromValue(readInt(inputStream)));
			order.setStartingPrice(readDouble(inputStream));
			order.setStockReferencePrice(readDouble(inputStream));
			order.setDelta(readDouble(inputStream));
			order.setLowerStockPriceRange(readDouble(inputStream));
			order.setUpperStockPriceRange(readDouble(inputStream));
			order.setDisplaySize(readInt(inputStream));
			if (getVersion() < 18) {
				readBoolean(inputStream);
			}
			order.setBlockOrder(readBoolean(inputStream));
			order.setSweepToFill(readBoolean(inputStream));
			order.setAllOrNone(readBoolean(inputStream));
			order.setMinimumQuantity(readInt(inputStream));
			order.setOcaType(OcaType.fromValue(readInt(inputStream)));
			order.setElectronicTradeOnly(readBoolean(inputStream));
			order.setFirmQuoteOnly(readBoolean(inputStream));
			order.setNbboPriceCap(readDouble(inputStream));
		}
		if (getVersion() >= 10) {
			order.setParentId(toOrderId(readInt(inputStream)));
			order.setStopTriggerMethod(StopTriggerMethod.fromValue(readInt(inputStream)));
		}
		if (getVersion() >= 11) {
			order.setVolatility(readDouble(inputStream));
			order.setVolatilityType(VolatilityType.fromValue(readInt(inputStream)));
			if (getVersion() == 11) {
				order.setDeltaNeutralOrderType(readInt(inputStream) == 0 ? "NONE" : "MKT");
			} else {
				order.setDeltaNeutralOrderType(readString(inputStream));
				order.setDeltaNeutralAuxPrice(readDouble(inputStream));
				if ((getVersion() >= 27) && StringUtils.isNotEmpty(order.getDeltaNeutralOrderType())) {
					order.setDeltaNeutralContractId(readInt(inputStream));
					order.setDeltaNeutralSettlingFirm(readString(inputStream));
					order.setDeltaNeutralClearingAccount(readString(inputStream));
					order.setDeltaNeutralClearingIntent(readString(inputStream));
				}
			}
			order.setContinuouslyUpdate(readInt(inputStream));
			if (getServerCurrentVersion() == 26) {
				order.setLowerStockPriceRange(readDouble(inputStream));
				order.setUpperStockPriceRange(readDouble(inputStream));
			}
			order.setReferencePriceType(ReferencePriceType.fromValue(readInt(inputStream)));
		}
		if (getVersion() >= 13) {
			order.setTrailingStopPrice(readDouble(inputStream));
		}
		if (getVersion() >= 14) {
			order.setBasisPoint(readDouble(inputStream));
			order.setBasisPointType(readInt(inputStream));
			contract.setComboLegsDescription(readString(inputStream));
		}
		if (getVersion() >= 26) {
			final int smartComboRoutingParametersCount = readInt(inputStream);
			for (int i = 0; i < smartComboRoutingParametersCount; i++) {
				final PairTagValue pairTagValue = new PairTagValue();
				order.getSmartComboRoutingParameters().add(pairTagValue);
				pairTagValue.setTagName(readString(inputStream));
				pairTagValue.setValue(readString(inputStream));
			}
		}
		if (getVersion() >= 15) {
			if (getVersion() >= 20) {
				order.setScaleInitialLevelSize(readIntMax(inputStream));
				order.setScaleSubsequentLevelSize(readIntMax(inputStream));
			} else {
				readInt(inputStream);
				order.setScaleInitialLevelSize(readIntMax(inputStream));
			}
			order.setScalePriceIncrement(readDoubleMax(inputStream));
		}
		if (getVersion() >= 24) {
			order.setHedgeType(HedgeType.fromInitial(readString(inputStream)));
			if (StringUtils.isNotEmpty(order.getHedgeType().getInitial())) {
				order.setHedgeParameter(readString(inputStream));
			}
		}
		if (getVersion() >= 25) {
			order.setOptOutSmartRouting(readBoolean(inputStream));
		}
		if (getVersion() >= 19) {
			order.setClearingAccount(readString(inputStream));
			order.setClearingIntent(readString(inputStream));
		}
		if (getVersion() >= 22) {
			order.setNotHeld(readBoolean(inputStream));
		}
		if (getVersion() >= 20) {
			if (readBoolean(inputStream)) {
				final UnderlyingCombo underlyingCombo = new UnderlyingCombo();
				contract.setUnderlyingCombo(underlyingCombo);
				underlyingCombo.setContractId(readInt(inputStream));
				underlyingCombo.setDelta(readDouble(inputStream));
				underlyingCombo.setPrice(readDouble(inputStream));
			}
		}
		if (getVersion() >= 21) {
			order.setAlgorithmStrategy(readString(inputStream));
			if (StringUtils.isNotEmpty(order.getAlgorithmStrategy())) {
				final int algorithmParametersCount = readInt(inputStream);
				for (int i = 0; i < algorithmParametersCount; i++) {
					final PairTagValue pairTagValue = new PairTagValue();
					order.getAlgorithmParameters().add(pairTagValue);
					pairTagValue.setTagName(readString(inputStream));
					pairTagValue.setValue(readString(inputStream));
				}
			}
		}
		if (getVersion() >= 16) {
			order.setRequestPreTradeInformation(readBoolean(inputStream));
		}
		return order;
	}

	private OrderExecution consumeOrderExecution(final InputStream inputStream) {
		final OrderExecution execution = new OrderExecution();
		if (getVersion() >= 16) {
			execution.setOrderStatus(OrderStatus.fromLabel(readString(inputStream)));
			execution.setInitialMargin(readString(inputStream));
			execution.setMaintenanceMargin(readString(inputStream));
			execution.setEquityWithLoan(readString(inputStream));
			execution.setCommission(readDoubleMax(inputStream));
			execution.setMinCommission(readDoubleMax(inputStream));
			execution.setMaxCommission(readDoubleMax(inputStream));
			execution.setCommissionCurrencyCode(readString(inputStream));
			execution.setWarningText(readString(inputStream));
		}
		return execution;
	}

	private RetrieveOpenOrderEvent createEvent(final int requestId, final Contract contract, final Order order,
			final OrderExecution orderExecution) {
		return new RetrieveOpenOrderEvent(toOrderId(requestId), contract, order, orderExecution);
	}
}
