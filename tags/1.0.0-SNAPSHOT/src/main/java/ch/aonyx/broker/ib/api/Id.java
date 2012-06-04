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

/**
 * This interface represents an id used to bind {@link Request} object to
 * {@link Event} object.
 * 
 * No method is defined in this contract as the {@link Object#toString()} method
 * will return a human readable id.
 * 
 * @see ch.aonyx.broker.ib.api.Request#getId(),
 *      ch.aonyx.broker.ib.api.Event#getId(),
 *      ch.aonyx.broker.ib.api.OrderRequest#getId(),
 *      ch.aonyx.broker.ib.api.SimpleRequest#getId(),
 *      ch.aonyx.broker.ib.api.SubscriptionRequest#getId(),
 *      ch.aonyx.broker.ib.api.UnsubscriptionRequest#getId().
 * @author Christophe Marcourt
 * @version 1.0.0
 */
public interface Id {

}
