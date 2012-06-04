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

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public enum HistoricalDataType {

	UNKNOWN("UNKNOWN"), EMPTY(""), TRADES("TRADES"), MID_POINT("MIDPOINT"), BID("BID"), ASK("ASK"), BID_ASK("BID_ASK"), HISTORICAL_VOLATILITY(
			"HISTORICAL_VOLATILITY"), OPTION_IMPLIED_VOLATILITY("OPTION_IMPLIED_VOLATILITY");

	private final String label;
	private static final Map<String, HistoricalDataType> MAP;

	static {
		MAP = Maps.newHashMap();
		for (final HistoricalDataType dataType : values()) {
			MAP.put(dataType.getLabel(), dataType);
		}
	}

	private HistoricalDataType(final String label) {
		this.label = label;
	}

	public final String getLabel() {
		return label;
	}

	public static final HistoricalDataType fromLabel(final String label) {
		if (StringUtils.isBlank(label)) {
			return EMPTY;
		}
		final String labelUpperCase = label.toUpperCase();
		if (MAP.containsKey(labelUpperCase)) {
			return MAP.get(labelUpperCase);
		}
		return UNKNOWN;
	}
}
