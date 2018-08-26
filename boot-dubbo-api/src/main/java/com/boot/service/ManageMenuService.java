package com.boot.service;

import com.boot.domain.ManageMenu;
import com.common.PageRet;
import com.common.Result;
import org.springframework.data.domain.Pageable;


/**
 * @program: solution-parent
 * @package: xin.selegant.solutionservice.service
 * @author: WangTao
 * @create: 2018-05-23 10:38
 **/
public interface ManageMenuService {

    Result getMenu(Integer userType);

//    Result menuAuth(String path, String role);
//
//    PageRet<ManageMenu> getMenuListByPage(ManageMenu manageMenu, Pageable pageable);
}
