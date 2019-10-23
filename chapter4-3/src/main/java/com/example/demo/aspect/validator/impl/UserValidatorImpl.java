package com.example.demo.aspect.validator.impl;

import com.example.demo.aspect.validator.UserValidator;
import com.example.demo.pojo.User;

public class UserValidatorImpl implements UserValidator {
    @Override
    public boolean validate(User user) {
        System.out.println("引入新的接口："+UserValidatorImpl.class.getSimpleName());
        return user!=null;
    }
}
