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
package ch.aonyx.broker.ib.api.fa;

import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readInt;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readString;

import java.io.InputStream;

import ch.aonyx.broker.ib.api.io.AbstractEventCreatingInputStreamConsumerSupport;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class FinancialAdvisorConfigurationEventCreatingInputStreamConsumer extends
		AbstractEventCreatingInputStreamConsumerSupport<FinancialAdvisorConfigurationEvent> {

	public FinancialAdvisorConfigurationEventCreatingInputStreamConsumer(final InputStream inputStream,
			final int serverCurrentVersion) {
		super(inputStream, serverCurrentVersion);
	}

	@Override
	protected FinancialAdvisorConfigurationEvent consumeVersionLess(final InputStream inputStream) {
		final int dataType = readInt(inputStream);
		final String xml = readString(inputStream);
		return createEvent(dataType, xml);
	}

	private FinancialAdvisorConfigurationEvent createEvent(final int dataType, final String xml) {
		return new FinancialAdvisorConfigurationEvent(FinancialAdvisorDataType.fromValue(dataType), xml);
	}

}
