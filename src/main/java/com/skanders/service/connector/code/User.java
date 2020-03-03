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

package com.skanders.service.connector.code;

import com.skanders.jbel.def.SkandersException;

public class User
{
    public static final int ACTIVE = 1;
    public static final int LOCKED = 2;
    public static final int CLOSED = 3;

    public static int validate(String level)
    {
        switch (level.toUpperCase().trim()) {
            case "ACTIVE":
                return ACTIVE;
            case "LOCKED":
                return LOCKED;
            case "CLOSED":
                return CLOSED;
            default:
                throw new SkandersException("User level is invalid: " + level);
        }
    }
}
