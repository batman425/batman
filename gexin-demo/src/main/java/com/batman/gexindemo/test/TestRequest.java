package com.batman.gexindemo.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liusongwei
 * @Title: TestRequest
 * @ProjectName batman
 * @Description: TODO
 * @date 2019/5/149:37
 */
@RestController
public class TestRequest {
    @RequestMapping("/test/test")
    public void test(){
        System.out.println("打印第一条信息");
    }

    @RequestMapping("/applyInfo")
    public void applyInfo(){
        System.out.println("打印demo服务的信息");
    }
}
