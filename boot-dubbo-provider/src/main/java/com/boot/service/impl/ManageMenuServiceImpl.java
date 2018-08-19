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
import com.boot.dto.AuthDTO;
import com.boot.dto.RouterMenuDTO;
import com.boot.service.ManageMenuService;
import com.common.PageRet;
import com.common.Result;
import com.common.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Result getMenu(String roleId) {
        List<ManageMenu> parentMenuList=manageMenuDao.queryParentMenuListByRoleId(roleId);
        List<RouterMenuDTO> routers=new ArrayList<>();
        for (ManageMenu menu:parentMenuList
                ) {
            RouterMenuDTO parentMenuDTO = new RouterMenuDTO();
            parentMenuDTO.setName(menu.getMenuName());
            parentMenuDTO.setPath(menu.getMenuPath());
            parentMenuDTO.setAuth(menu.getMenuAuth());
            parentMenuDTO.setIcon("earth");
            parentMenuDTO.setTitle(menu.getMenuName());
            parentMenuDTO.setChildren(getChildMenuDTO(menu.getMenuId(),roleId));
            routers.add(parentMenuDTO);
        }
        return ResultGenerator.genSuccessResult(routers);
    }

    @Override
    public Result menuAuth(String path, String role) {
        AuthDTO authDTO=new AuthDTO();
        if(WHITE_MENU.contains(path)){
            authDTO.setRouteAuth(false);
            authDTO.setRoleAuth(true);
            return ResultGenerator.genSuccessResult(authDTO);
        }
        ManageMenu manageMenu= new ManageMenu();
        manageMenu.setMenuPath(path);
        manageMenu=manageMenuDao.templateOne(manageMenu);
        if(manageMenu.getMenuAuth()){
            ManageMenu authMenu=manageMenuDao.queryParentMenuListByRoleIdAndPath(role,path);
            if(authMenu!=null){
                authDTO.setRouteAuth(manageMenu.getMenuAuth());
                authDTO.setRoleAuth(true);
            }else {
                authDTO.setRouteAuth(manageMenu.getMenuAuth());
                authDTO.setRoleAuth(false);
            }
        }else{
            authDTO.setRouteAuth(manageMenu.getMenuAuth());
            authDTO.setRoleAuth(true);
        }
        return ResultGenerator.genSuccessResult(authDTO);
    }

    @Override
    public PageRet<ManageMenu> getMenuListByPage(ManageMenu manageMenu, Pageable pageable) {
        return manageMenuDao.getMenuListByPage(manageMenu, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
    }


    public List<RouterMenuDTO> getChildMenuDTO(Integer parentId,String roleId) {
        List<ManageMenu> childMenu = manageMenuDao.queryMenuByParentId(parentId,roleId);
        List<RouterMenuDTO> routerMenuDTOS = new ArrayList<>();
        for (ManageMenu child : childMenu
                ) {
            RouterMenuDTO routerMenuDTO = new RouterMenuDTO();
            routerMenuDTO.setName(child.getMenuName());
            routerMenuDTO.setPath(child.getMenuPath());
            routerMenuDTO.setAuth(child.getMenuAuth());
            routerMenuDTO.setIcon("earth");
            routerMenuDTO.setTitle(child.getMenuName());
            routerMenuDTO.setChildren(getChildMenuDTO(child.getMenuId(),roleId));
            routerMenuDTOS.add(routerMenuDTO);
        }
        return routerMenuDTOS;
    }
}

