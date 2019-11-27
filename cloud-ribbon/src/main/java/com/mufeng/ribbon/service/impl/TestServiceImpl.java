package com.mufeng.ribbon.service.impl;

import com.mufeng.ribbon.service.ITestService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @auther: mufeng
 * @Date: 2019/11/22 15:55
 */
@Service
public class TestServiceImpl implements ITestService {
    @Autowired
    private RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "myFallback")
    @Override
    public String getInfo() {
        String message;
            System.out.println("调用 服务 EUREKA-CLIENT/info");
            message = restTemplate.getForObject("http://cloud-web/test2", String.class);
            System.out.println("服务 EUREKA-CLIENT/info 返回信息 : " + message);
            System.out.println("调用 服务 EUREKA-CLIENT/info 成功！");
        return message;
    }
    private void test1(){
        ResponseEntity<String> forEntity = restTemplate.getForEntity("", String.class, "");
    }
    public String myFallback(){
        return "error";
    }
}
