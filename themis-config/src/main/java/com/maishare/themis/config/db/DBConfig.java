/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.config.db;

import com.maishare.themis.extension.utils.ClassUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * 数据库连接配置
 * @author moushaokun
 * @version @Id: DBConfig.java, v 0.1 2020年03月21日 15:28 moushaokun Exp $
 */
public class DBConfig {
    public static final String  CONFIG_ROOT = "config";

    public static final String  CONFIG_FILE = "db.properties";

    public static final String  USER_NAME   = "userName";

    public static final String  PASSWORD    = "password";

    public static final String  URL_STRING  = "url";

    protected static Properties props;

    static {
        try {
            props = loadDbProperties();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String getProp(String key) {
        return props.getProperty(key);
    }

    private static Properties loadDbProperties() throws Exception {
        ClassLoader classLoader = ClassUtils.getClassLoader(DBConfig.class);
        URL location = classLoader.getResource(CONFIG_ROOT);
        if (location == null) {
            return null;
        }
        File root = new File(location.toURI());
        File platformFile = new File(root, CONFIG_FILE);

        if (!platformFile.exists()) {
            return null;
        }

        Properties props = new Properties();
        try (InputStream inStream = new FileInputStream(platformFile);) {
            props.load(inStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return props;
    }

}
