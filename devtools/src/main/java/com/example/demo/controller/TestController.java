package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zj
 * @date 2020/3/2
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String hello() {
        return "hello  world!";
    }
}
