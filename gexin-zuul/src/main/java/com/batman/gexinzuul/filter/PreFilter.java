package com.batman.gexinzuul.filter;

import com.alibaba.fastjson.JSONObject;
import com.batman.gexinzuul.service.ICheckToken;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liusongwei
 * @Title: AccessTokenFilter
 * @Description: 前置过滤器
 * @date 2018/11/716:08
 */
public class PreFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(PostFilter.class);

    @Autowired
    private ICheckToken iCheckToken;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
     //   long start, end;
      //  start = System.currentTimeMillis();

        RequestContext ctx = RequestContext.getCurrentContext();

        String urlstr = ctx.getRequest().getRequestURI();
        if (urlstr.contains("noaccesstoken")) {
            ctx.set("isSuccess", false);
        } else {
            HttpServletRequest request = ctx.getRequest();
            //避免中文乱码
            ctx.addZuulResponseHeader("Content-type", "text/json;charset=UTF-8");
            ctx.getResponse().setCharacterEncoding("UTF-8");
            Object accessToken = request.getHeader("accesstoken");
            log.info("前置过滤器校验token值：" + accessToken + "  请求的URL" + urlstr);
            try {
                if (accessToken == null) {
                    ctx.set("isSuccess", true);
                  /*  Map<String,String> tokenMap = new HashMap<>();
                    tokenMap.put("code","401");
                    tokenMap.put("message","无token,非法请求");
                    Map<String,Object> resultTokens = new HashMap<>();
                    resultTokens.put("error",tokenMap);

                    ctx.setSendZuulResponse(false); //不路由
                    ctx.setResponseStatusCode(200); //返回的错误码
                    ctx.setResponseBody(URLDecoder.decode(JSONObject.toJSONString(resultTokens),"UTF-8"));*/
                } else {
                    String ipaddr = getIpAddr(request);
                    log.info("IP地址为: " + ipaddr);
                    String addrhost = request.getRemoteAddr() + request.getRemoteHost();
                    String at = (String) accessToken;
                    String tmp = iCheckToken.gettokenvalue(at);
                    log.info("请求信息: " + addrhost + " 获取redis信息: " + tmp);
                    if (tmp != null && tmp.equals(addrhost)) {
                        log.info("token====addrhost");
                        ctx.set("isSuccess", true);
                    } else {
                        //为了测试此处设置为真
                        ctx.set("isSuccess", false);
                        Map<String,String> tokenMap = new HashMap<>();
                        tokenMap.put("code","401");
                        tokenMap.put("message","token已经过期，请重新登陆");
                        Map<String,Object> resultTokens = new HashMap<>();
                        resultTokens.put("error",tokenMap);

                        ctx.setSendZuulResponse(false); //不路由
                        ctx.setResponseStatusCode(200); //返回的错误码
                        ctx.setResponseBody(URLDecoder.decode(JSONObject.toJSONString(resultTokens),"UTF-8"));
                    }
                }
            } catch (Exception e) {
                log.info("前置过滤器请求处理异常");
                e.printStackTrace();
            }
        }
      //  end = System.currentTimeMillis();
     //   System.out.println("start time:" + start + "; end time:" + end + "; Run Time:" + (end - start) + "(ms)");
        return null;
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                 //可能是代理服务器的ip
                ipAddress = request.getRemoteAddr();
                 //本机访问获取ip
                if (ipAddress.equals("127.0.0.1")) {
                    //根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
             //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if(ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length() // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }

        return ipAddress;
    }

}
