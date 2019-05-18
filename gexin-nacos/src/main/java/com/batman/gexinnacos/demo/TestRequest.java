package com.batman.gexinnacos.demo;

import com.batman.gexinnacos.feign.client.SendApply;
import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;

/**
 * @author liusongwei
 * @Title: TestRequest
 * @ProjectName batman
 * @Description: TODO
 * @date 2019/5/149:37
 */
@RestController
public class TestRequest {
    private static Logger log = LoggerFactory.getLogger(TestRequest.class);

    @Autowired
    private SendApply sendApply;

    @RequestMapping("/test/test")
    public void test(String name){
        log.info("测试skywalking专用" + name);
        System.out.println("打印第一条信息");
    }

    @RequestMapping("/test/test2")
    public void test2(String name){
        log.info("测试skywalking专用222222222" + name);

        System.out.println("打印链路跟踪ID:"+ TraceContext.traceId());
        ActiveSpan.tag("login_tag", "login to system, user: " + name);
        /*测试异常场景*/
        //int i=0,j;
        //j=3/i;
        sendApply.applyInfo();
    }

}
