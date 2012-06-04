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
public enum OutgoingMessageId {

	NO_MESSAGE(0), MARKET_DATA_SUBSCRIPTION_REQUEST(1), ACCOUNT_UPDATE_SUBSCRIPTION_REQUEST(6), NEXT_VALID_ORDER_ID_REQUEST(
			8), MARKET_DATA_UNSUBSCRIPTION_REQUEST(2), OPTION_IMPLIED_VOLATILITY_SUBSCRIPTION_REQUEST(54), OPTION_IMPLIED_VOLATILITY_UNSUBSCRIPTION_REQUEST(
			56), OPTION_PRICE_SUBSCRIPTION_REQUEST(55), OPTION_PRICE_UNSUBSCRIPTION_REQUEST(57), MARKET_DATA_TYPE_REQUEST(
			59), SERVER_LOG_LEVEL_REQUEST(14), SERVER_CURRENT_TIME_REQUEST(49), EXECUTION_REPORT_REQUEST(7), CONTRACT_SPECIFICATION_REQUEST(
			9), MARKET_DEPTH_SUBSCRIPTION_REQUEST(10), MARKET_DEPTH_UNSUBSCRIPTION_REQUEST(11), NEWS_BULLETIN_SUBSCRIPTION_REQUEST(
			12), NEWS_BULLETIN_UNSUBSCRIPTION_REQUEST(13), MANAGED_ACCOUNT_LIST_REQUEST(17), FINANCIAL_ADVISOR_CONFIGURATION_REQUEST(
			18), FINANCIAL_ADVISOR_REPLACE_CONFIGURATION_REQUEST(19), MARKET_SCANNER_VALID_PARAMETERS_REQUEST(24), MARKET_SCANNER_SUBSCRIPTION_REQUEST(
			22), MARKET_SCANNER_UNSUBSCRIPTION_REQUEST(23), HISTORICAL_DATA_SUBSCRIPTION_REQUEST(20), HISTORICAL_DATA_UNSUBSCRIPTION_REQUEST(
			25), REAL_TIME_BAR_SUBSCRIPTION_REQUEST(50), REAL_TIME_BAR_UNSUBSCRIPTION_REQUEST(51), FUNDAMENTAL_DATA_SUBSCRIPTION_REQUEST(
			52), FUNDAMENTAL_DATA_UNSUBSCRIPTION_REQUEST(53), PLACE_ORDER_REQUEST(3), CANCEL_ORDER_REQUEST(4), EXERCISE_OPTION_REQUEST(
			21), RETRIEVE_OPEN_ORDER_REQUEST(5), RETRIEVE_ALL_OPEN_ORDER_REQUEST(16), BIND_NEWLY_CREATED_OPEN_ORDER_REQUEST(
			15), CANCEL_ALL_ORDERS_REQUEST(58);

	private final int id;
	private static final Map<Integer, OutgoingMessageId> MAP;

	static {
		MAP = Maps.newHashMap();
		for (final OutgoingMessageId outgoingMessageId : values()) {
			MAP.put(outgoingMessageId.getId(), outgoingMessageId);
		}
	}

	private OutgoingMessageId(final int id) {
		this.id = id;
	}

	public final int getId() {
		return id;
	}

	public static final OutgoingMessageId fromId(final int id) {
		if (MAP.containsKey(id)) {
			return MAP.get(id);
		}
		return NO_MESSAGE;
	}
}
