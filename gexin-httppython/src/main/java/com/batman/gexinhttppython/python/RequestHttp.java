package com.batman.gexinhttppython.python;

import com.batman.gexinhttppython.common.util.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author liusongwei
 * @Title: RequestHttp
 * @ProjectName batman
 * @Description: TODO
 * @date 2019/5/2211:38
 */
@RestController
public class RequestHttp {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${chargeItem_match_url}")
    private String chargeItem_match_url;

    @RequestMapping("requestHttp/test")
    public void Test(){
        logger.info("测试获取的url:{}",chargeItem_match_url);
    }

    @RequestMapping("requestHttp/python")
    public String HttpPython(String miName){
        String response = HttpClientUtil.sendGet(chargeItem_match_url,miName, null);
        List<Map<String, String>> list = HttpClientUtil.parseResp(response, miName, 3);
        logger.info("匹配返回的结果:{}",list.toString());
        return "SUCCESS";
    }
}
