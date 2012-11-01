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
public enum TimeInForce {

    UNKNOWN("UNKNOWN"), EMPTY(""), DAY("DAY"), GOOD_TILL_CANCEL("GTC"), IMMEDIATE_OR_CANCEL("IOC"), GOOD_TILL_DATE(
            "GTD");

    private final String abbreviation;
    private static final Map<String, TimeInForce> MAP;

    static {
        MAP = Maps.newHashMap();
        for (final TimeInForce timeInForce : values()) {
            MAP.put(timeInForce.getAbbreviation(), timeInForce);
        }
    }

    private TimeInForce(final String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public final String getAbbreviation() {
        return abbreviation;
    }

    public static final TimeInForce fromAbbreviation(final String abbreviation) {
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
