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
package ch.aonyx.broker.ib.api.data.historical;

import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readDouble;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readInt;
import static ch.aonyx.broker.ib.api.util.InputStreamUtils.readString;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.lang3.BooleanUtils;

import ch.aonyx.broker.ib.api.io.AbstractEventCreatingInputStreamConsumerSupport;

import com.google.common.collect.Lists;

/**
 * @author Christophe Marcourt
 * @version 1.0.0
 */
public final class HistoricalDataEventListEventCreatingInputStreamConsumer extends
		AbstractEventCreatingInputStreamConsumerSupport<HistoricalDataEventListEvent> {

	private static final String FINISHED = "finished";

	public HistoricalDataEventListEventCreatingInputStreamConsumer(final InputStream inputStream,
			final int serverCurrentVersion) {
		super(inputStream, serverCurrentVersion);
	}

	@Override
	protected HistoricalDataEventListEvent consumeVersionLess(final InputStream inputStream) {
		final List<HistoricalDataEvent> historicalDataEvents = Lists.newArrayList();
		final int requestId = readInt(inputStream);
		String startDate = null;
		String endDate = null;
		String finishedRetrievingHistoricalData = FINISHED;
		if (getVersion() >= 2) {
			startDate = readString(inputStream);
			endDate = readString(inputStream);
			finishedRetrievingHistoricalData = finishedRetrievingHistoricalData + "-" + startDate + "-" + endDate;
		}
		final int historicalDatas = readInt(inputStream);
		for (int i = 0; i < historicalDatas; i++) {
			historicalDataEvents.add(consumeHistoricalData(requestId, inputStream));
		}
		historicalDataEvents.add(createHistoricalDataEvent(requestId, finishedRetrievingHistoricalData, -1, -1, -1, -1,
				-1, -1, -1, "false"));
		return createHistoricalDataEventListEvent(requestId, historicalDataEvents);
	}

	private HistoricalDataEvent consumeHistoricalData(final int requestId, final InputStream inputStream) {
		final String dateTime = readString(inputStream);
		final double open = readDouble(inputStream);
		final double high = readDouble(inputStream);
		final double low = readDouble(inputStream);
		final double close = readDouble(inputStream);
		final int volume = readInt(inputStream);
		final double weightedAveragePrice = readDouble(inputStream);
		final String hasGap = readString(inputStream);
		int tradeNumber = -1;
		if (getVersion() >= 3) {
			tradeNumber = readInt(inputStream);
		}
		return createHistoricalDataEvent(requestId, dateTime, open, high, low, close, volume, weightedAveragePrice,
				tradeNumber, hasGap);
	}

	private HistoricalDataEvent createHistoricalDataEvent(final int requestId, final String dateTime,
			final double open, final double high, final double low, final double close, final int volume,
			final double weightedAveragePrice, final int tradeNumber, final String hasGap) {
		return new HistoricalDataEvent(toRequestId(requestId), dateTime, open, high, low, close, volume, tradeNumber,
				weightedAveragePrice, BooleanUtils.toBoolean(hasGap));
	}

	private HistoricalDataEventListEvent createHistoricalDataEventListEvent(final int requestId,
			final List<HistoricalDataEvent> historicalDataEvents) {
		return new HistoricalDataEventListEvent(toRequestId(requestId), historicalDataEvents);
	}
}
