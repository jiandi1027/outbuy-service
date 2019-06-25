package com.cjdjyf.outbuyservice.controller.sys;

import com.cjdjyf.outbuyservice.base.PageBean;
import com.cjdjyf.outbuyservice.base.ResultBean;
import com.cjdjyf.outbuyservice.pojo.sys.SysTest;
import com.cjdjyf.outbuyservice.service.sys.SysTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
/**
 * @author : cjd
 * @description :
 * @create : 2019-06-25 09:38:54
 **/
@Controller
@RequestMapping("/sys/sysTest")
public class SysTestController {
    @Autowired
    private SysTestService sysTestService;

    @GetMapping("/list")
    public String list() {
        return "sys/sysTest/sysTestList";
    }

    @GetMapping("/addList")
    public String sysTestAddList(String id, HttpServletRequest request) {
        request.setAttribute("sysTest", sysTestService.findById(id));
        return "sys/sysTest/sysTestAddList";
    }

    @PostMapping("/list")
    @ResponseBody
    public PageBean<SysTest> forList(SysTest sysTest) {
        return sysTestService.findPageBean(sysTest);
    }

    @PostMapping("save")
    @ResponseBody
    public ResultBean<String> save(SysTest sysTest) {
        return new ResultBean<>(sysTestService.save(sysTest));
    }

    @PostMapping("del")
    @ResponseBody
    public ResultBean<String> del(SysTest sysTest) {
        return new ResultBean<>(sysTestService.del(sysTest));
    }
}