/*
 * Copyright (C) 2012-2013 Aonyx
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

import static ch.aonyx.broker.ib.api.CodeRange.isError;
import static ch.aonyx.broker.ib.api.CodeRange.isSystem;
import static ch.aonyx.broker.ib.api.CodeRange.isWarning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Christophe Marcourt
 * @since 1.1.4
 */
public class ServerMessageEventLoggingListener implements ServerMessageEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerMessageEventLoggingListener.class);

    @Override
    public void onInfo(final ServerMessageEvent event) {
        LOGGER.info(event.toString());
    }

    @Override
    public void onWarn(final ServerMessageEvent event) {
        LOGGER.warn(event.toString());
    }

    @Override
    public void onError(final ServerMessageEvent event) {
        LOGGER.error(event.toString());
    }

    @Override
    public void onUnknown(final ServerMessageEvent event) {
        LOGGER.info(event.toString());
    }

    @Override
    public void notify(final ServerMessageEvent event) {
        final int code = event.getCode();
        if (isError(code)) {
            onError(event);
        } else if (isSystem(code)) {
            onInfo(event);
        } else if (isWarning(code)) {
            onWarn(event);
        } else {
            onUnknown(event);
        }
    }

}
