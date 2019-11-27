package com.mueng.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @auther: mufeng
 * @Date: 2019/11/25 17:44
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class StartCloudFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartCloudFeignApplication.class,args);
    }
}
