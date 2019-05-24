package com.batman.gexinzuul.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Iterator;
import java.util.Set;

/**
 * @author liusongwei
 * @Title: RedisManager
 * @Description: TODO
 * @date 2018/11/209:49
 */
@Service
public class RedisManager {
    // 0-表示永远不过期
    private int expire = 0;

   // @Autowired
   // private RedisCacheConfiguration redisCacheConfiguration;

    @Autowired
    JedisPool jedisPool;
    public RedisManager() {
     //   jedisPool = redisCacheConfiguration.redisPoolFactory();
    }

    /**
     * @Title: get
     * @Description: 根据key来获得一条特定的缓存数据
     * @param @param key 序列化后的key
     * @param @return    设定文件
     * @return byte[]    返回类型
     * @throws
     */
    public byte[] get(byte[] key) {
        byte[] value = null;
        Jedis jedis = jedisPool.getResource();
        try {
            value = jedis.get(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * @Title: get
     * @Description: 根据key来获得一条特定的缓存数据
     * @param @param key string类型的key
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public String get(String key) {
        String value = null;
        Jedis jedis = jedisPool.getResource();
        try {
            value = jedis.get(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * @Title: set
     * @Description: 向redis数据库中缓存数据，key，value为字符串类型，缓存时间为永不过期
     * @param @param key
     * @param @param value
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
            if (this.expire != 0) {
                jedis.expire(key, this.expire);
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * @Title: set
     * @Description: 向redis数据库中缓存数据，key，value都为字符串的类型
     * @param @param key
     * @param @param value
     * @param @param expire 0为永不过期，其他时间则会设置对应的过期时间
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public String set(String key, String value, int expire) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
            if (expire != 0) {
                jedis.expire(key, expire);
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * @Title: del
     * @Description: 根据byte数组（已经序列化的key）来删除redis数据库中缓存的数据
     * @param @param key 设定文件
     * @return void 返回类型
     * @throws
     */
    public void del(byte[] key) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.del(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * @Title: del
     * @Description: 根据特定的string类型的key来删除redis数据库中的缓存数据
     * @param @param key 设定文件
     * @return void 返回类型
     * @throws
     */
    public void del(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.del(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * @Title: flushDB
     * @Description: 清除指定redis数据库中的数据
     * @param
     * @return void 返回类型
     * @throws
     */
    public void flushDB() {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.flushDB();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * @Title: dbSize
     * @Description: 获得redis缓存数据的大小
     * @param @return 设定文件
     * @return Long 返回类型
     * @throws
     */
    public Long dbSize() {
        Long dbSize = 0L;
        Jedis jedis = jedisPool.getResource();
        try {
            dbSize = jedis.dbSize();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return dbSize;
    }

    /**
     * @Title: keys
     * @Description: 根据泛型来查询所有符合泛型的缓存数据
     * @param @param pattern
     * @param @return 设定文件
     * @return Set<byte[]> 返回类型
     * @throws
     */
    public Set<byte[]> keys(String pattern) {
        Set<byte[]> keys = null;
        Jedis jedis = jedisPool.getResource();
        try {
            keys = jedis.keys(pattern.getBytes());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return keys;
    }

    /**
     * @Title: dels
     * @Description: 根据提供的泛型来删除reids中缓存的数据
     * @param @param pattern 设定文件
     * @return void 返回类型
     * @throws
     */
    public void dels(String pattern) {
        Set<byte[]> keys = null;
        Jedis jedis = jedisPool.getResource();
        try {
            keys = jedis.keys(pattern.getBytes());
            Iterator<byte[]> ito = keys.iterator();
            while (ito.hasNext()) {
                jedis.del(ito.next());
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
