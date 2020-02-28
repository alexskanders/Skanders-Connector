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

public class Constants
{
    public static final int EMAIL_MIN = 4;
    public static final int EMAIL_MAX = 64;

    public static final int PASSWORD_MIN = 8;
    public static final int PASSWORD_MAX = 32;

    public static final int SESSION_TOKEN_BITS    = 512;
    public static final int SESSION_TOKEN_BYTES   = SESSION_TOKEN_BITS / 8;
    public static final int SESSION_TOKEN_HEX_LEN = SESSION_TOKEN_BYTES * 2;

    public static final int TRANSACTION_TOKEN_BITS    = 512;
    public static final int TRANSACTION_TOKEN_BYTES   = TRANSACTION_TOKEN_BITS / 8;
    public static final int TRANSACTION_TOKEN_HEX_LEN = TRANSACTION_TOKEN_BYTES * 2;
}
