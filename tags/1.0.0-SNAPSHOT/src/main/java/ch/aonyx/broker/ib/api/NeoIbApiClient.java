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

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.aonyx.broker.ib.api.net.ConnectionCallback;
import ch.aonyx.broker.ib.api.net.ConnectionException;
import ch.aonyx.broker.ib.api.net.ConnectionParameters;

/**
 * @author Christophe Marcourt
 * @version 1.0.0
 */
public final class NeoIbApiClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(NeoIbApiClient.class);
	private final ClientCallback clientCallback;
	private Socket socket;

	public NeoIbApiClient(final ClientCallback clientCallback) {
		Validate.notNull(clientCallback);
		this.clientCallback = clientCallback;
	}

	public void connect(final ConnectionParameters connectionParameters, final ConnectionCallback callback) {
		Validate.notNull(connectionParameters);
		Validate.notNull(callback);
		try {
			socket = new Socket(connectionParameters.getHost(), connectionParameters.getPort());
			callback.onSuccess(new SocketSession(socket, connectionParameters.getClientId(), clientCallback));
		} catch (final UnknownHostException e) {
			LOGGER.error("", e);
			callback.onFailure(new ConnectionException(ClientMessageCode.CONNECTION_ERROR, "Unknown host "
					+ e.getMessage()));
		} catch (final IOException e) {
			LOGGER.error("", e);
			callback.onFailure(new ConnectionException(ClientMessageCode.CONNECTION_FAILURE, e.getMessage()));
		}
	}

	public void disconnect() {
		IOUtils.closeQuietly(socket);
	}

	public boolean isConnected() {
		return socket.isConnected();
	}

}
