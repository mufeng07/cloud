package com.mufeng.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther: mufeng
 * @Date: 2019/11/19 15:10
 */
@RestController
@RefreshScope
public class TestController {
//    @Value("${word}")
//    private String world;
    @Value("${server.port}")
    private String port;
    @GetMapping("/test1")
    public String test1(){
        return "hello ";
    }

    @GetMapping("/test2")
    public String test2(HttpServletRequest request){
        int a=1/0;
        String s=request.getScheme()+"://"+request.getServerName()+":"+port+request.getServletPath();
        return s;
    }
}
