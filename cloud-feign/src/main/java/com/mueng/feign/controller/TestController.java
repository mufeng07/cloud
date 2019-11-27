package com.mueng.feign.controller;

import com.mueng.feign.service.feign.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: mufeng
 * @Date: 2019/11/26 11:39
 */
@RestController
public class TestController {
    @Autowired
    private ITestService testService;
    @GetMapping("/test1")
    public String test1(){
        return testService.test1();
    }
}
