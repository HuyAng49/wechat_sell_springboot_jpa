package com.hxzy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: huYang
 * @description: hello
 * @date: 2020-05-23 14:18
 */
@Controller
public class hellocontroller {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "hello word";
    }
}
