package com.tomas.hellodemo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tomas.StockService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {


    @Reference(url= "dubbo://192.168.3.9:20880", version = "1.0.0-SNAPSHOT")
    StockService stockService;

    @RequestMapping("/say")
    @ResponseBody
    public String sayHello(){

        try {
            String s = stockService.sayHello("123");
            System.out.println(s);
            return s;

        }catch (Exception e){
            System.out.println();
        }
        return null;

    }

    @RequestMapping("/query")
    @ResponseBody
    public int queryStock(){

        int a = stockService.queryStock("123");
        System.out.println(a);
        return a;

    }
}
