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

import com.skanders.rms.base.result.Result;
import com.skanders.rms.def.logger.Pattern;
import com.skanders.service.connector.caller.CallerFactory;
import com.skanders.service.connector.caller.CallerResult;
import com.skanders.service.connector.common.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CallerValidate
{
    private static final Logger LOG = LoggerFactory.getLogger(CallerValidate.class);

    public static Result level(Integer level)
    {
        LOG.trace(Pattern.ENTER, "Validating Privilege Level Range");

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
        LOG.trace(Pattern.ENTER, "Validating  Email");

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
        LOG.trace(Pattern.ENTER, "Validating SessionToken");

        if (sessionToken == null) {
            return CallerResult.SESSION_TOKEN_MISSING;

        } else {
            return Result.VALID;

        }
    }

    public static Result transactionToken(String transactionToken)
    {
        LOG.trace(Pattern.ENTER, "Validating TransactionToken");

        if (transactionToken == null) {
            return CallerResult.TRANSACTION_TOKEN_MISSING;

        } else if (transactionToken.length() != CallerFactory.TRANSACTION_ID_LEN) {
            return CallerResult.TRANSACTION_INVALID_TOKEN;

        } else {
            return Result.VALID;

        }
    }

    public static Result ipAddress(String ipAddress)
    {
        LOG.trace(Pattern.ENTER, "Validating IP Address");

        if (ipAddress == null) {
            return CallerResult.IP_ADDRESS_MISSING;

        } else if (!Validate.INET_VALIDATOR.isValid(ipAddress)) {
            return CallerResult.IP_INVALID;

        } else {
            return Result.VALID;

        }
    }
}
