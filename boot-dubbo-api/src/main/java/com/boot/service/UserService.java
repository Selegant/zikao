package com.boot.service;

import com.boot.domain.User;
import com.common.Result;

/**
 * @author wangtao
 * @createTime 2018/8/26
 */
public interface UserService {
    Result login(User user);

    Result register(User user);
}
