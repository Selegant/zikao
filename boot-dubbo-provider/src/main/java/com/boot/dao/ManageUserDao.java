package com.boot.dao;
/**
 * @program: solution-parent
 * @package: xin.selegant.solutionservice.dao
 * @author: WangTao
 * @create: 2018-05-22 11:18
 **/

import com.boot.domain.ManageUser;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @description: 用户dao
 **/
@Repository
public interface ManageUserDao extends BaseMapper<ManageUser> {
    ManageUser findByName(@Param("userName") String userName);
}

