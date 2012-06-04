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
package ch.aonyx.broker.ib.api.account;

import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readDouble;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readInt;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readString;

import java.io.InputStream;

import ch.aonyx.broker.ib.api.contract.Contract;
import ch.aonyx.broker.ib.api.contract.OptionRight;
import ch.aonyx.broker.ib.api.contract.SecurityType;
import ch.aonyx.broker.ib.api.io.AbstractEventCreatingInputStreamConsumerSupport;

/**
 * @author Christophe Marcourt
 * @version 1.0.0
 */
public final class PortfolioUpdateEventCreatingInputStreamConsumer extends
		AbstractEventCreatingInputStreamConsumerSupport<PortfolioUpdateEvent> {

	public PortfolioUpdateEventCreatingInputStreamConsumer(final InputStream inputStream, final int serverCurrentVersion) {
		super(inputStream, serverCurrentVersion);
	}

	@Override
	protected PortfolioUpdateEvent consumeVersionLess(final InputStream inputStream) {
		final Contract contract = consumeContract(inputStream);
		final int position = readInt(inputStream);
		final double marketPrice = readDouble(inputStream);
		final double marketValue = readDouble(inputStream);
		double averageCost = 0;
		double unrealizedProfitAndLoss = 0;
		double realizedProfitAndLoss = 0;
		if (getVersion() >= 3) {
			averageCost = readDouble(inputStream);
			unrealizedProfitAndLoss = readDouble(inputStream);
			realizedProfitAndLoss = readDouble(inputStream);
		}
		String accountName = null;
		if (getVersion() >= 4) {
			accountName = readString(inputStream);
		}
		if ((getVersion() == 6) && (getServerCurrentVersion() == 39)) {
			contract.setPrimaryExchange(readString(inputStream));
		}
		return createEvent(contract, position, marketPrice, marketValue, averageCost, unrealizedProfitAndLoss,
				realizedProfitAndLoss, accountName);
	}

	private PortfolioUpdateEvent createEvent(final Contract contract, final int position, final double marketPrice,
			final double marketValue, final double averageCost, final double unrealizedProfitAndLoss,
			final double realizedProfitAndLoss, final String accountName) {
		return new PortfolioUpdateEvent(contract, position, marketPrice, marketValue, averageCost,
				unrealizedProfitAndLoss, realizedProfitAndLoss, accountName);
	}

	private Contract consumeContract(final InputStream inputStream) {
		final Contract contract = new Contract();
		if (getVersion() >= 6) {
			contract.setId(readInt(inputStream));
		}
		contract.setSymbol(readString(inputStream));
		contract.setSecurityType(SecurityType.fromAbbreviation(readString(inputStream)));
		contract.setExpiry(readString(inputStream));
		contract.setStrike(readDouble(inputStream));
		contract.setOptionRight(OptionRight.fromInitialOrName(readString(inputStream)));
		if (getVersion() >= 7) {
			contract.setMultiplier(readString(inputStream));
			contract.setPrimaryExchange(readString(inputStream));
		}
		contract.setCurrencyCode(readString(inputStream));
		if (getVersion() >= 2) {
			contract.setLocalSymbol(readString(inputStream));
		}
		return contract;
	}

}
