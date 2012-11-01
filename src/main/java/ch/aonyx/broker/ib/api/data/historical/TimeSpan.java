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
package ch.aonyx.broker.ib.api.data.historical;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class TimeSpan {

    private final int duration;
    private final TimeSpanUnit unit;

    public TimeSpan(final int duration, final TimeSpanUnit unit) {
        this.duration = duration;
        this.unit = unit;
    }

    public int getDuration() {
        return duration;
    }

    public TimeSpanUnit getUnit() {
        return unit;
    }

    public String getFormattedDuration() {
        return duration + " " + unit.getInitial();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(duration).append(unit).toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        final TimeSpan rhs = (TimeSpan) obj;
        return new EqualsBuilder().append(duration, rhs.duration).append(unit, rhs.unit).isEquals();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
