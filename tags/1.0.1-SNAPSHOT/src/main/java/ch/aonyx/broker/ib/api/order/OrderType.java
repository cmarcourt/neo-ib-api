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

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public enum OrderType {

	UNKNOWN("UNKNOWN"), MARKET_TO_LIMIT("MTL"), STOP("STP"), STOP_LIMIT("STPLMT"), TRAILING_STOP_LIMIT_IF_PRICE_TOUCHED(
			"TRAILLIT"), TRAILING_STOP_MARKET_IF_PRICE_TOUCHED("TRAILMIT"), TRAILING_STOP("TRAIL"), TRAILING_STOP_LIMIT(
			"TRAILLMT"), MARKET("MKT"), MARKET_IF_PRICE_TOUCHED("MIT"), MARKET_ON_CLOSE("MOC"), MARKET_ON_OPEN("MOO"), PEGGED_TO_MARKET(
			"PEGMKT"), RELATIVE("REL"), BOX_TOP("BOXTOP"), LIMIT_ON_CLOSE("LOC"), LIMIT_ON_OPEN("LOO"), LIMIT_IF_PRICE_TOUCHED(
			"LIT"), PEGGED_TO_MIDPOINT("PEGMID"), VOLUME_WEIGHTED_AVERAGE_PRICE_GUARANTEED("VWAP"), GOOD_AFTER_TIME(
			"GAT"), GOOD_TILL_DATE("GTD"), GOOD_TILL_CANCELLED("GTC"), IMMEDIATE_OR_CANCEL("IOC"), ONE_CANCELS_ALL(
			"OCA"), VOLATILITY("VOL"), LIMIT("LMT"), HIDDEN("HID"), ICEBERG("ICE"), SCALE("SCALE"), EMPTY("");

	private final String abbreviation;
	private static final Map<String, OrderType> MAP;

	static {
		MAP = Maps.newHashMap();
		for (final OrderType orderType : values()) {
			MAP.put(orderType.getAbbreviation(), orderType);
		}
	}

	private OrderType(final String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public final String getAbbreviation() {
		return abbreviation;
	}

	public static final OrderType fromAbbreviation(final String abbreviation) {
		if (StringUtils.isBlank(abbreviation)) {
			return EMPTY;
		}
		final String abbreviationUpperCase = abbreviation.toUpperCase();
		if (MAP.containsKey(abbreviationUpperCase)) {
			return MAP.get(abbreviationUpperCase);
		}
		return UNKNOWN;
	}
}
