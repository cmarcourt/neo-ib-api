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
package ch.aonyx.broker.ib.api.data.depth;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ch.aonyx.broker.ib.api.AbstractEventSupport;
import ch.aonyx.broker.ib.api.Id;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class MarketDepthUpdateEvent extends AbstractEventSupport {

	private final int rowId;
	private final Operation operation;
	private final BookSide bookSide;
	private final double price;
	private final int size;

	public MarketDepthUpdateEvent(final Id requestId, final int rowId, final Operation operation,
			final BookSide bookSide, final double price, final int size) {
		super(requestId);
		this.rowId = rowId;
		this.operation = operation;
		this.bookSide = bookSide;
		this.price = price;
		this.size = size;
	}

	@Override
	public Class<?> getAssignableListenerType() {
		return MarketDepthUpdateEventListener.class;
	}

	public final int getRowId() {
		return rowId;
	}

	public final Operation getOperation() {
		return operation;
	}

	public final BookSide getBookSide() {
		return bookSide;
	}

	public final double getPrice() {
		return price;
	}

	public final int getSize() {
		return size;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
