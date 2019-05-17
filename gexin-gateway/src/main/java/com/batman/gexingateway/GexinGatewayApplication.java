package com.batman.gexingateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.batman.gexingateway"})
@SpringBootApplication
@EnableDiscoveryClient
public class GexinGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GexinGatewayApplication.class, args);
    }

}
