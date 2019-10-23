package com.example.demo.aspect.validator;

import com.example.demo.pojo.User;

public interface UserValidator {
    //检测用户对象是否有空
    public boolean validate(User user);
}
