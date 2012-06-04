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
package ch.aonyx.broker.ib.api.io;

import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readInt;

import java.io.InputStream;

import org.apache.commons.lang3.Validate;

import ch.aonyx.broker.ib.api.AbstractEventCreatingConsumerSupport;
import ch.aonyx.broker.ib.api.Event;

/**
 * @author Christophe Marcourt
 * @version 1.0.0
 */
public abstract class AbstractEventCreatingInputStreamConsumerSupport<E extends Event> extends
		AbstractEventCreatingConsumerSupport<E> {

	private int version;
	private final InputStream inputStream;
	private final int serverCurrentVersion;

	protected AbstractEventCreatingInputStreamConsumerSupport(final InputStream inputStream,
			final int serverCurrentVersion) {
		this.inputStream = inputStream;
		this.serverCurrentVersion = serverCurrentVersion;
	}

	@Override
	public E consume() {
		Validate.notNull(inputStream);
		version = readInt(inputStream);
		return consumeVersionLess(inputStream);
	}

	protected abstract E consumeVersionLess(InputStream inputStream);

	protected final int getVersion() {
		return version;
	}

	protected final int getServerCurrentVersion() {
		return serverCurrentVersion;
	}

}
