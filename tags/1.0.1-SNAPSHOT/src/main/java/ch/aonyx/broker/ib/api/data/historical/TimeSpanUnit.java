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
 * @version 1.0.0
 */
public enum TimeSpanUnit {

	UNKNOWN("U"), EMPTY(""), SECOND("S"), DAY("D"), WEEK("W"), MONTH("M"), YEAR("Y");

	private final String initial;
	private static final Map<String, TimeSpanUnit> MAP;

	static {
		MAP = Maps.newHashMap();
		for (final TimeSpanUnit unit : values()) {
			MAP.put(unit.getInitial(), unit);
		}
	}

	private TimeSpanUnit(final String initial) {
		this.initial = initial;
	}

	public final String getInitial() {
		return initial;
	}

	public static final TimeSpanUnit fromInitial(final String initial) {
		if (StringUtils.isBlank(initial)) {
			return EMPTY;
		}
		final String initialUpperCase = initial.toUpperCase();
		if (MAP.containsKey(initialUpperCase)) {
			return MAP.get(initialUpperCase);
		}
		return UNKNOWN;
	}
}
