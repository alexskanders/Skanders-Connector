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

import com.skanders.commons.bytes.SecureBytes;
import com.skanders.commons.def.Verify;
import com.skanders.service.connector.code.Constants;
import org.glassfish.grizzly.http.server.Request;

import javax.ws.rs.core.HttpHeaders;

public class CallerFactory
{
    public static Caller rebuildUser(HttpHeaders headers)
    {
        Verify.notNull(headers, "headers cannot be null");

        return new Caller(
                headers.getHeaderString(Caller.IP_ADDRESS),
                headers.getHeaderString(Caller.TRANSACTION_TOKEN),
                headers.getHeaderString(Caller.EMAIL),
                headers.getHeaderString(Caller.SESSION_TOKEN));
    }

    public static Caller rebuildGuest(HttpHeaders headers)
    {
        Verify.notNull(headers, "headers cannot be null");

        return new Caller(
                headers.getHeaderString(Caller.IP_ADDRESS),
                headers.getHeaderString(Caller.TRANSACTION_TOKEN));
    }

    public static Caller asUser(Request userRequest, HttpHeaders headers)
    {
        Verify.notNull(userRequest, "userRequest cannot be null");
        Verify.notNull(headers, "headers cannot be null");

        return new Caller(
                userRequest.getRemoteAddr(),
                SecureBytes.gen16(Constants.TRANSACTION_TOKEN_BYTES),
                headers.getHeaderString(Caller.EMAIL),
                headers.getHeaderString(Caller.SESSION_TOKEN));
    }

    public static Caller asGuest(Request userRequest)
    {
        Verify.notNull(userRequest, "userRequest cannot be null");

        return new Caller(userRequest.getRemoteAddr(), SecureBytes.gen16(Constants.TRANSACTION_TOKEN_BYTES));
    }
}
