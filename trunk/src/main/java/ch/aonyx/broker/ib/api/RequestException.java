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
public class RequestException extends NeoIbApiClientException {

	private static final long serialVersionUID = -1744532346747011511L;
	private final Request request;

	public RequestException(final RequestException exception) {
		this(exception.getClientMessageCode(), exception.getDetailedMessage(), exception.getRequest(), exception
				.getCause());
	}

	public RequestException(final ClientMessageCode clientMessageCode, final String detailedMessage,
			final Request request, final Throwable cause) {
		super(clientMessageCode, detailedMessage, cause);
		this.request = request;
	}

	public RequestException(final ClientMessageCode clientMessageCode, final String detailedMessage,
			final Request request) {
		this(clientMessageCode, detailedMessage, request, null);
	}

	public final Request getRequest() {
		return request;
	}
}
