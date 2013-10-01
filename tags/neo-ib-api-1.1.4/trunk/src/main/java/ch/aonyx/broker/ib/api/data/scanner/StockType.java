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
package ch.aonyx.broker.ib.api.data.scanner;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public enum StockType {

    UNKNOWN("UNKNOWN"), ALL("ALL"), EXCLUDE_ETF("STOCK"), EXCLUDE_STOCK("ETF"), EMPTY("");

    private final String label;
    private static final Map<String, StockType> MAP;

    static {
        MAP = Maps.newHashMap();
        for (final StockType stockType : values()) {
            MAP.put(stockType.getLabel(), stockType);
        }
    }

    private StockType(final String label) {
        this.label = label;
    }

    public final String getLabel() {
        return label;
    }

    public static final StockType fromLabel(final String label) {
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
