package com.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Contact;
import com.bean.Customer;
import com.bean.User;
import com.dao.interfaces.IUserDAO;
import com.service.interfaces.IContactSV;
import com.service.interfaces.ICustomerSV;
import com.service.interfaces.IUserSV;
import com.utils.LocalConstants;
import com.utils.RooCommonUtils;

@Service
public class UserSVImpl implements IUserSV{

	private static final transient Logger logger = Logger.getLogger(UserSVImpl.class);
	
	@Autowired
	User user;
	
	@Autowired
	IUserDAO userDAO;
	
	@Autowired
	IContactSV contactSV;
	
	@Autowired
	ICustomerSV customerSV;
	
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
			user.setRegisterTime(RooCommonUtils.getCurrentDate());
			user.setState("1");
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

	@Override
	public Boolean isLoin() {
		try {
			session=request.getSession();
			if(null!=session) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public List<User> qryUserByPageNum(int startPage,int count) {
		try {
			return (List<User>) userDAO.qryUserByPageNum(startPage, count);
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public List<Integer> qryPageNum() {
		List<Integer> pageNum = new ArrayList<Integer>();
		try {
			for(int i=0;i<(userDAO.qryUserCount()%10==0?userDAO.qryUserCount()/10:userDAO.qryUserCount()/10+1);i++) {
				pageNum.add(i+1);
			}
			return pageNum;
		}catch(Exception e){
			return pageNum;
		}
	}

	@Override
	public User qryUserById(int id) {
		try {
			return userDAO.qryById(id);
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public int qryUserCount() {
		try {
			return userDAO.qryUserCount();
		}catch(Exception e){
			return 0;
		}
	}

	@Override
	public int qryAllTipsCount() {
		try {
			return contactSV.qryAllTipsCount();
		}catch(Exception e){
			return 0;
		}
	}

	@Override
	public List<Contact> qryAllTips() {
		try {
			List<Contact> newContactList = new ArrayList();
			List<Contact> contactList = new ArrayList();
			contactList = contactSV.qryAllTips();
			if(contactList != null && contactList.size() > 0) {
				for(int i=0;i<contactList.size();i++) {
					Contact contact = contactList.get(i);
					Customer customer = customerSV.qryById(contact.getCustomerId());
					String contactTime = contact.getContactTime();
					String customerName = customer.getName();
					
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					Date beginDate;
					Date endDate;
					
					beginDate = format.parse(RooCommonUtils.getCurrentDate());
					endDate= format.parse(contactTime); 
					long day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);  
					
					Contact newContact = new Contact();
					if(day == 0) {
						newContact.setContent("今天您和"+customerName+"有预约");
					}else if(day == 1) {
						newContact.setContent("明天您和"+customerName+"有预约");
					}else {
						newContact.setContent("后天您和"+customerName+"有预约");
					}
					newContactList.add(newContact);
				}
			}
			return newContactList;
		}catch(Exception e){
			return null;
		}
	}

}
