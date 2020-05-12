package com.mueng.feign.service.impl;

import com.mueng.feign.service.IUserService;
import com.mueng.feign.service.feign.ICloudWebFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: mufeng
 * @create: 2020/5/11 15:41
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {
    @Autowired
    private ICloudWebFeign cloudWebFeign;
    @Override
    public String getUser() {
        log.info("开始调用ICloudWebFeign。。。");
        return cloudWebFeign.test1();
    }
}
