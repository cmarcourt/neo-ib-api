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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Christophe Marcourt
 * @since 1.1.4
 */
public abstract class EventLoggingListenerSupport<E extends Event> implements EventListener<E> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void notify(final E event) {
        if (logger.isTraceEnabled()) {
            logger.trace(event.toString());
        } else if (logger.isDebugEnabled()) {
            logger.debug(event.toString());
        } else if (logger.isInfoEnabled()) {
            logger.info(event.toString());
        } else if (logger.isWarnEnabled()) {
            logger.warn(event.toString());
        } else if (logger.isErrorEnabled()) {
            logger.error(event.toString());
        }
    }

}
