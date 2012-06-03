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
package ch.aonyx.broker.ib.api.contract;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

/**
 * @author Christophe Marcourt
 * @version 1.0.0
 */
public enum OptionRight {

	PUT("P", "PUT"), CALL("C", "CALL"), UNKNOWN("U", "UNKNOWN"), EMPTY("", "");

	private final String initial;
	private final String name;
	private static final Map<String, OptionRight> INITIAL_MAP;
	private static final Map<String, OptionRight> NAME_MAP;

	static {
		INITIAL_MAP = Maps.newHashMap();
		NAME_MAP = Maps.newHashMap();
		for (final OptionRight optionRight : values()) {
			INITIAL_MAP.put(optionRight.getInitial(), optionRight);
			NAME_MAP.put(optionRight.getName(), optionRight);
		}
	}

	private OptionRight(final String initial, final String name) {
		this.initial = initial;
		this.name = name;
	}

	public final String getInitial() {
		return initial;
	}

	public final String getName() {
		return name;
	}

	public static final OptionRight fromInitialOrName(final String value) {
		if (StringUtils.isBlank(value)) {
			return EMPTY;
		}
		final String valueUpperCase = value.toUpperCase();
		if (INITIAL_MAP.containsKey(valueUpperCase)) {
			return INITIAL_MAP.get(valueUpperCase);
		}
		if (NAME_MAP.containsKey(valueUpperCase)) {
			return NAME_MAP.get(valueUpperCase);
		}
		return UNKNOWN;
	}
}
