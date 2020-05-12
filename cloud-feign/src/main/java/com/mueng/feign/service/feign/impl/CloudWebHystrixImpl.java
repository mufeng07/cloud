package com.mueng.feign.service.feign.impl;

import com.mueng.feign.service.feign.ICloudWebFeign;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: mufeng
 * @create: 2020/5/11 15:44
 */
@Component
public class CloudWebHystrixImpl implements ICloudWebFeign {
    @Override
    public String test1() {
        return "调用失败，服务降级";
    }
}
