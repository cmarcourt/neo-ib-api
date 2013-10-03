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
package ch.aonyx.broker.ib.api.net;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class ConnectionParameters {

    public static final String HOST_DEFAULT = "127.0.0.1";
    public static final int PORT_DEFAULT = 7496;
    public static final int CLIENT_ID_DEFAULT = 0;
    private final String host;
    private final int port;
    private final int clientId;

    /**
     * Create a {@code ConnectionParameters} object with default values: {@link ConnectionParameters#HOST_DEFAULT}
     * ("127.0.0.1"), {@link ConnectionParameters#PORT_DEFAULT} (7496), {@link ConnectionParameters#CLIENT_ID_DEFAULT}
     * (0).
     */
    public ConnectionParameters() {
        this(HOST_DEFAULT, PORT_DEFAULT, CLIENT_ID_DEFAULT);
    }

    /**
     * Create a {@code ConnectionParameters} object with default values: {@link ConnectionParameters#HOST_DEFAULT}
     * ("127.0.0.1"), {@link ConnectionParameters#PORT_DEFAULT} (7496).
     */
    public ConnectionParameters(final int clientId) {
        this(HOST_DEFAULT, PORT_DEFAULT, clientId);
    }

    public ConnectionParameters(final String host, final int port, final int clientId) {
        this.host = host;
        this.port = port;
        this.clientId = clientId;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public int getClientId() {
        return clientId;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(host).append(port).append(clientId).toHashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
