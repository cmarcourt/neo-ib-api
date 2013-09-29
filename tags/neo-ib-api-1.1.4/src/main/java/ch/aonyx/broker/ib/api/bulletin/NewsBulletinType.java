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
package ch.aonyx.broker.ib.api.bulletin;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public enum NewsBulletinType {

    UNKNOWN(-1), REGULAR(1), EXCHANGE_NON_AVAILABLE(2), EXCHANGE_AVAILABLE(3);

    private final int value;
    private static final Map<Integer, NewsBulletinType> MAP;

    static {
        MAP = Maps.newHashMap();
        for (final NewsBulletinType newsBulletinType : values()) {
            MAP.put(newsBulletinType.getValue(), newsBulletinType);
        }
    }

    private NewsBulletinType(final int value) {
        this.value = value;
    }

    public final int getValue() {
        return value;
    }

    public static final NewsBulletinType fromValue(final int value) {
        if (MAP.containsKey(value)) {
            return MAP.get(value);
        }
        return UNKNOWN;
    }
}
