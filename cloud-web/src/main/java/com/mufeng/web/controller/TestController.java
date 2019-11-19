package com.mufeng.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: mufeng
 * @Date: 2019/11/19 15:10
 */
@RestController
@RefreshScope
public class TestController {
    @Value("${word}")
    private String world;
    @GetMapping("/test1")
    public String test1(){
        return "hello "+world;
    }

}
