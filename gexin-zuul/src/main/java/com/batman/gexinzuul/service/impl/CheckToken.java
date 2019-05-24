package com.batman.gexinzuul.service.impl;

import com.batman.gexinzuul.redis.RedisManager;
import com.batman.gexinzuul.service.ICheckToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liusongwei
 * @Title: CheckToken
 * @Description: TODO
 * @date 2018/11/1919:56
 */
@Service
public class CheckToken implements ICheckToken {
    @Autowired
    private RedisManager redisManager;

    @Override
    public String checkipandtoken(String key,String value,int expires){
        //String uuid = UUID.randomUUID().toString();
        redisManager.set(key,value,expires);
        return value;
    }

    @Override
    public String gettokenvalue(String key) {
        return redisManager.get(key);
    }
}
