/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.config.redis;

import com.maishare.themis.config.ThemisProps;
import lombok.Getter;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Themis redis配置类
 * @author moushaokun
 * @version @Id: ThemisRedisConfiguration.java, v 0.1 2020年03月25日 14:02 moushaokun Exp $
 */
public class ThemisRedisConfiguration {

    private static final String PROP_KEY_REDIS_HOST = "redis.host";
    private static final String PROP_KEY_REDIS_PORT = "redis.port";
    private static final String PROP_KEY_REDIS_PASSWORD = "redis.password";
    private static final String PROP_KEY_REDIS_CLUSTER_NODES = "redis.cluster.nodes";
    private static final String PROP_KEY_REDIS_SENTINEL_NODES = "redis.sentinel.nodes";
    private static final String PROP_KEY_REDIS_SENTINEL_MASTER_ID = "redis.sentinel.master-id";
    private static final String PROP_KEY_REDIS_CONN_TYPE = "redis.conn-type";

    public enum RedisConnType {
        STANDALONE("standalone") {
            @Override
            public RedisConfiguration getConfiguration() {
                String host = ThemisProps.getProps().getProperty(PROP_KEY_REDIS_HOST, null);
                String password = ThemisProps.getProps().getProperty(PROP_KEY_REDIS_PASSWORD, null);
                String port = ThemisProps.getProps().getProperty(PROP_KEY_REDIS_PORT, null);
                if (host == null || null == port) {
                    throw new RuntimeException("redis standalone props config is null!");
                }
                RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
                configuration.setHostName(host);
                configuration.setPassword(password);
                configuration.setPort(Integer.parseInt(port));
                return configuration;
            }
        }, SENTINEL("sentinel") {
            @Override
            public RedisConfiguration getConfiguration() {
                RedisSentinelConfiguration configuration = new RedisSentinelConfiguration();
                String masterId = ThemisProps.getProps().getProperty(PROP_KEY_REDIS_SENTINEL_MASTER_ID, null);
                String sentinelNodes = ThemisProps.getProps().getProperty(PROP_KEY_REDIS_SENTINEL_NODES, null);
                String password = ThemisProps.getProps().getProperty(PROP_KEY_REDIS_PASSWORD, null);
                if (masterId == null || "".equals(masterId) || null == sentinelNodes || "".equals(sentinelNodes)) {
                    throw new RuntimeException("redis sentinel props config is null!");
                }
                List<RedisNode> redisNodes;
                try {
                    redisNodes = Arrays.stream(sentinelNodes.split(",")).map(hostAndPort -> {
                        String host = hostAndPort.split(":")[0];
                        String port = hostAndPort.split(":")[1];
                        return new RedisNode(host, Integer.parseInt(port));
                    }).collect(Collectors.toList());

                    if (redisNodes.size() == 0) {
                        throw new IllegalArgumentException("redis sentinel props config redis.sentinel.nodes is illegal");
                    }
                } catch (Exception e) {
                    throw new IllegalArgumentException("redis sentinel props config redis.sentinel.nodes is illegal");
                }
                configuration.setSentinels(redisNodes);
                configuration.setMaster(masterId);
                configuration.setPassword(password);
                return configuration;
            }
        }, CLUSTER("cluster") {
            @Override
            public RedisConfiguration getConfiguration() {
                RedisClusterConfiguration configuration = new RedisClusterConfiguration();
                String clusterNodes = ThemisProps.getProps().getProperty(PROP_KEY_REDIS_CLUSTER_NODES, null);
                String password = ThemisProps.getProps().getProperty(PROP_KEY_REDIS_PASSWORD, null);
                if (null == clusterNodes || "".equals(clusterNodes)) {
                    throw new RuntimeException("redis sentinel props config is null!");
                }
                List<RedisNode> redisNodes;
                try {
                    redisNodes = Arrays.stream(clusterNodes.split(",")).map(hostAndPort -> {
                        String host = hostAndPort.split(":")[0];
                        String port = hostAndPort.split(":")[1];
                        return new RedisNode(host, Integer.parseInt(port));
                    }).collect(Collectors.toList());

                    if (redisNodes.size() == 0) {
                        throw new IllegalArgumentException("redis cluster props config redis.sentinel.nodes is illegal");
                    }
                } catch (Exception e) {
                    throw new IllegalArgumentException("redis cluster props config redis.sentinel.nodes is illegal");
                }
                configuration.setClusterNodes(redisNodes);
                configuration.setPassword(password);
                return configuration;
            }
        };
        @Getter
        private final String code;

        RedisConnType(String code) {
            this.code = code;
        }

        public abstract RedisConfiguration getConfiguration();

        public static RedisConnType getRedisConnType(String code) {
            if (null == code || "".equals(code)) {
                return null;
            }
            for (RedisConnType redisConnType : values()) {
                if (redisConnType.code.equals(code)) {
                    return redisConnType;
                }
            }
            return null;
        }

    }

    @Getter
    private RedisConfiguration config;

    private ThemisRedisConfiguration(){
        if (ThemisProps.getProps() == null) {
            return;
        }

        String redisConnType = ThemisProps.getProps().getProperty(PROP_KEY_REDIS_CONN_TYPE, null);
        RedisConnType redisConnTypeStrategy = RedisConnType.getRedisConnType(redisConnType);
        if (redisConnTypeStrategy == null) {
            redisConnTypeStrategy = RedisConnType.STANDALONE;
        }

        this.config = redisConnTypeStrategy.getConfiguration();
    }



    public static ThemisRedisConfiguration getInstance(){
        return ThemisRedisConfigurationInstanceHelper.configuration;
    }

    private static class ThemisRedisConfigurationInstanceHelper {
        private static ThemisRedisConfiguration configuration = new ThemisRedisConfiguration();
    }

}

