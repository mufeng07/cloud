package com.mufeng.ribbon.controller;

import com.mufeng.ribbon.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: mufeng
 * @Date: 2019/11/22 15:52
 */
@RestController
public class TestController {
    @Autowired
    private ITestService testService;
    @GetMapping("/getRibbonInfo")
    public String getRibbonInfo(){
        return "ribbon:"+testService.getInfo();
    }

}
