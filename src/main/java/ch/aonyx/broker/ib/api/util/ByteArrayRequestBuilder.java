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

import org.apache.commons.lang3.StringUtils;

import com.google.common.primitives.Bytes;

/**
 * @author Christophe Marcourt
 * @since 1.0.0
 */
public final class ByteArrayRequestBuilder implements RequestBuilder {

    private static final byte[] EOL = { 0 };
    private byte[] bytes = new byte[] {};

    public ByteArrayRequestBuilder() {
    }

    @Override
    public RequestBuilder append(final int i) {
        if (i != Integer.MAX_VALUE) {
            bytes = Bytes.concat(bytes, String.valueOf(i).getBytes());
        }
        appendEol();
        return this;
    }

    private void appendEol() {
        bytes = Bytes.concat(bytes, EOL);
    }

    @Override
    public RequestBuilder append(final boolean b) {
        int i = 0;
        if (b) {
            i = 1;
        }
        bytes = Bytes.concat(bytes, String.valueOf(i).getBytes());
        appendEol();
        return this;
    }

    @Override
    public RequestBuilder append(final double d) {
        if (d != Double.MAX_VALUE) {
            bytes = Bytes.concat(bytes, String.valueOf(d).getBytes());
        }
        appendEol();
        return this;
    }

    @Override
    public RequestBuilder append(final String s) {
        if (StringUtils.isNotEmpty(s)) {
            bytes = Bytes.concat(bytes, s.getBytes());
        }
        appendEol();
        return this;
    }

    @Override
    public byte[] toBytes() {
        return bytes;
    }
}
