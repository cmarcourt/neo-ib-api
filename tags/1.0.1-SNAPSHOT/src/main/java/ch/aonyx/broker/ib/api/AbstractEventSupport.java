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
public abstract class AbstractEventSupport implements Event {

	private final long creationTimestamp;
	private long sequence;
	private Id id;

	protected AbstractEventSupport(final Id id) {
		this();
		this.id = id;
	}

	protected AbstractEventSupport() {
		creationTimestamp = System.currentTimeMillis();
	}

	@Override
	public final long getCreationTimestamp() {
		return creationTimestamp;
	}

	@Override
	public final void setSequence(final long sequence) {
		this.sequence = sequence;
	}

	@Override
	public final long getSequence() {
		return sequence;
	}

	@Override
	public final Id getId() {
		return id;
	}

}