package com.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bean.User;
import com.dao.interfaces.IUserDAO;
import com.service.interfaces.IUserSV;

@Service
public class UserSVImpl implements IUserSV{

	private static final transient Logger logger = Logger.getLogger(UserSVImpl.class);
	
	@Autowired
	User user;
	
	@Autowired
	IUserDAO userDAO;
	
	@Autowired  
	private HttpServletRequest request; 
	
	@Autowired  
	private HttpSession session;  

	@SuppressWarnings("unchecked")
	@Override
	public List<User> qryAllUser() {
		return (List<User>) userDAO.qryAll();
	}

	@Override
	public Boolean login(String name, String password) {
		try {
			user = userDAO.qryByNameAndPswd(name, password);
			if(null == user) {
				return false;
			}else {
				session=request.getSession();
				session.setAttribute("isLogin", true);
				session.setAttribute("user", user);
				return true;
			}
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public Boolean logout() {
		try {
			session=request.getSession();
			if(null!=session) {
				session.removeAttribute("isLogin");
				session.removeAttribute("user");
			}
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public Boolean addUser(User user) {
		try{
			int retNum = userDAO.insert(user);
			if(retNum == 1) {
				return true;
			}
		}catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public Boolean deleteUser(int id) {
		try{
			int retNum = userDAO.delete(id);
			if(retNum == 1) {
				return true;
			}
		}catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public Boolean updateUser(User user) {
		try{
			int retNum = userDAO.update(user);
			if(retNum == 1) {
				return true;
			}
		}catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public List<User> qryByName(String name) {
		try{
			List<User> userList = userDAO.qryByName(name);
			return userList;
		}catch (Exception e) {
			return null;
		}
	}

}
