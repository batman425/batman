package com.batman.gexinhttppython.common.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liusongwei
 * @Title: HttpClientUtil
 * @ProjectName batman
 * @Description: TODO
 * @date 2019/5/2211:50
 */
public class HttpClientUtil {
    public static final String CHARSET = "UTF-8";
    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    public static String sendPost(String url,String params){

        HttpClient httpClient = HttpClients.createDefault();//创建http客户端
        HttpPost post = new HttpPost(url);//创建post请求

        //post配置
        config(post);

        StringEntity entity = new StringEntity(params, Charset.forName(CHARSET));
        post.setEntity(entity);//设置请求参数

        String response = null;//设置响应参数

        try {
            HttpResponse httpResponse = httpClient.execute(post);//发起post请求
            response = httpResponse.getEntity().toString();//获取响应信息
        } catch (IOException e) {
            logger.error("post发送异常:{}",e);
        }

        logger.info(response);

        return response;
    }
    public static String sendGet(String url,String params,String drugForm){

        HttpClient httpClient = HttpClients.createDefault();//创建http客户端
        try {
            if (ObjectUtils.isEmpty(drugForm)){
                url = url+"?name="+ URLEncoder.encode(params,"utf-8");
            } else {
                url = url+"?name="+URLEncoder.encode(params,"utf-8")+"&drugForm="+URLEncoder.encode(drugForm,"utf-8");
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("get发送异常:{}",e);
        }

        logger.info("请求url------->>>"+url);
        HttpGet get = new HttpGet(url);

        //post配置
        config(get);
        String response = null;//设置响应参数

        try {
            HttpResponse httpResponse = httpClient.execute(get);//发起post请求
            int code = httpResponse.getStatusLine().getStatusCode();
            if (200 != code){
                return null;
            }
            response =  EntityUtils.toString(httpResponse.getEntity(),CHARSET);
        } catch (IOException e) {
            logger.error("get发送异常:{}",e);
        }
//        parseResp(response,params);
        logger.info("请求python，url：{}，请求参数：{}------->>>响应结果集为----------》》》{}",url,params,response);
        return response;
    }

    public static void config(HttpRequestBase request){

        request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36 SE 2.X MetaSr 1.0");
//            request.setHeader("Content-Type","text/html;charset=utf-8");
        RequestConfig config = RequestConfig.custom()
                .setConnectionRequestTimeout(6000)//获取连接的超时时间为6秒
                .setSocketTimeout(30000)//响应超时时间2分钟
                .setConnectTimeout(20000).build();//与服务器连接超时时间
        request.setConfig(config);
    }

    public static List<Map<String,String>> parseResp(String response, String name, Integer matchType){
        Map map = JSONObject.parseObject(response,Map.class);
        Map rs = JSONObject.parseObject(map.get("res").toString(),Map.class);
        List<Map<String,String>> wrapList = new ArrayList<>();
        List<List<String>> list =JSONObject.parseObject(rs.get(name).toString(),List.class);
        if (list.size()==0){
            return wrapList;
        }
        for (List<String> list1:list){
            Map<String,String> result = new HashMap();
            BigDecimal bigDecimal = new BigDecimal(String.valueOf(list1.get(4))).setScale(2,BigDecimal.ROUND_HALF_UP);
            result.put("originName",name);
            result.put("itemId",String.valueOf(list1.get(0)));
            result.put("miCode",String.valueOf(list1.get(1)));
            result.put("miName",String.valueOf(list1.get(2)));
            result.put("miDosageForm",String.valueOf(list1.get(3)));
            result.put("score",bigDecimal.toString());
            result.put("matchType",String.valueOf(matchType));
            result.put("chargeItemType",String.valueOf(list1.get(5)));
            wrapList.add(result);
        }
        logger.info("{}",wrapList);
        return wrapList;
    }
}
