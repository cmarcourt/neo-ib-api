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

import ch.aonyx.broker.ib.api.io.AbstractEventCreatingInputStreamConsumerSupport;

/**
 * @author Christophe Marcourt
 * @version 1.1.0
 */
public final class CommissionReportEventCreatingInputStreamConsumer extends
        AbstractEventCreatingInputStreamConsumerSupport<CommissionReportEvent> {

    public CommissionReportEventCreatingInputStreamConsumer(final InputStream inputStream,
            final int serverCurrentVersion) {
        super(inputStream, serverCurrentVersion);
    }

    @Override
    protected CommissionReportEvent consumeVersionLess(final InputStream inputStream) {
        final CommissionReport commissionReport = new CommissionReport();
        commissionReport.setExecutionId(readString(inputStream));
        commissionReport.setCommission(readDouble(inputStream));
        commissionReport.setCurrencyCode(readString(inputStream));
        commissionReport.setRealizedProfitAndLoss(readDouble(inputStream));
        commissionReport.setYield(readDouble(inputStream));
        commissionReport.setYieldRedemptionDate(readInt(inputStream));
        return createEvent(commissionReport);
    }

    private CommissionReportEvent createEvent(final CommissionReport commissionReport) {
        return new CommissionReportEvent(commissionReport);
    }

}
