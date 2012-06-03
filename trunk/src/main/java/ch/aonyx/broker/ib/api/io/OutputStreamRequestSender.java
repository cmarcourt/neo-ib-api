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
package ch.aonyx.broker.ib.api.io;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import ch.aonyx.broker.ib.api.ClientMessageCode;
import ch.aonyx.broker.ib.api.Request;
import ch.aonyx.broker.ib.api.RequestException;

/**
 * @author Christophe Marcourt
 * @version 1.0.0
 */
public final class OutputStreamRequestSender implements RequestSender {

	private final OutputStream outputStream;

	public OutputStreamRequestSender(final OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	@Override
	public void send(final Request request) throws RequestException {
		try {
			IOUtils.write(request.getBytes(), outputStream);
		} catch (final IOException e) {
			throw new PublisherException(ClientMessageCode.CANT_PUBLISH_REQUEST, e.getMessage(), request, e);
		}
	}
}
