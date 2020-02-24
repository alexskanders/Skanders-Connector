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

import com.skanders.rms.base.config.RMSConfig;
import com.skanders.rms.base.config.RMSProperties;

public class ServiceConfig extends RMSConfig
{
    public ServiceConfig(RMSProperties prop)
    {
        super(prop);

        initIdmConnector(prop);
    }

    private void initIdmConnector(RMSProperties prop)
    {
        String idmUrl            = prop.getReqStr("idm.url");
        String idmSessionEP      = prop.getReqStr("idm.sessionEP");
        String idmPrivilegeEP    = prop.getReqStr("idm.privilegeEP");

        String idmTrustStoreFile = prop.getStr("idm.trustStoreFile");
        String idmTrustStorePass = prop.getStr("idm.trustStorePass");

        Service.initIdm(idmUrl, idmSessionEP, idmPrivilegeEP, idmTrustStoreFile, idmTrustStorePass);
    }
}
