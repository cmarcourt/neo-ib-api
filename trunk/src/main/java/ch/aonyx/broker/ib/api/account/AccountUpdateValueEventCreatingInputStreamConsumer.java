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
package ch.aonyx.broker.ib.api.account;

import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readString;

import java.io.InputStream;

import ch.aonyx.broker.ib.api.io.AbstractEventCreatingInputStreamConsumerSupport;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class AccountUpdateValueEventCreatingInputStreamConsumer extends
		AbstractEventCreatingInputStreamConsumerSupport<AccountUpdateValueEvent> {

	public AccountUpdateValueEventCreatingInputStreamConsumer(final InputStream inputStream,
			final int serverCurrentVersion) {
		super(inputStream, serverCurrentVersion);
	}

	@Override
	protected AccountUpdateValueEvent consumeVersionLess(final InputStream inputStream) {
		final String key = readString(inputStream);
		final String value = readString(inputStream);
		final String currency = readString(inputStream);
		String accountName = null;
		if (getVersion() >= 2) {
			accountName = readString(inputStream);
		}
		return createEvent(key, value, currency, accountName);
	}

	private AccountUpdateValueEvent createEvent(final String key, final String value, final String currency,
			final String accountName) {
		return new AccountUpdateValueEvent(key, value, currency, accountName);
	}
}
