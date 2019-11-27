package com.mufeng.zuul;

import com.mufeng.zuul.config.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * @auther: mufeng
 * @Date: 2019/11/26 15:51
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class StartCloudZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartCloudZuulApplication.class,args);
    }
    @Bean
    public AccessFilter accessFilter(){
        return new AccessFilter();
    }
}
