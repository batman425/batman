package com.batman.gexinzuul.filter;

import com.alibaba.fastjson.JSONObject;
import com.batman.gexinzuul.service.ICheckToken;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;
import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;

/**
 * @author liusongwei
 * @Title: PostFilter
 * @Description: 后置过滤器
 * @date 2018/11/1919:02
 */

@Component
public class PostFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(PostFilter.class);

    @Autowired
    private ICheckToken iCheckToken;

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        if((boolean) ctx.get("isSuccess")){
            return true;
        }else{
            return  false;
        }
    }

    @Override
    public Object run() {
       // long start,end;
        //start = System.currentTimeMillis();
        RequestContext ctx = getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Object accessToken = request.getHeader("accesstoken");
        //避免中文乱码
        ctx.addZuulResponseHeader("Content-type", "text/json;charset=UTF-8");
        ctx.getResponse().setCharacterEncoding("UTF-8");
        log.info("后置过滤器校验token值：" + accessToken);
        if(accessToken == null){
                /*获取前端发送的信息，选择性进行存储*/
               // Map<String,String> map = getParameterMap(request);
              /*  BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
                String str = "";
                String wholeStr = "";
                while((str = reader.readLine()) != null){//一行一行的读取body体里面的内容；
                    wholeStr += str;
                }
                System.out.println(wholeStr);
                JSONObject t =JSONObject.parseObject(wholeStr);*/
             //   String username = t.getString("username");
             //   net.sf.json.JSONObject t= net.sf.json.JSONObject.fromObject(wholeStr);//转化成json对象
            try {
                InputStream stream = ctx.getResponseDataStream();
                String body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
                Map json = (Map) JSONObject.parse(body);
                Map<String,Integer> metaMap = new HashMap<>();
                Map<String,String> tokenMap = new HashMap<>();
                Map<String,Object> resultTokens = new HashMap<>();
                if(json != null){
                    String token = (String)json.get("access_token");
                    if (StringUtils.isNotBlank(token)) {
                        String addrhost =  request.getRemoteAddr() + request.getRemoteHost();
                        int expires = (int) json.get("expires_in");
                        log.info("获取到的tocken值为:" + token + " 请求方信息:" + addrhost + " 过期时间:" + expires);
                        //此处的固定值换成上面的信息
                        //expires = 60;
                        iCheckToken.checkipandtoken(token,addrhost,expires);
                        metaMap.put("code",200);
                        tokenMap.put("access_token",token);
                        resultTokens.put("data",tokenMap);
                        resultTokens.put("meta",metaMap);
                        ctx.setResponseBody(URLDecoder.decode(JSONObject.toJSONString(resultTokens),"UTF-8"));
                    }else {
                        String errortmp = (String)json.get("error");
                        tokenMap.put("code","6665");
                        tokenMap.put("message","用户名或者密码错误，请重新输入！");
                        resultTokens.put("error",tokenMap);
                        ctx.setSendZuulResponse(false);
                        if (errortmp.equals("invalid_grant")){
                            log.info("连接认证中心后端异常invalid_grant" + body);
                            ctx.setResponseStatusCode(200);
                            ctx.setResponseBody(URLDecoder.decode(JSONObject.toJSONString(resultTokens),"UTF-8"));
                        }else{
                            log.info("连接认证中心后端异常超时" + body);
                            ctx.setResponseStatusCode(500);
                            tokenMap.put("message","连接认证中心后端异常超时！");
                            resultTokens.put("error",tokenMap);
                            ctx.setResponseBody(URLDecoder.decode(JSONObject.toJSONString(resultTokens),"UTF-8"));
                        }
                    }

                }
                else{
                    metaMap.put("code",400);
                    tokenMap.put("message","连接认证中心获取token失败");
                    resultTokens.put("data",tokenMap);
                    resultTokens.put("meta",metaMap);
                    ctx.setSendZuulResponse(false);
                    ctx.setResponseStatusCode(500);
                    ctx.setResponseBody(URLDecoder.decode(JSONObject.toJSONString(resultTokens),"UTF-8"));
                    log.info("连接认证中心获取token失败:" + resultTokens);
                }

            } catch (IOException e) {
                log.info("后置过滤器请求处理异常");
                rethrowRuntimeException(e);
            }
            return ctx;
        }
        //end = System.currentTimeMillis();
       // System.out.println("start time:" + start+ "; end time:" + end+ "; Run Time:" + (end - start) + "(ms)");
        return null;
    }
}