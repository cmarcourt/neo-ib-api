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

import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * @author Christophe Marcourt
 * @version 1.0.0
 */
final class RequestIdInternalIdBinding {

	private static final RequestIdInternalIdBinding INSTANCE = new RequestIdInternalIdBinding();
	private final BiMap<Id, Integer> binding;
	private final AtomicInteger sequence;

	static RequestIdInternalIdBinding getInstance() {
		return INSTANCE;
	}

	private RequestIdInternalIdBinding() {
		binding = HashBiMap.create();
		sequence = new AtomicInteger(1);
	}

	void addAndBind(final Request request) {
		binding.put(request.getId(), sequence.getAndIncrement());
	}

	boolean containsRequestId(final Id id) {
		return binding.containsKey(id);
	}

	Id getRequestId(final int internalId) {
		return binding.inverse().get(internalId);
	}

	boolean containsInternalId(final int internalId) {
		return binding.containsValue(internalId);
	}

	int getInternalId(final Id id) {
		return binding.get(id);
	}
}
