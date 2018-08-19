package com.boot.dto;
/**
 * @program: solution-parent
 * @package: xin.selegant.solutionservice.bean
 * @author: WangTao
 * @create: 2018-05-22 09:36
 **/

import lombok.Data;

/**
 * @description: 权限DTO
 **/

@Data
public class AuthDTO {
    /**
     * 是否判断权限路由
     */
    private Boolean routeAuth;

    /**
     * 具有访问路由权限
     */
    private Boolean roleAuth;

}

