package com.batman.gexinoauth2.common.exception;


/**
 * 业务层统一异常对象
 */
public class BusinessException extends ResponseCodeAwareException {

    /**
     * 构造函数.
     *
     * @param code
     *            错误码
     * @param message
     *            错误消息
     */
    public BusinessException(Integer code, String message) {
        super(code, message);
    }

}
