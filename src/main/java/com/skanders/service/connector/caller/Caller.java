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

package com.skanders.service.connector.caller;

import com.skanders.rms.util.result.Result;
import com.skanders.service.connector.caller.validate.CallerValidate;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.ws.rs.core.MultivaluedHashMap;

public class Caller
{
    private static final Logger LOG = LoggerFactory.getLogger(Caller.class);

    public static final String IP_ADDRESS        = "ip-address";
    public static final String EMAIL             = "email";
    public static final String TRANSACTION_TOKEN = "transaction-token";
    public static final String SESSION_TOKEN     = "session-token";

    public enum Type { USER, VISITOR }

    private String ipAddress;
    private String transactionToken;
    private String email;
    private String sessionToken;

    private boolean sessionTokenUpdated;

    private final Type type;

    Caller(String ipAddress, String transactionToken)
    {
        LOG.trace("Creating caller as a GUEST");

        this.ipAddress = ipAddress;
        this.transactionToken = transactionToken;
        this.email = null;
        this.sessionToken = null;

        this.sessionTokenUpdated = false;

        this.type = Type.VISITOR;
    }

    Caller(String ipAddress, String transactionToken, String email, String sessionToken)
    {
        LOG.trace("Creating caller as a USER");

        this.ipAddress = ipAddress;
        this.transactionToken = transactionToken;
        this.email = email;
        this.sessionToken = sessionToken;

        this.sessionTokenUpdated = false;

        this.type = Type.USER;
    }

    public String getIpAddress()
    {
        return ipAddress;
    }

    public String getTransactionToken()
    {
        return transactionToken;
    }

    public String getEmail()
    {
        return email;
    }

    public String getSessionToken()
    {
        return sessionToken;
    }

    public boolean isSessionTokenUpdated()
    {
        return sessionTokenUpdated;
    }

    public Type getType()
    {
        return type;
    }

    public void updateSessionToken(String sessionToken)
    {
        this.sessionToken = sessionToken;
        this.sessionTokenUpdated = true;
    }

    public Result validate()
    {
        return type == Type.VISITOR ? validateVisitor() : validateUser();
    }

    private Result validateUser()
    {
        LOG.trace("Caller validating as user");

        Result result;

        result = validateVisitor();
            if (result.notValid()) return result;

        result = CallerValidate.email(email);
            if (result.notValid()) return result;

        result = CallerValidate.sessionToken(sessionToken);
            if (result.notValid()) return result;

        return Result.VALID;
    }

    private Result validateVisitor()
    {
        LOG.trace("Caller validating as visitor");

        Result result;

        result = CallerValidate.ipAddress(ipAddress);
            if (result.notValid()) return result;

        result = CallerValidate.transactionToken(transactionToken);
            if (result.notValid()) return result;

        return Result.VALID;
    }

    public MultivaluedHashMap<String, Object> toHeader()
    {
        MultivaluedHashMap<String, Object> headers = new MultivaluedHashMap<>();
            headers.add(IP_ADDRESS, ipAddress);
            headers.add(TRANSACTION_TOKEN, transactionToken);

        if (type == Type.USER) {
            headers.add(EMAIL, email);
            headers.add(SESSION_TOKEN, sessionToken);

        }

        return headers;
    }

    public MultivaluedHashMap<String, Object> toSessionHeader()
    {
        MultivaluedHashMap<String, Object> headers = new MultivaluedHashMap<>();

        if (isSessionTokenUpdated())
            headers.add(SESSION_TOKEN, sessionToken);

        return headers;
    }
}
