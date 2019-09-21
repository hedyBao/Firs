package com.tomas.hellodemo.Test;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Component

@Service(version="2.0.0")
public class HiServiceImpl implements HiService {
    public String sayHi(String name){
        return "hi";
    }
}
