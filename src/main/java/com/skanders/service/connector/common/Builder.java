/*
 * Copyright (c) 2020 Alexander Iskander
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

package com.skanders.service.connector.common;

import org.glassfish.jersey.SslConfigurator;

import javax.net.ssl.SSLContext;

public class Builder
{
    private static final String PROTOCOL = "TLSv1.2";

    /**
     * Attaches a SSLContext truststore to the ServiceSocketFactory.
     * <p>
     * Uses a default protocol of TLSv1.2.
     * <p>
     * Replaces any SSLContext on the socket.
     *
     * @param trustStoreFile truststore file location
     * @param trustStorePass truststore file password
     * @return returns this instance of ServiceSocketFactory
     */
    public static SSLContext sslContext(String trustStoreFile, String trustStorePass)
    {
        SslConfigurator configurator = SslConfigurator.newInstance()
                .trustStoreFile(trustStoreFile)
                .trustStorePassword(trustStorePass)
                .securityProtocol(PROTOCOL);

        return configurator.createSSLContext();
    }
}
