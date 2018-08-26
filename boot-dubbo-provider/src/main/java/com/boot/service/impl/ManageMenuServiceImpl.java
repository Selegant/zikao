package com.boot.service.impl;
/**
 * @program: solution-parent
 * @package: xin.selegant.solutionservice.service.impl
 * @author: WangTao
 * @create: 2018-05-23 10:38
 **/

import com.alibaba.dubbo.config.annotation.Service;
import com.boot.dao.ManageMenuDao;
import com.boot.dao.ManageUserDao;
import com.boot.domain.ManageMenu;
import com.boot.dto.RouterMenuDTO;
import com.boot.service.ManageMenuService;
import com.common.Result;
import com.common.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 菜单Service
 **/
@Service(version = "1.0.0")
public class ManageMenuServiceImpl implements ManageMenuService {

    private static final String WHITE_MENU= "/403;/home;/login";

    @Autowired
    ManageUserDao manageUserDao;
    @Autowired
    ManageMenuDao manageMenuDao;


    @Override
    public Result getMenu(Integer userType) {
        List<ManageMenu> parentMenuList=manageMenuDao.queryParentMenuListByUserType(userType);
        List<RouterMenuDTO> routers=new ArrayList<>();
        for (ManageMenu menu:parentMenuList
                ) {
            RouterMenuDTO parentMenuDTO = new RouterMenuDTO();
            parentMenuDTO.setLabel(menu.getMenuName());
            parentMenuDTO.setPath(menu.getMenuPath());
            parentMenuDTO.setAuth(menu.getMenuAuth());
            parentMenuDTO.setIcon("earth");
            parentMenuDTO.setTitle(menu.getMenuName());
            List<RouterMenuDTO> childList = getChildMenuDTO(menu.getMenuId(),userType);
            if(childList.size()>0){
                parentMenuDTO.setSubmenu(childList);
            }
            routers.add(parentMenuDTO);
        }
        return ResultGenerator.genSuccessResult(routers);
    }


    public List<RouterMenuDTO> getChildMenuDTO(Integer parentId,Integer userType) {
        List<ManageMenu> childMenu = manageMenuDao.queryMenuByParentId(parentId,userType);
        List<RouterMenuDTO> routerMenuDTOS = new ArrayList<>();
        for (ManageMenu child : childMenu
                ) {
            RouterMenuDTO routerMenuDTO = new RouterMenuDTO();
            routerMenuDTO.setLabel(child.getMenuName());
            routerMenuDTO.setPath(child.getMenuPath());
            routerMenuDTO.setAuth(child.getMenuAuth());
            routerMenuDTO.setIcon("earth");
            routerMenuDTO.setTitle(child.getMenuName());
            routerMenuDTO.setSubmenu(getChildMenuDTO(child.getMenuId(),userType));
            routerMenuDTOS.add(routerMenuDTO);
        }
        return routerMenuDTOS;
    }
}

