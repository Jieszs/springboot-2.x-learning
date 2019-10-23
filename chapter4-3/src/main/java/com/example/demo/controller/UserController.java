package com.example.demo.controller;

import com.example.demo.aspect.validator.UserValidator;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/print")
    public User printUser(long id, String username, String note) {
        User user = new User();
        user.setNote(note);
        user.setId(id);
        user.setUsername(username);
        userService.print(user);
//        userService.print(null);
        return user;
    }

    @GetMapping("/vp")
    public User validateAndPrintUser(long id, String username, String note) {
        User user = new User();
        user.setNote(note);
        user.setId(id);
        user.setUsername(username);
        UserValidator userValidator = (UserValidator) userService;
        if (userValidator.validate(user)) {
            userService.print(user);
        }
        return user;
    }
    @GetMapping("/manyAspect")
    public String testManyAspect()
    {
        userService.manyAspect();
        return "manyAspect";
    }
}
