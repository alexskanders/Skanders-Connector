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

import com.skanders.rms.base.model.RequestModel;
import com.skanders.rms.base.model.ResponseModel;
import com.skanders.rms.util.result.Result;
import com.skanders.service.connector.caller.Caller;
import org.glassfish.jersey.SslConfigurator;

import javax.net.ssl.SSLContext;

public class Commons
{
    public static final String PROTOCOL = "TLSv1.2";

    public static SSLContext createSSLContext(String trustStoreFile, String trustStorePass)
    {
        SslConfigurator configurator = SslConfigurator.newInstance()
                .trustStoreFile(trustStoreFile)
                .trustStorePassword(trustStorePass)
                .securityProtocol(PROTOCOL);

        return configurator.createSSLContext();
    }

    public
    <Req extends RequestModel, Res extends ResponseModel>
    boolean validate(Caller caller, Req request, Res response)
    {
        Result result;

        result = caller.validate();
        if (result.notValid(response))
            return true;

        result = request.validate();
        return result.notValid(response);
    }

    public
    <Res extends ResponseModel>
    boolean validate(Caller caller, Res response)
    {
        Result result;

        result = caller.validate();
        return result.notValid(response);
    }
}
