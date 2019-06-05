package com.batman.gexindockerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author liusongwei
 * @Title: GexinDockerDemoApplication
 * @ProjectName batman
 * @Description: TODO
 * @date 2019/6/510:59
 */
@SpringBootApplication
@EnableEurekaServer
public class GexinDockerDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(GexinDockerDemoApplication.class, args);
    }
}
