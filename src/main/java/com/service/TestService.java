package com.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bean.Test;
import com.dao.TestDao;

@Service
public class TestService {
	
	@Autowired
	TestDao userDao;

 	@Transactional
	public boolean tx() {
		
		
		Test t1= new Test();
		t1.setId(2);
		t1.setName("2222");
		userDao.insert(t1);
		
		Test t2= new Test();
		t2.setId(1);
		t2.setName("11111");
		userDao.insert(t2);
		
		return true;
	}
	
}
