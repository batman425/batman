package com.batman.gexinzuul.service;

/**
 * @author liusongwei
 * @Title: ICheckToken
 * @Description: TODO
 * @date 2018/11/1919:59
 */
public interface ICheckToken {
    public abstract String checkipandtoken(String key, String value, int expires);

    public abstract String gettokenvalue(String key);
}
