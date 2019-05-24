package com.batman.gexinzuul.redis.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author liusongwei
 * @Title: RedisCacheConfiguration
 * @Description: TODO
 * @date 2018/11/2010:19
 */
@Configuration
@EnableCaching
public class RedisCacheConfiguration extends CachingConfigurerSupport {
    private static Logger logger = LoggerFactory.getLogger(RedisCacheConfiguration.class);

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.max-total}")
    private int maxTotal;

    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public JedisPool redisPoolFactory() {
        logger.info("JedisPool注入成功 redis地址：" + host + ":" + port);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大空闲连接数, 应用自己评估，不要超过ApsaraDB for Redis每个实例最大的连接数
        jedisPoolConfig.setMaxIdle(maxIdle);
        //最大连接数, 应用自己评估，不要超过ApsaraDB for Redis每个实例最大的连接数
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setTestOnBorrow(false);
        jedisPoolConfig.setTestOnReturn(false);
        JedisPool pool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        return pool;
    }
}
