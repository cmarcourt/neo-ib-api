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

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ch.aonyx.broker.ib.api.AbstractEventSupport;
import ch.aonyx.broker.ib.api.Id;
import ch.aonyx.broker.ib.api.contract.ContractSpecification;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class MarketScannerDataEvent extends AbstractEventSupport {

    private final int ranking;
    private final ContractSpecification contractSpecification;
    private final String distance;
    private final String benchmark;
    private final String projection;
    private final String comboLegDescription;

    public MarketScannerDataEvent(final Id requestId, final int ranking,
            final ContractSpecification contractSpecification, final String distance, final String benchmark,
            final String projection, final String comboLegDescription) {
        super(requestId);
        this.ranking = ranking;
        this.contractSpecification = contractSpecification;
        this.distance = distance;
        this.benchmark = benchmark;
        this.projection = projection;
        this.comboLegDescription = comboLegDescription;
    }

    public int getRanking() {
        return ranking;
    }

    public ContractSpecification getContractSpecification() {
        return contractSpecification;
    }

    public String getDistance() {
        return distance;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public String getProjection() {
        return projection;
    }

    public String getComboLegDescription() {
        return comboLegDescription;
    }

    @Override
    public Class<?> getAssignableListenerType() {
        return MarketScannerDataEventListener.class;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
