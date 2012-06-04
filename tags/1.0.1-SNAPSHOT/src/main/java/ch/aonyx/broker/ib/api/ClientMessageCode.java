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
public enum ClientMessageCode {

	UNKNOWN_MESSAGE(0, "Unknown message."), CONNECTION_ERROR(1, "Connection error."), INTERNAL_ERROR(2,
			"Internal error."), INPUT_OUTPUT_STREAM_EXCEPTION(10, "Input/Output stream exception."), ERROR_CREATING_OUPUT_STREAM(
			11, "Error creating output stream."), ERROR_CREATING_INPUT_STREAM(12, "Error creating input stream."), CANT_START_SESSION(
			13, "Can't start session."), SERVER_CURRENT_VERSION(14, "Server current version."), CONNECTION_SERVER_TIME(
			15, "Connection server time."), CANT_PUBLISH_REQUEST(16, "Can't publish request."), SUBSCRIPTION_ALREADY_SUBSCRIBED(
			20, "Subscription already subscribed, skip"), ALREADY_CONNECTED(501, "Already connected."), CONNECTION_FAILURE(
			502, "Couldn't connect to TWS. Confirm that \"Enable ActiveX and Socket Clients\" "
					+ "is enabled on the TWS \"Edit->Global Configuration->API->Settings\" menu."), UPDATE_TWS(503,
			"TWS is out of date and must be updated."), ACCOUNT_UPDATE_SUBSCRIPTION_REQUEST_FAILED(513,
			"Account Update Request Sending Error.");

	private final int code;
	private final String message;
	private static final Map<Integer, ClientMessageCode> MAP;

	static {
		MAP = Maps.newHashMap();
		for (final ClientMessageCode code : values()) {
			MAP.put(code.getCode(), code);
		}
	}

	private ClientMessageCode(final int code, final String message) {
		this.code = code;
		this.message = message;
	}

	public final int getCode() {
		return code;
	}

	public final String getMessage() {
		return message;
	}

	public static final ClientMessageCode fromCode(final int code) {
		if (MAP.containsKey(code)) {
			return MAP.get(code);
		}
		return UNKNOWN_MESSAGE;
	}
}
