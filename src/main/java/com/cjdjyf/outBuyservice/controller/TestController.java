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
        System.out.println(234542343);
        System.out.println(123);
        return "test";
    }
}
