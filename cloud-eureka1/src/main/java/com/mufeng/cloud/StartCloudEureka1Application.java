package com.mufeng.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author mufeng
 * @auther: mufeng
 * @Date: 2019/11/15 11:57
 */
@SpringBootApplication
@EnableEurekaServer
public class StartCloudEureka1Application {
    public static void main(String[] args) {
        SpringApplication.run(StartCloudEureka1Application.class,args);
    }
}
