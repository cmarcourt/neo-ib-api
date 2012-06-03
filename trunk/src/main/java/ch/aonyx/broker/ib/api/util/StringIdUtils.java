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
package ch.aonyx.broker.ib.api.util;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import ch.aonyx.broker.ib.api.contract.Contract;
import ch.aonyx.broker.ib.api.execution.ExecutionReportFilter;
import ch.aonyx.broker.ib.api.order.Order;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author Christophe Marcourt
 * @version 1.0.0
 */
public final class StringIdUtils {

	private static final char SEPARATOR = '_';
	private static final List<String> UNIQUE_IDS = Lists.newArrayList();
	private static final Map<Contract, String> CONTRACT_ID_CACHE = Maps.newHashMap();

	private StringIdUtils() {
	}

	/**
	 * Random string id based on {@link UUID}.
	 */
	public static final String uniqueRandomId() {
		final String id = UUID.randomUUID().toString();
		if (UNIQUE_IDS.contains(id)) {
			return uniqueRandomId();
		}
		UNIQUE_IDS.add(id);
		return id;
	}

	/**
	 * String id based on {@link Contract} properties concatenation
	 */
	public static final String uniqueIdFromContract(final Contract contract) {
		if (CONTRACT_ID_CACHE.containsKey(contract)) {
			return CONTRACT_ID_CACHE.get(contract);
		}
		final StringBuilder builder = new StringBuilder();
		appendCommonTrunk(contract, builder);
		appendExtendedValue(contract.getMultiplier(), builder);
		appendExtendedValue(contract.getExpiry(), builder);
		appendExtendedValue(contract.getOptionRight().getName(), builder);
		final double strike = contract.getStrike();
		if (strike > 0) {
			appendExtendedValue(String.valueOf(strike), builder);
		}
		final String id = builder.toString();
		CONTRACT_ID_CACHE.put(contract, id);
		return id;
	}

	private static void appendCommonTrunk(final Contract contract, final StringBuilder builder) {
		builder.append(contract.getSecurityType().getAbbreviation());
		builder.append(SEPARATOR);
		builder.append(contract.getCurrencyCode());
		builder.append(SEPARATOR);
		builder.append(contract.getExchange());
		builder.append(SEPARATOR);
		builder.append(contract.getSymbol());
	}

	private static void appendExtendedValue(final String value, final StringBuilder builder) {
		if (StringUtils.isNotEmpty(value)) {
			builder.append(SEPARATOR);
			builder.append(value);
		}
	}

	/**
	 * String id based on {@link ExecutionReportFilter} properties concatenation
	 */
	public static final String uniqueIdFromExecutionReportFilter(final ExecutionReportFilter filter) {
		final StringBuilder builder = new StringBuilder();
		builder.append(filter.getAccountNumber());
		builder.append(SEPARATOR);
		builder.append(filter.getClientId());
		appendExtendedValue(filter.getSecurityType().getAbbreviation(), builder);
		builder.append(SEPARATOR);
		builder.append(filter.getExchange());
		builder.append(SEPARATOR);
		builder.append(filter.getSymbol());
		appendExtendedValue(filter.getOrderAction().getAbbreviation(), builder);
		return builder.toString();
	}

	/**
	 * String id based on {@link Order} and {@link Contract} properties
	 * concatenation
	 */
	public static final String uniqueIdFromOrderAndContract(final Order order, final Contract contract) {
		final StringBuilder builder = new StringBuilder("@");
		builder.append(System.currentTimeMillis());
		builder.append(SEPARATOR);
		builder.append(order.getAccountName());
		builder.append(SEPARATOR);
		builder.append(order.getClientId());
		builder.append(SEPARATOR);
		builder.append(order.getAction());
		builder.append(SEPARATOR);
		builder.append(order.getTotalQuantity());
		builder.append(SEPARATOR);
		builder.append(uniqueIdFromContract(contract));
		builder.append(SEPARATOR);
		builder.append(order.getOrderType());
		double price = order.getLimitPrice();
		if (price > 0) {
			appendExtendedValue(String.valueOf(price), builder);
		}
		price = order.getStopPrice();
		if (price > 0) {
			appendExtendedValue(String.valueOf(price), builder);
		}
		return builder.toString();
	}
}
