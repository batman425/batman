package com.batman.gexineureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author liusongwei
 * @Title: GexinEurekaApplication
 * @ProjectName batman
 * @Description: TODO
 * @date 2019/5/2217:11
 */
@SpringBootApplication
@EnableEurekaServer
public class GexinEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(GexinEurekaApplication.class, args);
    }
}
