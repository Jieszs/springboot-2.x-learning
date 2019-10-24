package com.example.demo.service;

import com.example.demo.pojo.User;

public interface UserService {
    User getUser(Long id);
    Integer insertUser(User user);
}
