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

package com.skanders.service.connector;


import com.skanders.commons.config.Config;
import com.skanders.rms.config.RMSConfig;

public class ServiceConfig extends RMSConfig
{
    public ServiceConfig(Config prop)
    {
        super(prop);

        initIdmConnector(prop);
    }

    private void initIdmConnector(Config prop)
    {
        String idmUrl = prop.getReqStr("connector.idm.url");

        String idmTrustStoreFile = prop.getStr("connector.idm.trustStoreFile");
        String idmTrustStorePass = prop.getStr("connector.idm.trustStorePass");

        Service.initIdm(idmUrl, idmTrustStoreFile, idmTrustStorePass);
    }
}
