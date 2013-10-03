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

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
final class RequestBiMapService implements RequestService {

    private final Map<Id, Request> idRequestMap;

    RequestBiMapService() {
        idRequestMap = Maps.newHashMap();
    }

    @Override
    public List<Request> getRequests() {
        return Collections.unmodifiableList(Lists.newArrayList(idRequestMap.values()));
    }

    @Override
    public Request getRequest(final Id id) {
        return idRequestMap.get(id);
    }

    @Override
    public boolean contains(final Id id) {
        return idRequestMap.containsKey(id);
    }

    void addRequest(final Request request) {
        idRequestMap.put(request.getId(), request);
    }
}
