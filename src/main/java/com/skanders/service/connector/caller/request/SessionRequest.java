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

package com.skanders.service.connector.caller.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skanders.jbel.model.RequestModel;
import com.skanders.jbel.result.Result;
import com.skanders.service.connector.caller.Caller;
import com.skanders.service.connector.caller.validate.CallerValidate;

public class SessionRequest extends RequestModel
{
    @JsonProperty("email")
    private final String email;
    @JsonProperty("sessionToken")
    private final String sessionToken;

    @JsonCreator
    public SessionRequest(
            @JsonProperty(value = "email", required = true) String email,
            @JsonProperty(value = "sessionToken", required = true) String sessionToken)
    {
        this.email        = email;
        this.sessionToken = sessionToken;
    }

    public String getEmail()
    {
        return email;
    }

    public String getSessionToken()
    {
        return sessionToken;
    }

    public Result validate()
    {
        Result result;

        result = CallerValidate.sessionToken(sessionToken);
        if (result.notValid()) return result;

        result = CallerValidate.email(email);
        if (result.notValid()) return result;

        return Result.VALID;
    }

    public static SessionRequest createModel(Caller caller)
    {
        return new SessionRequest(caller.getEmail(), caller.getSessionToken());
    }
}
