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

import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readBoolean;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readDouble;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readInt;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readString;

import java.io.InputStream;

import ch.aonyx.broker.ib.api.io.AbstractEventCreatingInputStreamConsumerSupport;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class BondContractSpecificationEventCreatingInputStreamConsumer extends
		AbstractEventCreatingInputStreamConsumerSupport<BondContractSpecificationEvent> {

	public BondContractSpecificationEventCreatingInputStreamConsumer(final InputStream inputStream,
			final int serverCurrentVersion) {
		super(inputStream, serverCurrentVersion);
	}

	@Override
	protected BondContractSpecificationEvent consumeVersionLess(final InputStream inputStream) {
		int requestId = -1;
		if (getVersion() >= 3) {
			requestId = readInt(inputStream);
		}
		final ContractSpecification contractSpecification = consumerContractSpecification(inputStream);
		return createEvent(requestId, contractSpecification);
	}

	private ContractSpecification consumerContractSpecification(final InputStream inputStream) {
		final ContractSpecification contractSpecification = new ContractSpecification();
		final Contract contract = new Contract();
		contractSpecification.setContract(contract);
		contract.setSymbol(readString(inputStream));
		contract.setSecurityType(SecurityType.fromAbbreviation(readString(inputStream)));
		contractSpecification.setCusip(readString(inputStream));
		contractSpecification.setCoupon(readDouble(inputStream));
		contractSpecification.setMaturity(readString(inputStream));
		contractSpecification.setIssueDate(readString(inputStream));
		contractSpecification.setRatings(readString(inputStream));
		contractSpecification.setBondType(readString(inputStream));
		contractSpecification.setCouponType(readString(inputStream));
		contractSpecification.setConvertible(readBoolean(inputStream));
		contractSpecification.setCallable(readBoolean(inputStream));
		contractSpecification.setPutable(readBoolean(inputStream));
		contractSpecification.setDescription(readString(inputStream));
		contract.setExchange(readString(inputStream));
		contract.setCurrencyCode(readString(inputStream));
		contractSpecification.setMarketName(readString(inputStream));
		contractSpecification.setTradingClass(readString(inputStream));
		contract.setId(readInt(inputStream));
		contractSpecification.setMinimumFluctuation(readDouble(inputStream));
		contractSpecification.setValidOrderTypes(readString(inputStream));
		contractSpecification.setValidExchanges(readString(inputStream));
		if (getVersion() >= 2) {
			contractSpecification.setNextOptionDate(readString(inputStream));
			contractSpecification.setNextOptionType(readString(inputStream));
			contractSpecification.setNextOptionPartial(readBoolean(inputStream));
			contractSpecification.setNotes(readString(inputStream));
		}
		if (getVersion() >= 4) {
			contractSpecification.setLongName(readString(inputStream));
		}
		return contractSpecification;
	}

	private BondContractSpecificationEvent createEvent(final int requestId,
			final ContractSpecification contractSpecification) {
		return new BondContractSpecificationEvent(toRequestId(requestId), contractSpecification);
	}
}
