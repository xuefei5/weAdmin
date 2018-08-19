package com.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Contact;
import com.dao.interfaces.IContactDAO;
import com.service.interfaces.IContactSV;
import com.utils.LocalConstants;

@Service
public class ContactSVImpl implements IContactSV{

	private static final transient Logger logger = Logger.getLogger(ContactSVImpl.class);
	
	@Autowired
	Contact Contact;
	
	@Autowired
	IContactDAO ContactDAO;
	
	@Autowired  
	private HttpServletRequest request; 
	
	@Autowired  
	private HttpSession session;  

	@SuppressWarnings("unchecked")
	

	@Override
	public Boolean addContact(Contact Contact) {
		try{
			int retNum = ContactDAO.insert(Contact);
			if(retNum == 1) {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public Boolean deleteContact(int id) {
		try{
			int retNum = ContactDAO.delete(id);
			if(retNum == 1) {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public List<Contact> qryByCustId(int id) {
		try {
			return (List<Contact>) ContactDAO.qryByCustId(id);
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public int qryAllTipsCount() {
		try {
			return ContactDAO.qryContactTipsCount();
		}catch(Exception e){
			return 0;
		}
	}

	@Override
	public List<Contact> qryAllTips() {
		try {
			return (List<Contact>) ContactDAO.qryContactTips();
		}catch(Exception e) {
			return null;
		}
	}

	

}
