package com.boot.service.impl;
/**
 * @program: solution-parent
 * @package: xin.selegant.solutionservice.service.impl
 * @author: WangTao
 * @create: 2018-05-22 11:17
 **/


import com.alibaba.dubbo.config.annotation.Service;
import com.boot.dao.ManageMenuDao;
import com.boot.dao.ManageUserDao;
import com.boot.domain.ManageUser;
import com.boot.service.ManageUserService;
import com.common.Result;
import com.common.ResultCode;
import com.common.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 用户Service
 **/
@Service(version = "1.0.0")
public class ManageUserServiceImpl implements ManageUserService {

    @Autowired
    ManageUserDao manageUserDao;
    @Autowired
    ManageMenuDao manageMenuDao;

    @Override
    public Result login(String userName, String password) {
        ManageUser manageUser=new ManageUser();
        manageUser.setManageName(userName);
        manageUser=manageUserDao.templateOne(manageUser);
        if(manageUser==null){
            return ResultGenerator.genFailResult(ResultCode.NO_EXIST_USER);
        }
        if(manageUser.getPassword().equals(password)){
            Map<String,Object> map=new HashMap<>();
            map.put("role",manageUser.getRoleId());
            map.put("username",manageUser.getManageName());
            map.put("token","123456");
            return ResultGenerator.genSuccessResult(map);
        }
        return ResultGenerator.genFailResult(ResultCode.ERROR_PASSWORD);
    }


}

