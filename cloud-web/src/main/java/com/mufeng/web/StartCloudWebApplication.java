package com.mufeng.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @auther: mufeng
 * @Date: 2019/11/19 14:28
 */
@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class StartCloudWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartCloudWebApplication.class,args);
        log.info("start success ......");
    }
}
