package com.service.interfaces;

import java.util.List;


import com.bean.User;


public interface IUserSV {
	
	//查询全部用户对象
	public List<User> qryAllUser();
	
	//用户登录操作
	public Boolean login(String name,String password);
}
