package com.mueng.feign.service.feign;

import com.mueng.feign.service.feign.impl.CloudWebHystrixImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description:
 * @author: mufeng
 * @create: 2020/5/11 15:45
 */
@FeignClient(name = "cloud-web1",fallback = CloudWebHystrixImpl.class)
public interface ICloudWebFeign {
    @GetMapping("/test1")
    public String test1();
}
