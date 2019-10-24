package com.example.demo.service;

import com.example.demo.pojo.User;

import java.util.List;

public interface UserBatchService {
    public Integer insertUsers(List<User> userList);
}
