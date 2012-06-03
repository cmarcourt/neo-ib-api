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
package ch.aonyx.broker.ib.api.order;

import java.io.InputStream;

import ch.aonyx.broker.ib.api.io.AbstractEventCreatingInputStreamConsumerSupport;
import ch.aonyx.broker.ib.api.util.InputStreamUtils;

/**
 * @author Christophe Marcourt
 * @version 1.0.0
 */
public final class NextValidOrderIdEventCreatingInputStreamConsumer extends
		AbstractEventCreatingInputStreamConsumerSupport<NextValidOrderIdEvent> {

	public NextValidOrderIdEventCreatingInputStreamConsumer(final InputStream inputStream,
			final int serverCurrentVersion) {
		super(inputStream, serverCurrentVersion);
	}

	@Override
	protected NextValidOrderIdEvent consumeVersionLess(final InputStream inputStream) {
		final int nextValidOrderId = InputStreamUtils.readInt(inputStream);
		return createEvent(nextValidOrderId);
	}

	private NextValidOrderIdEvent createEvent(final int nextValidOrderId) {
		return new NextValidOrderIdEvent(nextValidOrderId);
	}
}
