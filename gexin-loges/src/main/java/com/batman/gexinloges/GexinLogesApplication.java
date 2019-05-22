package com.batman.gexinloges;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author liusongwei
 * @Title: GexinLogesApplication
 * @ProjectName batman
 * @Description: TODO
 * @date 2019/5/2010:56
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GexinLogesApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(GexinLogesApplication.class, args);
        String userName = applicationContext.getEnvironment().getProperty("user.name");
        String age = applicationContext.getEnvironment().getProperty("user.age");
        System.out.println("打印出的信息为: " + userName);
        System.out.println("打印出的年龄为: " + age);
    }
}
