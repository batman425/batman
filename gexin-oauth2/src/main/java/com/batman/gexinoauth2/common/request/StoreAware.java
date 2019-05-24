package com.batman.gexinoauth2.common.request;

import java.io.Serializable;

/**
 * 保存前端状态接口.
 *
 * @author wuchenxi
 *
 */
public interface StoreAware extends Serializable {

    /**
     * 获取状态.
     *
     * @return
     */
    Object getStore();

    /**
     * 保存状态.
     *
     * @param object
     */
    void setStore(Object object);
}
