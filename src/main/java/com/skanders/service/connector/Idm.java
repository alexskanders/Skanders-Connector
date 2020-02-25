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

import com.skanders.rms.base.result.Result;
import com.skanders.rms.def.verify.RMSVerify;
import com.skanders.rms.util.socket.ServiceSocket;
import com.skanders.rms.util.socket.ServiceSocketFactory;
import com.skanders.service.connector.caller.Caller;
import com.skanders.service.connector.caller.CallerResult;
import com.skanders.service.connector.caller.response.PrivilegeResponse;
import com.skanders.service.connector.caller.response.SessionResponse;
import com.skanders.service.connector.common.Commons;

import javax.annotation.Nonnull;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.Response;

public class Idm
{
    private static final String sessionEP = "idm/caller/session";
    private static final String privilegeEP = "idm/caller/privilege";

    private ServiceSocketFactory socketFactory;

    Idm()
    {
    }

    void init(String url, String trustStoreFile, String trustStorePass)
    {
        this.socketFactory = ServiceSocketFactory.createFactory(url, MediaType.APPLICATION_JSON_TYPE);

        if (trustStoreFile != null && trustStorePass != null)
            this.socketFactory.withSSLContext(Commons.createSSLContext(trustStoreFile, trustStorePass));
    }

    public Result session(@Nonnull Caller caller)
    {
        RMSVerify.checkNull(caller, "caller cannot be null");

        if (caller.getType() == Caller.Type.GUEST)
            return Result.VALID;

        SessionResponse sessionRM = checkSession(caller);
        Result result = sessionRM.getResult();

        if (!result.equals(CallerResult.SESSION_ACTIVE))
            return result;

        caller.updateSessionToken(sessionRM.getSessionToken());

        return Result.VALID;
    }

    public Result privilege(@Nonnull Caller caller, @Nonnull Integer level)
    {
        RMSVerify.checkNull(caller, "caller cannot be null");

        PrivilegeResponse privilegeRM = checkPrivilege(caller, level);
        Result result = privilegeRM.getResult();

        if (!result.equals(CallerResult.CALLER_LEVEL_SUFFICIENT))
            return result;

        return Result.VALID;
    }

    public ServiceSocketFactory getSocketFactory()
    {
        return socketFactory;
    }

    private PrivilegeResponse checkPrivilege(@Nonnull Caller caller, @Nonnull Integer level)
    {
        RMSVerify.checkNull(caller, "caller cannot be null");

        MultivaluedHashMap<String, Object> queries = new MultivaluedHashMap<>();
        queries.add("level", level);

        ServiceSocket socket = socketFactory.createSocket(privilegeEP)
                .headers(caller.toHeader())
                .queries(queries);

        try (Response response = socket.get()) {
            return response.readEntity(PrivilegeResponse.class);
        }
    }

    private SessionResponse checkSession(@Nonnull Caller caller)
    {
        RMSVerify.checkNull(caller, "caller cannot be null");
        RMSVerify.argument(caller.getType() == Caller.Type.GUEST, "Cannot call checkSession() on Visitor");

        ServiceSocket socket = socketFactory.createSocket(sessionEP)
                .headers(caller.toHeader());

        try (Response response = socket.get()) {
            return response.readEntity(SessionResponse.class);
        }
    }
}
