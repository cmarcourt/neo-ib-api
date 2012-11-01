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

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public enum Feature {

    POST_TRADE_ALLOCATION(39), REUTERS_FUNDAMENTAL_DATA(40), DELTA_NEUTRAL_COMBO_ORDER(40), SCALE_ORDER(40),
    CONTRACT_SPECIFICATION_MARKER(40), ALGORITHM_ORDER(41), EXECUTION_MARKER(42), NOT_HELD(44), SECURITY_ID_TYPE(45),
    PLACE_ORDER_BY_CONTRACT_ID(46), MARKET_DATA_REQUEST_BY_CONTRACT_ID(47), CALCULATE_IMPLIED_VOLATILITY(49),
    CALCULATE_OPTION_PRICE(50), CANCEL_CALCULATE_IMPLIED_VOLATILITY(50), CANCEL_CALCULATE_OPTION_PRICE(50),
    SHORT_SALE_EXEMPT_ORDER_OLD(51), SHORT_SALE_EXEMPT_ORDER(52), GLOBAL_CANCEL_ORDER_REQUEST(53), HEDGING_ORDER(54),
    MARKET_DATA_TYPE(55), OPT_OUT_DEFAULT_SMART_ROUTING_ASX_ORDER(56), SMART_COMBO_ROUTING_PARAMETER(57),
    DELTA_NEUTRAL_COMBO_ORDER_BY_CONTRACT_ID(58);

    private final int version;

    private Feature(final int version) {
        this.version = version;
    }

    private int getVersion() {
        return version;
    }

    public boolean isSupportedByVersion(final int version) {
        return version >= getVersion();
    }
}
