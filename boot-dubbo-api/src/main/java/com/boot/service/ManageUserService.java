package com.boot.service;

import com.boot.domain.User;
import com.common.Result;
/**
 * @program: solution-parent
 * @package: xin.selegant.solutionservice.service
 * @author: WangTao
 * @create: 2018-05-22 11:16
 **/
public interface ManageUserService {
    Result login(String userName, String password);

}
