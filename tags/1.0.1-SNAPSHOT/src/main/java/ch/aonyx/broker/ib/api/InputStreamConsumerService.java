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
package ch.aonyx.broker.ib.api;

import java.io.InputStream;
import java.util.Map;

import ch.aonyx.broker.ib.api.account.AccountUpdateTimeEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.account.AccountUpdateValueEndEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.account.AccountUpdateValueEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.account.ManagedAccountListEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.account.PortfolioUpdateEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.bulletin.NewsBulletinUpdateEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.contract.BondContractSpecificationEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.contract.ContractSpecificationEndEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.contract.ContractSpecificationEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.contract.DeltaNeutralValidationEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.data.CompositeTickEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.data.MarketDataTypeEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.data.TickEfpEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.data.TickGenericEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.data.TickOptionComputationEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.data.TickSizeEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.data.TickSnapshotEndEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.data.TickStringEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.data.bar.RealTimeBarEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.data.depth.MarketDepthLevelTwoUpdateEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.data.depth.MarketDepthUpdateEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.data.fundamental.FundamentalDataEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.data.historical.HistoricalDataEventListEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.data.scanner.MarketScannerDataEventListEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.data.scanner.MarketScannerValidParametersEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.execution.ExecutionReportEndEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.execution.ExecutionReportEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.fa.FinancialAdvisorConfigurationEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.io.EventCreatingConsumer;
import ch.aonyx.broker.ib.api.order.NextValidOrderIdEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.order.OrderStateUpdateEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.order.RetrieveOpenOrderEndEventCreatingInputStreamConcumer;
import ch.aonyx.broker.ib.api.order.RetrieveOpenOrderEventCreatingInputStreamConsumer;
import ch.aonyx.broker.ib.api.system.ServerCurrentTimeEventCreatingInputStreamConsumer;

import com.google.common.collect.Maps;

/**
 * @author Christophe Marcourt
 * @version 1.0.0
 */
final class InputStreamConsumerService {

	private final Map<IncomingMessageId, EventCreatingConsumer<? extends Event>> consumerCache = Maps.newHashMap();
	private final InputStream inputStream;
	private int serverCurrentVersion;

	InputStreamConsumerService(final InputStream inputStream) {
		this.inputStream = inputStream;
	}

	final void setServerCurrentVersion(final int serverCurrentVersion) {
		this.serverCurrentVersion = serverCurrentVersion;
	}

	<E extends Event> EventCreatingConsumer<E> getEventCreatingConsumer(final IncomingMessageId messageId) {
		return getEventCreatingConsumerFromCache(messageId);
	}

	@SuppressWarnings("unchecked")
	private <E extends Event> EventCreatingConsumer<E> getEventCreatingConsumerFromCache(
			final IncomingMessageId messageId) {
		if (consumerCache.containsKey(messageId)) {
			return (EventCreatingConsumer<E>) consumerCache.get(messageId);
		}
		return newEventCreatingConsumer(messageId);
	}

	@SuppressWarnings("unchecked")
	private <E extends Event> EventCreatingConsumer<E> newEventCreatingConsumer(final IncomingMessageId messageId) {
		EventCreatingConsumer<? extends Event> consumer = null;
		switch (messageId) {
		case TICK_PRICE:
			consumer = new CompositeTickEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		case TICK_SIZE:
			consumer = new TickSizeEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		case ORDER_STATE_UPDATE:
			consumer = new OrderStateUpdateEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		case SERVER_MESSAGE:
			consumer = new ServerMessageEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		case RETRIEVE_OPEN_ORDER:
			consumer = new RetrieveOpenOrderEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		case ACCOUNT_UPDATE_VALUE:
			consumer = new AccountUpdateValueEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		case PORTFOLIO_UPDATE:
			consumer = new PortfolioUpdateEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		case ACCOUNT_UPDATE_TIME:
			consumer = new AccountUpdateTimeEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		case NEXT_VALID_ORDER_ID:
			consumer = new NextValidOrderIdEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		case CONTRACT_SPECIFICATION:
			consumer = new ContractSpecificationEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		case EXECUTION_REPORT:
			consumer = new ExecutionReportEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		case MARKET_DEPTH_UPDATE:
			consumer = new MarketDepthUpdateEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		case MARKET_DEPTH_LEVEL_TWO_UPDATE:
			consumer = new MarketDepthLevelTwoUpdateEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		case NEWS_BULLETIN_UPDATE:
			consumer = new NewsBulletinUpdateEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		case MANAGED_ACCOUNT_LIST:
			consumer = new ManagedAccountListEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		case FINANCIAL_ADVISOR_CONFIGURATION:
			consumer = new FinancialAdvisorConfigurationEventCreatingInputStreamConsumer(inputStream,
					serverCurrentVersion);
			break;

		case HISTORICAL_DATA:
			consumer = new HistoricalDataEventListEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		case BOND_CONTRACT_SPECIFICATION:
			consumer = new BondContractSpecificationEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		case MARKET_SCANNER_VALID_PARAMETERS:
			consumer = new MarketScannerValidParametersEventCreatingInputStreamConsumer(inputStream,
					serverCurrentVersion);
			break;

		case MARKET_SCANNER_DATA:
			consumer = new MarketScannerDataEventListEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		case TICK_OPTION_COMPUTATION:
			consumer = new TickOptionComputationEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		case TICK_GENERIC:
			consumer = new TickGenericEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		case TICK_STRING:
			consumer = new TickStringEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		case TICK_EXCHANFE_FOR_PHYSICAL:
			consumer = new TickEfpEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		case SERVER_CURRENT_TIME:
			consumer = new ServerCurrentTimeEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		case REAL_TIME_BAR:
			consumer = new RealTimeBarEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		case FUNDAMENTAL_DATA:
			consumer = new FundamentalDataEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		case CONTRACT_SPECIFICATION_END:
			consumer = new ContractSpecificationEndEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		case RETRIEVE_OPEN_ORDER_END:
			consumer = new RetrieveOpenOrderEndEventCreatingInputStreamConcumer(inputStream, serverCurrentVersion);
			break;

		case ACCOUNT_UPDATE_VALUE_END:
			consumer = new AccountUpdateValueEndEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		case EXECUTION_REPORT_END:
			consumer = new ExecutionReportEndEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		case DELTA_NEUTRAL_VALIDATION:
			consumer = new DeltaNeutralValidationEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		case TICK_SNAPSHOT_END:
			consumer = new TickSnapshotEndEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		case MARKET_DATA_TYPE:
			consumer = new MarketDataTypeEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
			break;

		default:
			consumer = new EmptyEventCreatingInputStreamConsumer(inputStream, serverCurrentVersion);
		}
		consumerCache.put(messageId, consumer);
		return (EventCreatingConsumer<E>) consumer;
	}

}
