package com.mueng.feign.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @auther: mufeng
 * @Date: 2019/11/25 17:49
 */
@FeignClient("cloud-web")
public interface ITestService {
    @GetMapping("/test1")
    public String test1();
}
