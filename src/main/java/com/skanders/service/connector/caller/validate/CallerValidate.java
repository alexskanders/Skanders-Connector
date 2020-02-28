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

package com.skanders.service.connector.caller.validate;

import com.skanders.commons.def.LogPattern;
import com.skanders.commons.result.Result;
import com.skanders.service.connector.caller.CallerResult;
import com.skanders.service.connector.code.Constants;
import com.skanders.service.connector.common.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CallerValidate
{
    private static final Logger LOG = LoggerFactory.getLogger(CallerValidate.class);

    public static Result level(Integer level)
    {
        LOG.trace(LogPattern.ENTER, "Validating Privilege Level Range");

        if (level == null) {
            return CallerResult.PRIVILEGE_LEVEL_INVALID;

        } else if (level == 0) {
            return CallerResult.PRIVILEGE_LEVEL_INVALID;

        } else {
            return Result.VALID;

        }
    }

    public static Result email(String email)
    {
        LOG.trace(LogPattern.ENTER, "Validating  Email");

        if (email == null) {
            return CallerResult.EMAIL_MISSING;

        } else if (!Validate.EMAIL_VALIDATOR.isValid(email)) {
            return CallerResult.EMAIL_INVALID;

        } else {
            return Result.VALID;

        }
    }

    public static Result sessionToken(String sessionToken)
    {
        LOG.trace(LogPattern.ENTER, "Validating SessionToken");

        if (sessionToken == null) {
            return CallerResult.SESSION_TOKEN_MISSING;

        } else if (sessionToken.length() != Constants.SESSION_TOKEN_HEX_LEN) {
            return CallerResult.SESSION_INVALID_TOKEN;

        } else {
            return Result.VALID;

        }
    }

    public static Result transactionToken(String transactionToken)
    {
        LOG.trace(LogPattern.ENTER, "Validating TransactionToken");

        if (transactionToken == null) {
            return CallerResult.TRANSACTION_TOKEN_MISSING;

        } else if (transactionToken.length() != Constants.TRANSACTION_TOKEN_HEX_LEN) {
            return CallerResult.TRANSACTION_INVALID_TOKEN;

        } else {
            return Result.VALID;

        }
    }

    public static Result ipAddress(String ipAddress)
    {
        LOG.trace(LogPattern.ENTER, "Validating IP Address");

        if (ipAddress == null) {
            return CallerResult.IP_ADDRESS_MISSING;

        } else if (!Validate.INET_VALIDATOR.isValid(ipAddress)) {
            return CallerResult.IP_INVALID;

        } else {
            return Result.VALID;

        }
    }
}
