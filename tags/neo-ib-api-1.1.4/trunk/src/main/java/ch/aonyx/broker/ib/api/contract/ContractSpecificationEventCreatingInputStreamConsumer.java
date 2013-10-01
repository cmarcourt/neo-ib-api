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

import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readDouble;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readInt;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readString;

import java.io.InputStream;

import ch.aonyx.broker.ib.api.io.AbstractEventCreatingInputStreamConsumerSupport;
import ch.aonyx.broker.ib.api.order.PairTagValue;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class ContractSpecificationEventCreatingInputStreamConsumer extends
        AbstractEventCreatingInputStreamConsumerSupport<ContractSpecificationEvent> {

    public ContractSpecificationEventCreatingInputStreamConsumer(final InputStream inputStream,
            final int serverCurrentVersion) {
        super(inputStream, serverCurrentVersion);
    }

    @Override
    protected ContractSpecificationEvent consumeVersionLess(final InputStream inputStream) {
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
        contract.setExpiry(readString(inputStream));
        contract.setStrike(readDouble(inputStream));
        contract.setOptionRight(OptionRight.fromInitialOrName(readString(inputStream)));
        contract.setExchange(readString(inputStream));
        contract.setCurrencyCode(readString(inputStream));
        contract.setLocalSymbol(readString(inputStream));
        contractSpecification.setMarketName(readString(inputStream));
        contractSpecification.setTradingClass(readString(inputStream));
        contract.setId(readInt(inputStream));
        contractSpecification.setMinimumFluctuation(readDouble(inputStream));
        contract.setMultiplier(readString(inputStream));
        contractSpecification.setValidOrderTypes(readString(inputStream));
        contractSpecification.setValidExchanges(readString(inputStream));
        if (getVersion() >= 2) {
            contractSpecification.setPriceMagnifier(readInt(inputStream));
        }
        if (getVersion() >= 4) {
            contractSpecification.setUnderlyingContractId(readInt(inputStream));
        }
        if (getVersion() >= 5) {
            contractSpecification.setLongName(readString(inputStream));
            contract.setPrimaryExchange(readString(inputStream));
        }
        if (getVersion() >= 6) {
            contractSpecification.setContractMonth(readString(inputStream));
            contractSpecification.setIndustry(readString(inputStream));
            contractSpecification.setCategory(readString(inputStream));
            contractSpecification.setSubcategory(readString(inputStream));
            contractSpecification.setTimeZoneId(readString(inputStream));
            contractSpecification.setTradingHours(readString(inputStream));
            contractSpecification.setLiquidHours(readString(inputStream));
        }
        if (getVersion() >= 8) {
            contractSpecification.setEconomicValueRule(readString(inputStream));
            contractSpecification.setEconomicValueMultiplier(readDouble(inputStream));
        }
        if (getVersion() >= 7) {
            final int securityIdsCount = readInt(inputStream);
            for (int i = 0; i < securityIdsCount; i++) {
                final PairTagValue pairTagValue = new PairTagValue();
                contractSpecification.getSecurityIds().add(pairTagValue);
                pairTagValue.setTagName(readString(inputStream));
                pairTagValue.setValue(readString(inputStream));
            }
        }
        return contractSpecification;
    }

    private ContractSpecificationEvent createEvent(final int requestId,
            final ContractSpecification contractSpecification) {
        return new ContractSpecificationEvent(toRequestId(requestId), contractSpecification);
    }

}
