package com.boot.dao;
/**
 * @program: solution-parent
 * @package: xin.selegant.solutionservice.dao
 * @author: WangTao
 * @create: 2018-05-22 12:15
 **/

import com.boot.domain.ManageMenu;
import com.common.PageRet;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @description: MenuDAO
 **/
@Repository
public interface ManageMenuDao extends BaseMapper<ManageMenu> {
    List<ManageMenu> queryParentMenuListByUserType(@Param("userType") Integer userType);

    List<ManageMenu> queryMenuByParentId(@Param("parentId") Integer parentId, @Param("userType") Integer userType);

}

