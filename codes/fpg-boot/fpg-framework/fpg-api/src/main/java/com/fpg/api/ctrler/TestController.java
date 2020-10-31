package com.fpg.api.ctrler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RefreshScope
@RestController
public class TestController {
    @Autowired
    private Environment environment;

    @RequestMapping("/test")
    public Map index() {
        Map<String,String> ret = new HashMap<>();
        ret.put("type",environment.getProperty("type"));
        ret.put("driverClassName",environment.getProperty("driverClassName"));
        ret.put("url",environment.getProperty("url"));
        ret.put("username",environment.getProperty("username"));
        ret.put("password",environment.getProperty("password"));
        ret.put("miniIdle",environment.getProperty("miniIdle"));
        ret.put("maxPoolSize",environment.getProperty("maxPoolSize"));
        ret.put("idleTimeout",environment.getProperty("idleTimeout"));
        ret.put("connectionTimeout",environment.getProperty("connectionTimeout"));
        return ret;
    }

//    @Value("${key-11}")
//    private String message;
//
//    @GetMapping("/test")
//    public String test() {
//        return message;
//    }
}
