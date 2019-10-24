package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.service.UserBatchService;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;
    @Resource
    UserBatchService userBatchService;

    @RequestMapping("/get")
    public User getUser(Long id) {
        return userService.getUser(id);
    }

    @RequestMapping("/insert")
    public User insertUser(String userName, String note) {
        User user = new User();
        user.setNote(note);
        user.setUserName(userName);
        userService.insertUser(user);
        return user;
    }

    @RequestMapping("/inserts")
    public String insertUsers() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setNote("" + i);
            user.setUserName("" + i);
            userList.add(user);
        }
        userBatchService.insertUsers(userList);
        return "批量添加成功";
    }
}
