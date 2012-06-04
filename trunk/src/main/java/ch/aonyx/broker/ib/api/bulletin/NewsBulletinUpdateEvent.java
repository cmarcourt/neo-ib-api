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
package ch.aonyx.broker.ib.api.bulletin;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ch.aonyx.broker.ib.api.AbstractEventSupport;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class NewsBulletinUpdateEvent extends AbstractEventSupport {

	private final int newsBulletinId;
	private final NewsBulletinType newsBulletinType;
	private final String message;
	private final String exchange;

	public NewsBulletinUpdateEvent(final int newsBulletinId, final NewsBulletinType newsBulletinType,
			final String message, final String exchange) {
		super();
		this.newsBulletinId = newsBulletinId;
		this.newsBulletinType = newsBulletinType;
		this.message = message;
		this.exchange = exchange;
	}

	public final int getNewsBulletinId() {
		return newsBulletinId;
	}

	public final NewsBulletinType getNewsBulletinType() {
		return newsBulletinType;
	}

	public final String getMessage() {
		return message;
	}

	public final String getExchange() {
		return exchange;
	}

	@Override
	public Class<?> getAssignableListenerType() {
		return NewsBulletinUpdateEventListener.class;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
