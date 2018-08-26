package com.boot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.boot.domain.User;
import com.boot.service.ManageUserService;
import com.boot.service.UserService;
import com.common.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @program: solution-parent
 * @package: xin.selegant.solutionweb.controller
 * @author: WangTao
 * @create: 2018-05-22 11:15
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference(version = "1.0.0")
    UserService userService;



    @RequestMapping("/login")
    @PostMapping
    private Result login(@RequestParam String mobile, @RequestParam String password){
        User user =new User();
        user.setMobile(mobile);
        user.setPassword(password);
        return userService.login(user);
    }


    @RequestMapping("/register")
    @PostMapping
    private Result register(User user){
        return userService.register(user);
    }



}

