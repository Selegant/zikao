package com.boot.controller;
/**
 * @program: solution-parent
 * @package: xin.selegant.solutionweb.controller
 * @author: WangTao
 * @create: 2018-05-22 11:15
 **/

import com.alibaba.dubbo.config.annotation.Reference;
import com.boot.service.ManageUserService;
import com.common.Result;
import org.springframework.web.bind.annotation.*;


/**
 * @description: 后台用户相关操作
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference(version = "1.0.0")
    ManageUserService manageUserService;

    @RequestMapping("/login")
    @PostMapping
    private Result login(@RequestParam String userName, @RequestParam String password){
        return manageUserService.login(userName,password);
    }



}

