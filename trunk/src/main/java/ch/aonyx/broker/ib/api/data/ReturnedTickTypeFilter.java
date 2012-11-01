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
 * @since 1.0.0
 */
public enum ReturnedTickTypeFilter {

    UNKNOWN(-1), OPTION_VOLUME(100), OPTION_OPEN_INTEREST(101), HISTORICAL_VOLATILITY(104), OPTION_IMPLIED_VOLATILITY(
            106), INDEX_FUTURE_PREMIUM(162), MISCELLANEOUS_STATS(165), MARK_PRICE(221), AUCTION_VALUES(225),
    REAL_TIME_VOLUME(233), SHORTABLE(236), INVENTORY(256), FUNDAMENTAL_RATIOS(258),
    REAL_TIME_HISTORICAL_VOLATILITY(411);

    private final int id;
    private static final Map<Integer, ReturnedTickTypeFilter> MAP;

    static {
        MAP = Maps.newHashMap();
        for (final ReturnedTickTypeFilter filterReturnedTickType : values()) {
            MAP.put(filterReturnedTickType.getId(), filterReturnedTickType);
        }
    }

    private ReturnedTickTypeFilter(final int id) {
        this.id = id;
    }

    public final int getId() {
        return id;
    }

    public static final ReturnedTickTypeFilter fromId(final int id) {
        if (MAP.containsKey(id)) {
            return MAP.get(id);
        }
        return UNKNOWN;
    }
}
