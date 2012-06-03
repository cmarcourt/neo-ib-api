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

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @author Christophe Marcourt
 * @version 1.0.0
 */
public enum StopTriggerMethod {

	UNKNOWN(-1), DEFAULT(0), TWO_CONSECUTIVE_BID_ASK_PRICE(1), LAST_PRICE(2), DOUBLE_LAST_PRICE(3), BID_ASK_PRICE(4), LAST_OR_BID_ASK_PRICE(
			7), MID_POINT_PRICE(8);

	private final int value;
	private static final Map<Integer, StopTriggerMethod> MAP;

	static {
		MAP = Maps.newHashMap();
		for (final StopTriggerMethod method : values()) {
			MAP.put(method.getValue(), method);
		}
	}

	private StopTriggerMethod(final int value) {
		this.value = value;
	}

	public final int getValue() {
		return value;
	}

	public static final StopTriggerMethod fromValue(final int value) {
		if (MAP.containsKey(value)) {
			return MAP.get(value);
		}
		return UNKNOWN;
	}
}
