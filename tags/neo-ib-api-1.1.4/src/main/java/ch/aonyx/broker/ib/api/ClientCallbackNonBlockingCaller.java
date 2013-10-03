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

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
final class ClientCallbackNonBlockingCaller {

    private final Executor executor;

    ClientCallbackNonBlockingCaller() {
        executor = Executors.newCachedThreadPool();
    }

    void onSuccess(final ClientCallback clientCallback, final CallbackObject object) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                clientCallback.onSuccess(object);
            }
        });
    }

    void onFailure(final ClientCallback clientCallback, final NeoIbApiClientException exception) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                clientCallback.onFailure(exception);
            }
        });
    }

}
