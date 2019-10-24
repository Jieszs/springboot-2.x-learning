package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.UserBatchService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserBatchServiceImpl implements UserBatchService {
    @Resource
    private UserService userService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public Integer insertUsers(List<User> userList) {
        int count = 0;
        for (User user : userList) {
            //调用子方法，将使用Transactional定义的传播行为
            count += userService.insertUser(user);
        }
        return count;
    }
}
