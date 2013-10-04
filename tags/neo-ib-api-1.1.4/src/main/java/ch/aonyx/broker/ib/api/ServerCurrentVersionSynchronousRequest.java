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
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.aonyx.broker.ib.api.util.ByteArrayRequestBuilder;
import ch.aonyx.broker.ib.api.util.InputStreamUtils;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
final class ServerCurrentVersionSynchronousRequest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerCurrentVersionSynchronousRequest.class);
    private final int clientVersion;
    private final InputStream inputStream;
    private final OutputStream outputStream;
    private ByteArrayRequestBuilder builder;

    ServerCurrentVersionSynchronousRequest(final int clientVersion, final OutputStream outputStream,
            final InputStream inputStream) {
        this.clientVersion = clientVersion;
        this.outputStream = outputStream;
        this.inputStream = inputStream;
        createByteArrayRequestBuilder();
    }

    private void createByteArrayRequestBuilder() {
        builder = new ByteArrayRequestBuilder();
        builder.append(clientVersion);
    }

    int getResponse() {
        try {
            IOUtils.write(builder.toBytes(), outputStream);
        } catch (final IOException e) {
            LOGGER.error("", e);
        }
        return InputStreamUtils.readInt(inputStream);
    }
}
