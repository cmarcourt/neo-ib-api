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
package ch.aonyx.broker.ib.api.data.scanner;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Christophe Marcourt
 * @version 1.0.0
 */
public final class MarketScannerFilter {

	private static final String EMPTY = "";
	private final static int NO_NUMBER_OF_ROWS_SPECIFIED = -1;
	private double abovePrice = Double.MAX_VALUE;
	private int aboveVolume = Integer.MAX_VALUE;
	private int aboveAverageVolumeOption = Integer.MAX_VALUE;
	private double belowPrice = Double.MAX_VALUE;
	private double aboveCouponRate = Double.MAX_VALUE;
	private double belowCouponRate = Double.MAX_VALUE;
	private String excludeConvertible = EMPTY;
	private String instrument = EMPTY;
	private String locationCode = EMPTY;
	private double aboveMarketCapitalization = Double.MAX_VALUE;
	private double belowMarketCapitalization = Double.MAX_VALUE;
	private String aboveMaturityDate = EMPTY;
	private String belowMaturityDate = EMPTY;
	private String aboveMoodyRating = EMPTY;
	private String belowMoodyRating = EMPTY;
	private int numberOfRows = NO_NUMBER_OF_ROWS_SPECIFIED;
	private String scannerCode = EMPTY;
	private String scannerSettingPairs = EMPTY;
	private String aboveSpRating = EMPTY;
	private String belowSpRating = EMPTY;
	private StockType stockType = StockType.EMPTY;

	public final double getAbovePrice() {
		return abovePrice;
	}

	public final void setAbovePrice(final double abovePrice) {
		this.abovePrice = abovePrice;
	}

	public final int getAboveVolume() {
		return aboveVolume;
	}

	public final void setAboveVolume(final int aboveVolume) {
		this.aboveVolume = aboveVolume;
	}

	public final int getAboveAverageVolumeOption() {
		return aboveAverageVolumeOption;
	}

	public final void setAboveAverageVolumeOption(final int averageOptionAboveVolume) {
		aboveAverageVolumeOption = averageOptionAboveVolume;
	}

	public final double getBelowPrice() {
		return belowPrice;
	}

	public final void setBelowPrice(final double belowPrice) {
		this.belowPrice = belowPrice;
	}

	public final double getAboveCouponRate() {
		return aboveCouponRate;
	}

	public final void setAboveCouponRate(final double couponAboveRate) {
		aboveCouponRate = couponAboveRate;
	}

	public final double getBelowCouponRate() {
		return belowCouponRate;
	}

	public final void setBelowCouponRate(final double couponBelowRate) {
		belowCouponRate = couponBelowRate;
	}

	public final String getExcludeConvertible() {
		return excludeConvertible;
	}

	public final void setExcludeConvertible(final String excludeConvertible) {
		this.excludeConvertible = excludeConvertible;
	}

	public final String getInstrument() {
		return instrument;
	}

	public final void setInstrument(final String instrument) {
		this.instrument = instrument;
	}

	public final String getLocationCode() {
		return locationCode;
	}

	public final void setLocationCode(final String locationCode) {
		this.locationCode = locationCode;
	}

	public final double getAboveMarketCapitalization() {
		return aboveMarketCapitalization;
	}

	public final void setAboveMarketCapitalization(final double aboveMarketCapitalization) {
		this.aboveMarketCapitalization = aboveMarketCapitalization;
	}

	public final double getBelowMarketCapitalization() {
		return belowMarketCapitalization;
	}

	public final void setBelowMarketCapitalization(final double belowMarketCapitalization) {
		this.belowMarketCapitalization = belowMarketCapitalization;
	}

	public final String getAboveMaturityDate() {
		return aboveMaturityDate;
	}

	public final void setAboveMaturityDate(final String aboveMaturityDate) {
		this.aboveMaturityDate = aboveMaturityDate;
	}

	public final String getBelowMaturityDate() {
		return belowMaturityDate;
	}

	public final void setBelowMaturityDate(final String belowMaturityDate) {
		this.belowMaturityDate = belowMaturityDate;
	}

	public final String getAboveMoodyRating() {
		return aboveMoodyRating;
	}

	public final void setAboveMoodyRating(final String aboveMoodyRating) {
		this.aboveMoodyRating = aboveMoodyRating;
	}

	public final String getBelowMoodyRating() {
		return belowMoodyRating;
	}

	public final void setBelowMoodyRating(final String belowMoodyRating) {
		this.belowMoodyRating = belowMoodyRating;
	}

	public final int getNumberOfRows() {
		return numberOfRows;
	}

	public final void setNumberOfRows(final int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

	public final String getScannerCode() {
		return scannerCode;
	}

	public final void setScannerCode(final String scannerCode) {
		this.scannerCode = scannerCode;
	}

	public final String getScannerSettingPairs() {
		return scannerSettingPairs;
	}

	public final void setScannerSettingPairs(final String scannerSettingPairs) {
		this.scannerSettingPairs = scannerSettingPairs;
	}

	public final String getAboveSpRating() {
		return aboveSpRating;
	}

	public final void setAboveSpRating(final String aboveSpRating) {
		this.aboveSpRating = aboveSpRating;
	}

	public final String getBelowSpRating() {
		return belowSpRating;
	}

	public final void setBelowSpRating(final String belowSpRating) {
		this.belowSpRating = belowSpRating;
	}

	public final StockType getStockType() {
		return stockType;
	}

	public final void setStockType(final StockType stockType) {
		this.stockType = stockType;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(aboveMarketCapitalization).append(aboveMaturityDate)
				.append(aboveMoodyRating).append(abovePrice).append(aboveSpRating).append(aboveVolume)
				.append(aboveAverageVolumeOption).append(belowMarketCapitalization).append(belowMaturityDate)
				.append(belowMoodyRating).append(belowPrice).append(belowSpRating).append(aboveCouponRate)
				.append(belowCouponRate).append(excludeConvertible).append(instrument).append(locationCode)
				.append(numberOfRows).append(scannerCode).append(scannerSettingPairs).append(stockType).toHashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		final MarketScannerFilter rhs = (MarketScannerFilter) obj;
		return new EqualsBuilder().append(aboveCouponRate, rhs.aboveCouponRate)
				.append(aboveMarketCapitalization, rhs.aboveMarketCapitalization)
				.append(aboveMaturityDate, rhs.aboveMaturityDate).append(aboveMoodyRating, rhs.aboveMoodyRating)
				.append(abovePrice, rhs.abovePrice).append(aboveSpRating, rhs.aboveSpRating)
				.append(aboveVolume, rhs.aboveVolume).append(aboveAverageVolumeOption, rhs.aboveAverageVolumeOption)
				.append(belowCouponRate, rhs.belowCouponRate)
				.append(belowMarketCapitalization, rhs.belowMarketCapitalization)
				.append(belowMaturityDate, rhs.belowMaturityDate).append(belowMoodyRating, rhs.belowMoodyRating)
				.append(belowPrice, rhs.belowPrice).append(belowSpRating, rhs.belowSpRating)
				.append(excludeConvertible, rhs.excludeConvertible).append(instrument, rhs.instrument)
				.append(locationCode, rhs.locationCode).append(numberOfRows, rhs.numberOfRows)
				.append(scannerCode, rhs.scannerCode).append(scannerSettingPairs, rhs.scannerSettingPairs)
				.append(stockType, rhs.stockType).isEquals();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
