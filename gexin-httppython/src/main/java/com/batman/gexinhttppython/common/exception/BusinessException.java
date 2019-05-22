package com.batman.gexinhttppython.common.exception;

/**
 * @author liusongwei
 * @Title: BusinessException
 * @ProjectName batman
 * @Description: TODO
 * @date 2019/5/2214:09
 */
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
        super(String.valueOf(code), message);
    }

}
