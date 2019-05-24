package com.batman.gexinoauth2.common.exception;


import com.batman.gexinoauth2.common.response.ResponseCode;

/**
 * 没有认证异常.
 *
 * @author weijc
 *
 */
public class UnAuthorizedException extends ResponseCodeAwareException {

    private static final long serialVersionUID = 5543110584431562470L;

    /**
     * 没有认证异常，需要重新登录.
     */
    public UnAuthorizedException() {
        super(ResponseCode.UNAUTHORIZED, "请重新登录");
    }

}
