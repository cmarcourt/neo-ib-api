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

import static ch.aonyx.broker.ib.api.data.historical.BarSizeUnit.DAY;
import static ch.aonyx.broker.ib.api.data.historical.BarSizeUnit.HOUR;
import static ch.aonyx.broker.ib.api.data.historical.BarSizeUnit.MINUTE;
import static ch.aonyx.broker.ib.api.data.historical.BarSizeUnit.MINUTES;
import static ch.aonyx.broker.ib.api.data.historical.BarSizeUnit.SECOND;
import static ch.aonyx.broker.ib.api.data.historical.BarSizeUnit.SECONDS;

/**
 * @author Christophe Marcourt
 * @version 1.0.0
 */
public enum BarSize {

	ONE_SECOND(1, SECOND), FIVE_SECONDS(5, SECONDS), FIFTEEN_SECONDS(15, SECONDS), THIRTY_SECONDS(30, SECONDS), ONE_MINUTE(
			1, MINUTE), TWO_MINUTES(2, MINUTES), THREE_MINUTES(3, MINUTES), FIVE_MINUTES(5, MINUTES), FIFTEEN_MINUTES(
			15, MINUTES), THIRTY_MINUTES(30, MINUTES), ONE_HOUR(1, HOUR), ONE_DAY(1, DAY);

	private final int duration;
	private final BarSizeUnit barSizeUnit;

	private BarSize(final int duration, final BarSizeUnit barSizeUnit) {
		this.duration = duration;
		this.barSizeUnit = barSizeUnit;
	}

	public final int getDuration() {
		return duration;
	}

	public final BarSizeUnit getBarSizeUnit() {
		return barSizeUnit;
	}

	public final String getFormattedBarSize() {
		return duration + " " + barSizeUnit.getAbbreviation();
	}
}
