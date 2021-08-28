/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.config.redis;

import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;

import com.maishare.themis.config.ThemisProps;

import lombok.Getter;

/**
 * Themis redis配置类
 * @author moushaokun
 * @version @Id: ThemisRedisConfiguration.java, v 0.1 2020年03月25日 14:02 moushaokun Exp $
 */
public class ThemisRedisConfiguration {

    private static final String  REDIS_HOST     = "redis.host";

    private static final String  REDIS_PORT     = "redis.port";

    private static final String  REDIS_PASSWORD = "redis.password";

    @Getter
    private RedisConfiguration config;

    private ThemisRedisConfiguration(){
        if (ThemisProps.getProps() == null) {
            return;
        }

        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(ThemisProps.getProps().getProperty(REDIS_HOST));
        configuration.setPassword(ThemisProps.getProps().getProperty(REDIS_PASSWORD));
        configuration.setPort(Integer.parseInt(ThemisProps.getProps().getProperty(REDIS_PORT)));

        this.config = configuration;
    }

    public static ThemisRedisConfiguration getInstance(){
        return ThemisRedisConfigurationInstanceHelper.configuration;
    }

    private static class ThemisRedisConfigurationInstanceHelper {
        private static ThemisRedisConfiguration configuration = new ThemisRedisConfiguration();
    }
}

