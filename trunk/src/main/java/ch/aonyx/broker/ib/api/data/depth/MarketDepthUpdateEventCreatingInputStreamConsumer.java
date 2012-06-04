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
package ch.aonyx.broker.ib.api.data.depth;

import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readDouble;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readInt;

import java.io.InputStream;

import ch.aonyx.broker.ib.api.io.AbstractEventCreatingInputStreamConsumerSupport;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class MarketDepthUpdateEventCreatingInputStreamConsumer extends
		AbstractEventCreatingInputStreamConsumerSupport<MarketDepthUpdateEvent> {

	public MarketDepthUpdateEventCreatingInputStreamConsumer(final InputStream inputStream,
			final int serverCurrentVersion) {
		super(inputStream, serverCurrentVersion);
	}

	@Override
	protected MarketDepthUpdateEvent consumeVersionLess(final InputStream inputStream) {
		final int requestId = readInt(inputStream);
		final int rowId = readInt(inputStream);
		final int operation = readInt(inputStream);
		final int bookSide = readInt(inputStream);
		final double price = readDouble(inputStream);
		final int size = readInt(inputStream);
		return createEvent(requestId, rowId, operation, bookSide, price, size);
	}

	private MarketDepthUpdateEvent createEvent(final int requestId, final int rowId, final int operation,
			final int bookSide, final double price, final int size) {
		return new MarketDepthUpdateEvent(toRequestId(requestId), rowId, Operation.fromValue(operation),
				BookSide.fromValue(bookSide), price, size);
	}

}
