package com.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> qryAllUser() {
		return (List<User>) userDAO.qryAll();
	}
	
}
