package com.fpg.api.ctrler;

import com.ump.commons.web.ResultRsp;
import com.ump.commons.web.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping("/getApi")
    public String getApi(@RequestBody String params){
       return "success";
    }

    @RequestMapping("/getMap")
    public Map<String,String> getMap(@RequestBody String params){
        Map<String,String> ret = new HashMap<>();
        ret.put("aa","11");
        ret.put("bb","22");
        ret.put("cc","33");
        ret.put("dd","44");
        return ret;
    }

    @RequestMapping("/getRsp")
    public ResultRsp getRsp(@RequestBody String params){
         return ResultUtil.success("getResultRsp msg");
    }

//    @Value("${key-11}")
//    private String message;
//
//    @GetMapping("/test")
//    public String test() {
//        return message;
//    }
}
