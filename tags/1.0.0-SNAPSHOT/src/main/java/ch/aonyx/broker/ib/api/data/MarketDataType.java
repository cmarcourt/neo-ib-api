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

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @author Christophe Marcourt
 * @version 1.0.0
 */
public enum MarketDataType {

	UNKNOWN(-1), REAL_TIME(1), FROZEN(2);

	private final int value;
	private static final Map<Integer, MarketDataType> MAP;

	static {
		MAP = Maps.newHashMap();
		for (final MarketDataType marketDataType : values()) {
			MAP.put(marketDataType.getValue(), marketDataType);
		}
	}

	private MarketDataType(final int value) {
		this.value = value;
	}

	public final int getValue() {
		return value;
	}

	public static final MarketDataType fromValue(final int value) {
		if (MAP.containsKey(value)) {
			return MAP.get(value);
		}
		return UNKNOWN;
	}
}
