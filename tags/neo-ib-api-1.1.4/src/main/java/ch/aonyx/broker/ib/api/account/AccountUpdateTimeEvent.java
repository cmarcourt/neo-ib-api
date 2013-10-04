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
package ch.aonyx.broker.ib.api.account;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import ch.aonyx.broker.ib.api.AbstractEventSupport;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class AccountUpdateTimeEvent extends AbstractEventSupport {

    private final String time;

    public AccountUpdateTimeEvent(final String time) {
        super();
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public DateTime getDateTime() {
        return LocalTime.parse(time).toDateTimeToday();
    }

    @Override
    public Class<?> getAssignableListenerType() {
        return AccountUpdateTimeEventListener.class;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
