package com.batman.gexinoauth2.common.response;

/**
 * 前后端交互的状态码.
 *
 */
public final class ResponseCode {

    private ResponseCode() {
    }

    /**
     * 成功状态码,在返回对象的meta.
     */
    public static final Integer SUCCESS = 200;

    /**
     * 系统未知异常提示信息.
     */
    public static final String SYSTEM_ERROR_MSG = "系统繁忙，请稍后再试.";

    /**
     * 错误状态码,在返回对象的meta.
     */
    public static final Integer ERROR = 500;

    /**
     * 错误状态码,在返回对象的meta.
     */
    public static final Integer UNKNOW_ERROR = 500;

    /**
     * 请求参数错误统一状态码.
     */
    public static final Integer BAD_REQUEST = 400;

    /**
     * 没有认证通过， 需要重新登录.
     */
    public static final Integer UNAUTHORIZED = 401;

    /**
     * 没有权限， 需要重新登录.
     */
    public static final Integer FORBIDDEN = 403;

    /**
     * 没有任务错误码
     */
    public static final Integer TASK_EMPTY_ERROR_CODE = 9999;

    /**
     * 参数为空错误码
     */
    public static final Integer PARAMS_EMPTY = 8888;

}
