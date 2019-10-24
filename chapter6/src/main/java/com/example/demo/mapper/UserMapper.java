package com.example.demo.mapper;

import com.example.demo.pojo.User;

public interface UserMapper {
    User getUser(Long id);
    Integer insertUser(User user);
}
