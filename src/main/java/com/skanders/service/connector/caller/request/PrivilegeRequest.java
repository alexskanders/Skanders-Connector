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
import com.skanders.rms.base.model.RequestModel;
import com.skanders.rms.util.result.Result;
import com.skanders.service.connector.caller.validate.CallerValidate;

public class PrivilegeRequest extends RequestModel
{
    @JsonProperty("endpoint")
    private String endpoint;

    @JsonCreator
    public PrivilegeRequest(
            @JsonProperty(value = "endpoint", required = true) String endpoint)
    {
        this.endpoint = endpoint;
    }

    public String getEndpoint()
    {
        return endpoint;
    }

    public void setEndpoint(String endpoint)
    {
        this.endpoint = endpoint;
    }

    public Result validate()
    {
        Result result;

        result = CallerValidate.endpoint(endpoint);
            if (result.notValid()) return result;

        return Result.VALID;
    }

    public static PrivilegeRequest createModel(String endpoint)
    {
        return new PrivilegeRequest(endpoint);
    }
}
