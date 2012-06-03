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
 * @author Christophe Marcourt
 * @version 1.0.0
 */
public interface Event {

	Class<?> getAssignableListenerType();

	long getCreationTimestamp();

	/**
	 * This method is for internal use only. Any call during event creation
	 * would have no effect.
	 * 
	 * @param sequence
	 */
	void setSequence(long sequence);

	/**
	 * The ordered sequence which the event was created with.
	 */
	long getSequence();

	/**
	 * {@link Id} would be one of the following sub type : {@link RequestId} or
	 * {@link OrderId}.
	 */
	Id getId();
}
