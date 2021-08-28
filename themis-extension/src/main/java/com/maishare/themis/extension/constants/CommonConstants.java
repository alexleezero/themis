/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.maishare.themis.extension.constants;

import java.util.regex.Pattern;

public interface CommonConstants {
    String  COMMA_SEPARATOR     = ",";

    Pattern COMMA_SPLIT_PATTERN = Pattern.compile("\\s*[,]+\\s*");

    String  PROTOCOL_KEY        = "protocol";

    String  REMOVE_VALUE_PREFIX = "-";

    String  DEFAULT_KEY         = "default";

    String  ANYHOST_KEY         = "anyhost";

    String  ANYHOST_VALUE       = "0.0.0.0";

    String  LOCALHOST_KEY       = "localhost";

    String  LOCALHOST_VALUE     = "127.0.0.1";

    String  METHODS_KEY         = "methods";

    String  GROUP_KEY           = "group";

    String  PATH_KEY            = "path";

    String  INTERFACE_KEY       = "interface";

    String  VERSION_KEY         = "version";

    String  USERNAME_KEY        = "username";
    String  PASSWORD_KEY        = "password";
    String  HOST_KEY            = "host";
    String  PORT_KEY            = "port";
    String  BACKUP_KEY          = "backup";
}
