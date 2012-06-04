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
package ch.aonyx.broker.ib.api.data.scanner;

import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readDouble;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readInt;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readString;

import java.io.InputStream;
import java.util.List;

import ch.aonyx.broker.ib.api.contract.Contract;
import ch.aonyx.broker.ib.api.contract.ContractSpecification;
import ch.aonyx.broker.ib.api.contract.OptionRight;
import ch.aonyx.broker.ib.api.contract.SecurityType;
import ch.aonyx.broker.ib.api.io.AbstractEventCreatingInputStreamConsumerSupport;

import com.google.common.collect.Lists;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class MarketScannerDataEventListEventCreatingInputStreamConsumer extends
		AbstractEventCreatingInputStreamConsumerSupport<MarketScannerDataEventListEvent> {

	public MarketScannerDataEventListEventCreatingInputStreamConsumer(final InputStream inputStream,
			final int serverCurrentVersion) {
		super(inputStream, serverCurrentVersion);
	}

	@Override
	protected MarketScannerDataEventListEvent consumeVersionLess(final InputStream inputStream) {
		final int requestId = readInt(inputStream);
		final List<MarketScannerDataEvent> marketScannerDataEvents = consumeMarketScannerDataEvents(requestId,
				inputStream);
		return createEvent(requestId, marketScannerDataEvents);
	}

	private List<MarketScannerDataEvent> consumeMarketScannerDataEvents(final int requestId,
			final InputStream inputStream) {
		final List<MarketScannerDataEvent> marketScannerDataEvents = Lists.newArrayList();
		final int marketScannerDatas = readInt(inputStream);
		for (int i = 0; i < marketScannerDatas; i++) {
			marketScannerDataEvents.add(consumeMarketScannerDataEvent(requestId, inputStream));
		}
		return marketScannerDataEvents;
	}

	private MarketScannerDataEvent consumeMarketScannerDataEvent(final int requestId, final InputStream inputStream) {
		final Contract contract = new Contract();
		final ContractSpecification contractSpecification = new ContractSpecification();
		contractSpecification.setContract(contract);
		final int ranking = readInt(inputStream);
		if (getVersion() >= 3) {
			contract.setId(readInt(inputStream));
		}
		contract.setSymbol(readString(inputStream));
		contract.setSecurityType(SecurityType.fromAbbreviation(readString(inputStream)));
		contract.setExpiry(readString(inputStream));
		contract.setStrike(readDouble(inputStream));
		contract.setOptionRight(OptionRight.fromInitialOrName(readString(inputStream)));
		contract.setExchange(readString(inputStream));
		contract.setCurrencyCode(readString(inputStream));
		contract.setLocalSymbol(readString(inputStream));
		contractSpecification.setMarketName(readString(inputStream));
		contractSpecification.setTradingClass(readString(inputStream));
		final String distance = readString(inputStream);
		final String benchmark = readString(inputStream);
		final String projection = readString(inputStream);
		String comboLegDescription = null;
		if (getVersion() >= 2) {
			comboLegDescription = readString(inputStream);
		}
		return createMarketScannerDataEvent(requestId, contractSpecification, ranking, distance, benchmark, projection,
				comboLegDescription);
	}

	private MarketScannerDataEvent createMarketScannerDataEvent(final int requestId,
			final ContractSpecification contractSpecification, final int ranking, final String distance,
			final String benchmark, final String projection, final String comboLegDescription) {
		return new MarketScannerDataEvent(toRequestId(requestId), ranking, contractSpecification, distance, benchmark,
				projection, comboLegDescription);
	}

	private MarketScannerDataEventListEvent createEvent(final int requestId,
			final List<MarketScannerDataEvent> marketScannerDataEvents) {
		return new MarketScannerDataEventListEvent(toRequestId(requestId), marketScannerDataEvents);
	}
}
