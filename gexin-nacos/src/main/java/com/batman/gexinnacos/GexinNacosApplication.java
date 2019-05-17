package com.batman.gexinnacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class GexinNacosApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(GexinNacosApplication.class, args);
        String userName = applicationContext.getEnvironment().getProperty("user.name");
        String age = applicationContext.getEnvironment().getProperty("user.age");
        System.out.println("打印出的信息为: " + userName);
        System.out.println("打印出的年龄为: " + age);
    }

}
