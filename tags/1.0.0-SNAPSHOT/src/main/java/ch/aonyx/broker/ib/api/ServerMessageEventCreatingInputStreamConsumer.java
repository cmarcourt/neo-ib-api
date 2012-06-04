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
package ch.aonyx.broker.ib.api;

import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readInt;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readString;

import java.io.InputStream;

import ch.aonyx.broker.ib.api.io.AbstractEventCreatingInputStreamConsumerSupport;

/**
 * @author Christophe Marcourt
 * @version 1.0.0
 */
public final class ServerMessageEventCreatingInputStreamConsumer extends
		AbstractEventCreatingInputStreamConsumerSupport<ServerMessageEvent> {

	private static final int VERSION = 2;

	public ServerMessageEventCreatingInputStreamConsumer(final InputStream inputStream, final int serverCurrentVersion) {
		super(inputStream, serverCurrentVersion);
	}

	private ServerMessageEvent createEvent(final int requestId, final int code, final String message) {
		return new ServerMessageEvent(toRequestId(requestId), code, message);
	}

	@Override
	protected ServerMessageEvent consumeVersionLess(final InputStream inputStream) {
		if (getVersion() < VERSION) {
			final String message = readString(inputStream);
			return createEvent(-1, 0, message);
		} else {
			final int requestId = readInt(inputStream);
			final int code = readInt(inputStream);
			final String message = readString(inputStream);
			return createEvent(requestId, code, message);
		}
	}

}
