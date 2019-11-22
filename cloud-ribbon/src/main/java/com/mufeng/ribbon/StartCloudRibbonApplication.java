package com.mufeng.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @auther: mufeng
 * @Date: 2019/11/22 15:36
 */
@SpringBootApplication
@EnableDiscoveryClient
public class StartCloudRibbonApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartCloudRibbonApplication.class,args);
    }
}
