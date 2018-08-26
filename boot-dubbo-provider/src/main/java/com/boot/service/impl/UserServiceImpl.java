package com.boot.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.boot.dao.UserDao;
import com.boot.domain.User;
import com.boot.service.UserService;
import com.common.Result;
import com.common.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @author wangtao
 * @createTime 2018/8/26
 */
@Service(version = "1.0.0")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public Result login(User user) {
        User mobileUser = new User();
        mobileUser.setMobile(user.getMobile());
        mobileUser = userDao.templateOne(mobileUser);
        if(mobileUser!=null){
            if(mobileUser.getPassword().equals(user.getPassword())){
                mobileUser.setAccessToken(String.valueOf(UUID.randomUUID()));
                userDao.updateTemplateById(mobileUser);
                return ResultGenerator.genSuccessResult(mobileUser);
            }else{
                return ResultGenerator.genFailResult("密码错误");
            }
        }else{
            return ResultGenerator.genFailResult("用户不存在");
        }
    }

    @Override
    public Result register(User user) {
        User mobileUser = new User();
        mobileUser.setMobile(user.getMobile());

        mobileUser = userDao.templateOne(mobileUser);
        if(mobileUser!=null){
            return ResultGenerator.genFailResult("用户已存在");
        }else{
            user.setAccessToken(String.valueOf(UUID.randomUUID()));
            userDao.insertTemplate(user,true);
            return ResultGenerator.genSuccessResult(user);
        }
    }
}
