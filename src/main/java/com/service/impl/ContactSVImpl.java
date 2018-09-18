package com.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bean.Contact;
import com.bean.Customer;
import com.dao.interfaces.IContactDAO;
import com.service.interfaces.IContactSV;
import com.service.interfaces.ICustomerSV;

@Service
public class ContactSVImpl implements IContactSV{

	private static final transient Logger logger = Logger.getLogger(ContactSVImpl.class);
	
	@Autowired
	Contact Contact;
	
	@Autowired
	IContactDAO ContactDAO;
	
	@Autowired
	ICustomerSV customerSV;
	
	@Autowired  
	private HttpServletRequest request; 
	
	@Autowired  
	private HttpSession session;  

	@SuppressWarnings("unchecked")
	

	@Override
	@Transactional
	public Boolean addContact(Contact Contact) {
		try{
			int retNum = 0;
			
			List<Contact> contactList = ContactDAO.qryByCustId(Contact.getCustomerId());
			int isChance = 0; 
			if(contactList != null && contactList.size() > 0){
				for(int i = 0;i<contactList.size();i++) {
					Contact contact = contactList.get(i);
					if(contact.getIsChance().equals("1")) {
						isChance = isChance + 1;
					}
				}
			}
			
			if(isChance > 0) {
				//如果是修改操作
				if(!(-1==Contact.getId())){
					retNum = ContactDAO.update(Contact);
				}else{
					Contact.setId(0);
					retNum = ContactDAO.insert(Contact);
				}
				if(retNum == 1) {
					return true;
				}
			}else {
				if("1".equals(Contact.getIsChance())) {
					Customer customer = customerSV.qryById(Contact.getCustomerId());
					customer.setState("2");
					customerSV.updateCustomer(customer);
					
					//如果是修改操作
					if(!(-1==Contact.getId())){
						retNum = ContactDAO.update(Contact);
					}else{
						Contact.setId(0);
						retNum = ContactDAO.insert(Contact);
					}
					if(retNum == 1) {
						return true;
					}
				}else {
					//如果是修改操作
					if(!(-1==Contact.getId())){
						retNum = ContactDAO.update(Contact);
					}else{
						Contact.setId(0);
						retNum = ContactDAO.insert(Contact);
					}
					if(retNum == 1) {
						return true;
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	@Transactional
	public Boolean deleteContact(int id) {
		try{
			int customerId = ContactDAO.qryById(id).getCustomerId();
			ContactDAO.delete(id);
			
			int isChance = 0;
			List<Contact> contactList = ContactDAO.qryByCustId(customerId);
			if(contactList != null && contactList.size() > 0){
				for(int i = 0;i<contactList.size();i++) {
					Contact contact = contactList.get(i);
					if(contact.getIsChance().equals("1")) {
						isChance = isChance + 1;
					}
				}
			}
			
			if(isChance <= 0) {
				Customer customer = customerSV.qryById(customerId);
				customer.setState("0");
				customerSV.updateCustomer(customer);
				return true;
			}
			
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
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
