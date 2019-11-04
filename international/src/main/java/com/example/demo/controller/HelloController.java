package com.example.demo.controller;

import com.example.demo.exception.BusinessException;
import com.example.demo.util.InternationalUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zj
 * @date 2019/11/4
 */
@RestController
public class HelloController {
    @Resource
    private InternationalUtil internationalUtil;

    /**
     * 测试控制器结果国际化
     *
     * @return
     */
    @RequestMapping("/hello")
    public String hello() {
        return "HELLO_WORLD";
    }

    /**
     * 测试异常国际化
     *
     * @return
     */
    @RequestMapping("/exception")
    public void exception() throws BusinessException {
        throw new BusinessException("BUSINESS_EXCEPTION");
    }

    /**
     * 测试不国际化
     *
     * @return
     */
    @RequestMapping("/helloFilter")
    public String helloFilter() {
        return "#hello world!";
    }

    /**
     * 测试占位符
     *
     * @return
     */
    @RequestMapping("/helloUser")
    public String helloUser(String name) {
        return internationalUtil.getInterNationalMsg("HELLO_USER",name);
    }
}
