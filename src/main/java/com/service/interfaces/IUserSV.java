package com.service.interfaces;

import java.util.List;


import com.bean.User;


public interface IUserSV {
	
	//查询全部用户对象
	public List<User> qryAllUser();
	
	//用户登录操作
	public Boolean login(String name,String password);
	
	//用户注销操作
	public Boolean logout();
	
	//用户添加操作
	public Boolean addUser(User user);
	
	//用户删除操作
	public Boolean deleteUser(int id);
	
	//用户删除操作
	public Boolean updateUser(User user);
	
	//根据用户名查询用户对象列表
	public List<User> qryByName(String name);
	
}
