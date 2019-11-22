package com.mufeng.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @auther: mufeng
 * @Date: 2019/11/20 11:20
 */
@SpringBootApplication
@EnableEurekaServer
public class StartCloudEureka2Application {
    public static void main(String[] args) {
        SpringApplication.run(StartCloudEureka2Application.class,args);
    }
}
