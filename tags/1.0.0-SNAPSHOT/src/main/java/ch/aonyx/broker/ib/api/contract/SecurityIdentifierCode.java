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
public enum SecurityIdentifierCode {

	SIN("SIN"), CUSIP("CUSIP"), SEDOL("SEDOL"), RIC("RIC"), UNKNOWN("UNKNOWN"), EMPTY("");

	private final String acronym;
	private static final Map<String, SecurityIdentifierCode> MAP;

	static {
		MAP = Maps.newHashMap();
		for (final SecurityIdentifierCode code : values()) {
			MAP.put(code.getAcronym(), code);
		}
	}

	private SecurityIdentifierCode(final String acronym) {
		this.acronym = acronym;
	}

	public final String getAcronym() {
		return acronym;
	}

	public static final SecurityIdentifierCode fromAcronym(final String acronym) {
		if (StringUtils.isBlank(acronym)) {
			return EMPTY;
		}
		final String acronymUpperCase = acronym.toUpperCase();
		if (MAP.containsKey(acronymUpperCase)) {
			return MAP.get(acronymUpperCase);
		}
		return UNKNOWN;
	}
}
