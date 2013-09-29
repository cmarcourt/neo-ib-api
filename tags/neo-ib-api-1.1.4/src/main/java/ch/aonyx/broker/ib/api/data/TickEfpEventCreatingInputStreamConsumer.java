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
package ch.aonyx.broker.ib.api.data;

import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readDouble;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readInt;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readString;

import java.io.InputStream;

import ch.aonyx.broker.ib.api.io.AbstractEventCreatingInputStreamConsumerSupport;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class TickEfpEventCreatingInputStreamConsumer extends
        AbstractEventCreatingInputStreamConsumerSupport<TickEfpEvent> {

    public TickEfpEventCreatingInputStreamConsumer(final InputStream inputStream, final int serverCurrentVersion) {
        super(inputStream, serverCurrentVersion);
    }

    @Override
    protected TickEfpEvent consumeVersionLess(final InputStream inputStream) {
        final int requestId = readInt(inputStream);
        final int tickType = readInt(inputStream);
        final double basisPoints = readDouble(inputStream);
        final String formattedBasisPoints = readString(inputStream);
        final double impliedFuturePrice = readDouble(inputStream);
        final int holdDays = readInt(inputStream);
        final String futureExpiry = readString(inputStream);
        final double dividendImpact = readDouble(inputStream);
        final double dividendToExpiry = readDouble(inputStream);
        return createEvent(requestId, tickType, basisPoints, formattedBasisPoints, impliedFuturePrice, holdDays,
                futureExpiry, dividendImpact, dividendToExpiry);
    }

    private TickEfpEvent createEvent(final int requestId, final int tickType, final double basisPoints,
            final String formattedBasisPoints, final double impliedFuturePrice, final int holdDays,
            final String futureExpiry, final double dividendImpact, final double dividendToExpiry) {
        return new TickEfpEvent(toRequestId(requestId), TickType.fromValue(tickType), basisPoints,
                formattedBasisPoints, impliedFuturePrice, holdDays, futureExpiry, dividendImpact, dividendToExpiry);
    }

}
