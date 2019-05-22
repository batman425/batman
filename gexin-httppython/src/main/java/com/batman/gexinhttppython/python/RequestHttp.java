package com.batman.gexinhttppython.python;

import com.batman.gexinhttppython.common.util.HttpClientUtil;
import com.batman.gexinhttppython.file.ReadExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private ReadExcel readExcel;

    String path = "G:\\SLJB20190403SPIC2003.xlsx";

    /**
     * 模拟真实环境线程调用
     */
    @RequestMapping("requestHttp/test")
    public void Test(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                logger.info("测试获取的url:{}",chargeItem_match_url);
                Map<Integer,String> matchData = readExcel.readExcelData(path);
                logger.info("打印获取的数据：{}",matchData.toString());

                for(int i=0;i<matchData.size();i++){
                    logger.info("{}线程请求第{}条数据开始匹配{}",Thread.currentThread().getId(),i,matchData.get(i));
                    HttpPython(matchData.get(i));
                }
            }
        }).start();
    }

   // @RequestMapping("requestHttp/python")
    public String HttpPython(String miName){
        String response = HttpClientUtil.sendGet(chargeItem_match_url,miName, null);
        List<Map<String, String>> list = HttpClientUtil.parseResp(response, miName, 3);
        logger.info("匹配返回的结果:{}",list.toString());
        return "SUCCESS";
    }
}
