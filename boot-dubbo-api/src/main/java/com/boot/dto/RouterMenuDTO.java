package com.boot.dto;

import lombok.Data;

import java.util.List;
/**
 * @program: solution-parent
 * @package: xin.selegant.solutionservice.bean
 * @author: WangTao
 * @create: 2018-05-21 15:44
 **/

/**
 * @description:
 **/
@Data
public class RouterMenuDTO {
    private String path;
    private String icon;
    private String name;
    private String title;
    private String component;
    private Boolean auth;
    private List<RouterMenuDTO> children;
}

