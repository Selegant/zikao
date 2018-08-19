package com.boot.controller;
/**
 * @program: solution-parent
 * @package: xin.selegant.solutionweb.controller
 * @author: WangTao
 * @create: 2018-05-23 10:37
 **/

import com.alibaba.dubbo.config.annotation.Reference;
import com.boot.domain.ManageMenu;
import com.boot.service.ManageMenuService;
import com.common.PageRet;
import com.common.Result;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 菜单Controller
 **/

@RequestMapping("menu")
@RestController
public class MenuController {

    @Reference(version = "1.0.0")
    ManageMenuService manageMenuService;

    @RequestMapping("list")
    @PostMapping
    private Result list(@RequestParam(required = false) String roleId){
        return manageMenuService.getMenu(roleId);
    }


    @RequestMapping("auth")
    @PostMapping
    private Result menuAuth(@RequestParam(required = false) String path, String role){
        role = "";
        return manageMenuService.menuAuth(path,role);
    }

    @RequestMapping("menuList")
    @GetMapping
    private PageRet<ManageMenu> menuList(ManageMenu manageMenu, Pageable page){
        return manageMenuService.getMenuListByPage(manageMenu,page);
    }
}

