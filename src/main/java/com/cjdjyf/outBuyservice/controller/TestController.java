package com.cjdjyf.outBuyservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：cjd
 * @description:
 * @create 2019-06-23 21:21
 **/
@Controller
@RequestMapping("/outBuy")
public class TestController {
    @RequestMapping("test")
    public String test(){
        return "test";
    }
}
