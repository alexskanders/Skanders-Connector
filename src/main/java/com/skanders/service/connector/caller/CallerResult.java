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

import javax.ws.rs.core.Response.Status;

public final class CallerResult
{
    /* Header Results */
    public static final Result SESSION_TOKEN_MISSING;
    public static final Result TRANSACTION_TOKEN_MISSING;
    public static final Result EMAIL_MISSING;
    public static final Result IP_ADDRESS_MISSING;

    public static final Result SESSION_INVALID_TOKEN;
    public static final Result TRANSACTION_INVALID_TOKEN;
    public static final Result EMAIL_INVALID;
    public static final Result IP_INVALID;

    /* Bad Request Codes */
    public static final Result ENDPOINT_INVALID;
    public static final Result PAYLOAD_EMPTY;
    public static final Result ENDPOINT_NOT_FOUND;

    /* Session Codes */
    public static final Result SESSION_ACTIVE;
    public static final Result SESSION_TIMED_OUT;
    public static final Result SESSION_REVOKED;
    public static final Result SESSION_EXPIRED;
    public static final Result SESSION_CLOSED ;
    public static final Result SESSION_NOT_FOUND;

    /* Privilege Codes */
    public static final Result CALLER_LEVEL_SUFFICIENT;
    public static final Result CALLER_LEVEL_INSUFFICIENT;

    /* Caller Codes */
    public static final Result CALLER_EMAIL_NOT_FOUND;
    public static final Result MISMATCHED_CALLER;

    static
    {
        SESSION_TOKEN_MISSING     = Result.declare(-110, "Session Token not provided",     Status.BAD_REQUEST);
        TRANSACTION_TOKEN_MISSING = Result.declare(-111, "Transaction Token not provided", Status.BAD_REQUEST);
        EMAIL_MISSING             = Result.declare(-112, "Email not provided",             Status.BAD_REQUEST);
        IP_ADDRESS_MISSING        = Result.declare(-113, "IP Address not provided",        Status.BAD_REQUEST);

        SESSION_INVALID_TOKEN     = Result.declare(-120, "Session Token is not valid",     Status.BAD_REQUEST);
        TRANSACTION_INVALID_TOKEN = Result.declare(-121, "Transaction Token is not valid", Status.BAD_REQUEST);
        EMAIL_INVALID             = Result.declare(-122, "Email is not valid",             Status.BAD_REQUEST);
        IP_INVALID                = Result.declare(-123, "IP Address is not valid",        Status.BAD_REQUEST);

        ENDPOINT_INVALID          = Result.declare(-130, "Endpoint is missing",           Status.BAD_REQUEST);
        PAYLOAD_EMPTY             = Result.declare(-131, "Payload Was Empty",              Status.BAD_REQUEST);
        ENDPOINT_NOT_FOUND        = Result.declare(-132, "No endpoint found",              Status.BAD_REQUEST);

        SESSION_ACTIVE            = Result.declare(110, "User Session is active");
        SESSION_TIMED_OUT         = Result.declare(111, "User Session is timed out");
        SESSION_REVOKED           = Result.declare(112, "User Session is revoked");
        SESSION_EXPIRED           = Result.declare(113, "User Session is expired");
        SESSION_CLOSED            = Result.declare(114, "User Session is closed");
        SESSION_NOT_FOUND         = Result.declare(115, "User Session not found");

        CALLER_LEVEL_SUFFICIENT   = Result.declare(120, "User has sufficient privilege level");
        CALLER_LEVEL_INSUFFICIENT = Result.declare(121, "User has insufficient privilege level");

        CALLER_EMAIL_NOT_FOUND    = Result.declare(130, "User email not found");
        MISMATCHED_CALLER         = Result.declare(131, "User and requester are different");
    }

}
