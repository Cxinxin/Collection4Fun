package com.iris.redis.controller;

import com.iris.redis.domain.User;
import com.iris.redis.service.RedisService;
import com.iris.redis.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;


    //http://localhost:8888/getUser?name=amy
    @GetMapping(value = "/getUser")
    public User getUser(String name){
        User user = (User) userService.findByName(name);
        return user;
    }


    @PostMapping(value = "/save")
    public User saveUser(User user){
        return userService.save(user);
    }


}

