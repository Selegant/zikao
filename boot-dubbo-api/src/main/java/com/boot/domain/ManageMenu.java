package com.boot.domain;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* gen by wangtao 2018-05-22
*/

@Data
public class ManageMenu implements Serializable {

	//菜单ID
	private Integer menuId ;
	//父级菜单ID
	private Integer parentId ;
	//菜单是否判断权限
	private Boolean menuAuth ;
	//菜单注释
	private String menuDesc ;
	//菜单名称
	private String menuName ;
	//菜单路由
	private String menuPath ;
	//菜单状态
	private String menuStatus ;
	//创建时间
	private Date createTime ;
	//修改时间
	private Date updateTime ;


}
