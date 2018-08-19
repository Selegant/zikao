package com.boot.domain;
import lombok.Data;

import java.util.Date;

/**
* gen by wangtao 2018-05-22
*/

@Data
public class ManageUser  {

	//后台用户ID
	private Integer manageId ;
	//角色ID
	private Integer roleId ;
	//后台用户头像
	private String manageHeadUrl ;
	//后台用户姓名
	private String manageName ;
	//用户密码
	private String password ;
	//创建时间
	private Date createTime ;
	//修改时间
	private Date updateTime ;


}
