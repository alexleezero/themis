/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.db.execoter;

import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.maishare.themis.common.exception.ComponentException;

import lombok.Getter;

/**
 * Redis 执行器
 * @author moushaokun
 * @version @Id: RedisExecutor.java, v 0.1 2020年03月25日 13:15 moushaokun Exp $
 */
public class RedisExecutor {

    @Getter
    private RedisTemplate<Object, Object> redisTemplate;

    private static RedisExecutor          redisExecutor;

    private RedisExecutor(RedisConfiguration configuration){
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory((RedisStandaloneConfiguration)configuration);
        lettuceConnectionFactory.afterPropertiesSet();

        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        redisTemplate.afterPropertiesSet();

        this.redisTemplate = redisTemplate;
    }

    public static RedisExecutor getInstance(RedisConfiguration configuration) {
        if (redisExecutor == null) {
            synchronized (RedisExecutor.class) {
                if (redisExecutor == null) {
                    if (configuration == null) {
                        throw new ComponentException("使用Redis组件请先配置redis");
                    }
                    redisExecutor = new RedisExecutor(configuration);
                }
            }
        }
        return redisExecutor;
    }

    public Object get(Object key) {
        return this.redisTemplate.opsForValue().get(key);
    }

    public void set(Object key, Object value) {
        this.redisTemplate.opsForValue().set(key, value);
    }

    public Boolean delete(Object key){
        return redisTemplate.delete(key);
    }
}
