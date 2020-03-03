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

/**
 * Privilege rolls with levels 1-5 Reserved and User purpose set to level 100
 * (Lower) to leave room for higher custom levels.
 */
public class Privilege
{
    public static final int ROOT     = 1;
    public static final int OWNER    = 2;
    public static final int ADMIN    = 3;
    public static final int MANAGER  = 4;
    public static final int ENGINEER = 5;

    public static final int USER = 100;

    public static final int GUEST = Integer.MAX_VALUE;

    public static int validate(String level)
    {
        switch (level.toUpperCase().trim()) {
            case "ROOT":
                return ROOT;
            case "OWNER":
                return OWNER;
            case "ADMIN":
                return ADMIN;
            case "MANAGER":
                return MANAGER;
            case "ENGINEER":
                return ENGINEER;
            case "USER":
                return USER;
            case "GUEST":
                return GUEST;
            default:
                try {
                    int intLevel = Integer.parseInt(level);

                    if (intLevel >= ROOT && intLevel <= ENGINEER || intLevel == USER || intLevel == GUEST)
                        return intLevel;
                    else if (intLevel < 1)
                        throw new SkandersException("Privilege level is invalid, custom level must be greater than 0, given: " + intLevel);
                    else
                        return intLevel;

                } catch (NumberFormatException e) {
                    throw new SkandersException("Privilege level is invalid: " + level);

                }
        }
    }
}
