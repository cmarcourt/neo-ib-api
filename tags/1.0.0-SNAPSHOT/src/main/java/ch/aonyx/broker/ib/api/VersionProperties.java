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

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Christophe Marcourt
 * @version 1.0.0
 */
final class VersionProperties {

	private static final Logger LOGGER = LoggerFactory.getLogger(VersionProperties.class);
	private static final String VERSIONS_FILE_NAME = "versions.properties";
	private static final String VERSION_CLIENT_CURRENT = "version.client.current";
	private static final String VERSION_SERVER_MINIMUM_REQUIRED = "version.server.minimum-required";
	private final Properties properties;

	VersionProperties() {
		properties = new Properties();
		readPropertiesFile();
	}

	private void readPropertiesFile() {
		try {
			properties.load(getClass().getResourceAsStream(VERSIONS_FILE_NAME));
		} catch (final IOException e) {
			LOGGER.error("", e);
		}
	}

	int getClientCurrentVersion() {
		return Integer.valueOf(properties.getProperty(VERSION_CLIENT_CURRENT));
	}

	int getServerMinimumRequiredVersion() {
		return Integer.valueOf(properties.getProperty(VERSION_SERVER_MINIMUM_REQUIRED));
	}
}
