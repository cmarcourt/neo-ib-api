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
package ch.aonyx.broker.ib.api;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public enum IncomingMessageId {

    FINISH(-1), UNKNOWN(0), ACCOUNT_UPDATE_VALUE(6), ACCOUNT_UPDATE_TIME(8), MANAGED_ACCOUNT_LIST(15),
    ACCOUNT_UPDATE_VALUE_END(54), SERVER_MESSAGE(4), NEXT_VALID_ORDER_ID(9), TICK_PRICE(1), TICK_SIZE(2), TICK_GENERIC(
            45), TICK_STRING(46), TICK_OPTION_COMPUTATION(21), TICK_EXCHANFE_FOR_PHYSICAL(47), TICK_SNAPSHOT_END(57),
    SERVER_CURRENT_TIME(49), EXECUTION_REPORT(11), EXECUTION_REPORT_END(55), CONTRACT_SPECIFICATION(10),
    CONTRACT_SPECIFICATION_END(52), BOND_CONTRACT_SPECIFICATION(18), MARKET_DEPTH_UPDATE(12),
    MARKET_DEPTH_LEVEL_TWO_UPDATE(13), NEWS_BULLETIN_UPDATE(14), FINANCIAL_ADVISOR_CONFIGURATION(16),
    MARKET_SCANNER_VALID_PARAMETERS(19), MARKET_SCANNER_DATA(20), HISTORICAL_DATA(17), REAL_TIME_BAR(50),
    FUNDAMENTAL_DATA(51), ORDER_STATE_UPDATE(3), RETRIEVE_OPEN_ORDER(5), PORTFOLIO_UPDATE(7), RETRIEVE_OPEN_ORDER_END(
            53), DELTA_NEUTRAL_VALIDATION(56), MARKET_DATA_TYPE(58);

    private final int id;
    private static final Map<Integer, IncomingMessageId> MAP;

    static {
        MAP = Maps.newHashMap();
        for (final IncomingMessageId incomingMessageId : values()) {
            MAP.put(incomingMessageId.getId(), incomingMessageId);
        }
    }

    private IncomingMessageId(final int id) {
        this.id = id;
    }

    public final int getId() {
        return id;
    }

    public static final IncomingMessageId fromId(final int id) {
        if (MAP.containsKey(id)) {
            return MAP.get(id);
        }
        return UNKNOWN;
    }
}
