package com.batman.gexinzuul.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;


/**
 * @author liusongwei
 * @Title: ErrorFilter
 * @Description: 异常过滤器
 * @date 2018/11/1919:04
 */
@Component
public class ErrorFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(ErrorFilter.class);

    //private final RateLimiter rateLimiter= RateLimiter.create(1000.0);

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        //优先级，数字越大，优先级越低
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        log.info("请求信息异常" + ctx.getResponseBody());
        Map<String,String> metaMap = new HashMap<>();
        Map<String,Object> resultTokens = new HashMap<>();
        metaMap.put("code","6666");
        metaMap.put("message","服务异常，请联系系统管理员！");
        resultTokens.put("error",metaMap);
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(500);
        try{
            ctx.setResponseBody(URLDecoder.decode(JSONObject.toJSONString(resultTokens),"UTF-8"));
        }catch (IOException e){
            log.info("异常过滤器处理异常报错");
            e.getMessage();
        }
        return null;

    }
}