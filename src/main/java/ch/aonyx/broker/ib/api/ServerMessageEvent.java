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

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.common.collect.Lists;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class ServerMessageEvent extends AbstractEventSupport {

	private final int code;
	private final String message;
	private List<Object> parameters = Lists.newArrayList();

	public ServerMessageEvent(final int code, final String message, final Object... parameters) {
		this(null, code, message);
		if ((parameters != null) && (parameters.length > 0)) {
			this.parameters = Lists.newArrayList(parameters);
		}
	}

	public ServerMessageEvent(final Id requestId, final int code, final String message) {
		super(requestId);
		this.code = code;
		this.message = message;
	}

	public final int getCode() {
		return code;
	}

	public final String getMessage() {
		return message;
	}

	public final List<Object> getParameters() {
		return parameters;
	}

	@Override
	public Class<?> getAssignableListenerType() {
		return ServerMessageEventListener.class;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
