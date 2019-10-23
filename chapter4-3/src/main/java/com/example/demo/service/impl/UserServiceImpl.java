package com.example.demo.service.impl;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void print(User user) {
        if (user == null) {
            throw new RuntimeException("user is null");
        }
        System.out.println("id:" + user.getId());
        System.out.println("note:" + user.getNote());
        System.out.println("username:" + user.getUsername());
    }

    @Override
    public void manyAspect() {
        System.out.println("测试多个切面:");
    }
}
