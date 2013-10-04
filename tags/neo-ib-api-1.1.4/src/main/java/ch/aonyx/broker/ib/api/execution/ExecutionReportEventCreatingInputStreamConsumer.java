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
package ch.aonyx.broker.ib.api.execution;

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
 * @since 1.0.0
 */
public final class ExecutionReportEventCreatingInputStreamConsumer extends
        AbstractEventCreatingInputStreamConsumerSupport<ExecutionReportEvent> {

    public ExecutionReportEventCreatingInputStreamConsumer(final InputStream inputStream, final int serverCurrentVersion) {
        super(inputStream, serverCurrentVersion);
    }

    @Override
    protected ExecutionReportEvent consumeVersionLess(final InputStream inputStream) {
        int requestId = -1;
        if (getVersion() >= 7) {
            requestId = readInt(inputStream);
        }
        final int orderId = readInt(inputStream);
        final Contract contract = consumeContract(inputStream);
        final ExecutionReport executionReport = consumeExecutionReport(inputStream, orderId);
        return createEvent(requestId, contract, executionReport);
    }

    private Contract consumeContract(final InputStream inputStream) {
        final Contract contract = new Contract();
        if (getVersion() >= 5) {
            contract.setId(readInt(inputStream));
        }
        contract.setSymbol(readString(inputStream));
        contract.setSecurityType(SecurityType.fromAbbreviation(readString(inputStream)));
        contract.setExpiry(readString(inputStream));
        contract.setStrike(readDouble(inputStream));
        contract.setOptionRight(OptionRight.fromInitialOrName(readString(inputStream)));
        if (getVersion() >= 9) {
            contract.setMultiplier(readString(inputStream));
        }
        contract.setExchange(readString(inputStream));
        contract.setCurrencyCode(readString(inputStream));
        contract.setLocalSymbol(readString(inputStream));
        return contract;
    }

    private ExecutionReport consumeExecutionReport(final InputStream inputStream, final int orderId) {
        final ExecutionReport executionReport = new ExecutionReport();
        executionReport.setOrderId(orderId);
        executionReport.setExecutionId(readString(inputStream));
        executionReport.setTime(readString(inputStream));
        executionReport.setAccountNumber(readString(inputStream));
        executionReport.setExchange(readString(inputStream));
        executionReport.setSide(Side.fromAcronym(readString(inputStream)));
        executionReport.setFilledQuantity(readInt(inputStream));
        executionReport.setFilledPrice(readDouble(inputStream));
        if (getVersion() >= 2) {
            executionReport.setPermanentId(readInt(inputStream));
        }
        if (getVersion() >= 3) {
            executionReport.setClientId(readInt(inputStream));
        }
        if (getVersion() >= 4) {
            executionReport.setLiquidation(readInt(inputStream));
        }
        if (getVersion() >= 6) {
            executionReport.setCumulativeQuantity(readInt(inputStream));
            executionReport.setAverageFilledPrice(readDouble(inputStream));
        }
        if (getVersion() >= 8) {
            executionReport.setOrderRef(readString(inputStream));
        }
        if (getVersion() >= 9) {
            executionReport.setEconomicValueRule(readString(inputStream));
            executionReport.setEconomicValueMultiplier(readDouble(inputStream));
        }
        return executionReport;
    }

    private ExecutionReportEvent createEvent(final int requestId, final Contract contract,
            final ExecutionReport executionReport) {
        return new ExecutionReportEvent(toRequestId(requestId), contract, executionReport);
    }
}
