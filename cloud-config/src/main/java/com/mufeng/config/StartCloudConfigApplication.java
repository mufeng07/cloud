package com.mufeng.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @auther: mufeng
 * @Date: 2019/11/19 09:53
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
@Slf4j
public class StartCloudConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartCloudConfigApplication.class,args);
        log.info("start success.......");
    }
}
