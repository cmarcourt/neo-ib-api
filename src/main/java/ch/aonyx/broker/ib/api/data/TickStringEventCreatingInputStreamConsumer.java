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

import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readInt;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readString;

import java.io.InputStream;

import ch.aonyx.broker.ib.api.io.AbstractEventCreatingInputStreamConsumerSupport;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class TickStringEventCreatingInputStreamConsumer extends
        AbstractEventCreatingInputStreamConsumerSupport<TickStringEvent> {

    public TickStringEventCreatingInputStreamConsumer(final InputStream inputStream, final int serverCurrentVersion) {
        super(inputStream, serverCurrentVersion);
    }

    @Override
    protected TickStringEvent consumeVersionLess(final InputStream inputStream) {
        final int requestId = readInt(inputStream);
        final int tickType = readInt(inputStream);
        final String value = readString(inputStream);
        return createTickStringEvent(requestId, tickType, value);
    }

    private TickStringEvent createTickStringEvent(final int requestId, final int tickType, final String value) {
        return new TickStringEvent(toRequestId(requestId), TickType.fromValue(tickType), value);
    }

}
