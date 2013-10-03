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
 * @since 1.0.0
 */
public enum OriginInstitutional {

    UNKNOWN(-1), CUSTOMER(0), FIRM(1);

    private final int value;
    private static final Map<Integer, OriginInstitutional> MAP;

    static {
        MAP = Maps.newHashMap();
        for (final OriginInstitutional origin : values()) {
            MAP.put(origin.getValue(), origin);
        }
    }

    private OriginInstitutional(final int value) {
        this.value = value;
    }

    public final int getValue() {
        return value;
    }

    public static final OriginInstitutional fromValue(final int value) {
        if (MAP.containsKey(value)) {
            return MAP.get(value);
        }
        return UNKNOWN;
    }
}
