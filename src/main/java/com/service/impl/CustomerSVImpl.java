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
import com.dao.interfaces.ICustomerDAO;
import com.service.interfaces.IContactSV;
import com.service.interfaces.ICustomerSV;
import com.utils.LocalConstants;

@Service
public class CustomerSVImpl implements ICustomerSV{

	private static final transient Logger logger = Logger.getLogger(CustomerSVImpl.class);
	
	@Autowired
	Customer Customer;
	
	@Autowired
	ICustomerDAO CustomerDAO;
	
	@Autowired
	IContactSV contactSV;
	
	@Autowired  
	private HttpServletRequest request; 
	
	@Autowired  
	private HttpSession session;  

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> qryAllCustomer() {
		return (List<Customer>) CustomerDAO.qryAll();
	}
	
	@Override
	public int getCustomerAllCount() {
		return CustomerDAO.getCustomerAllCount();
	}

	@Override
	public Boolean addCustomer(Customer Customer) {
		try{
			int retNum = CustomerDAO.insert(Customer);
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
	@Transactional
	public Boolean deleteCustomer(int id) {
		try{
			int retNum = CustomerDAO.delete(id);
			if(retNum == 1) {
				List<Contact> contactList = contactSV.qryByCustId(id);
				if(contactList != null && contactList.size() > 0) {
					for(int i=0;i<contactList.size();i++) {
						Contact contact = contactList.get(i);
						if("1".equals(contact.getState())) {
							contactSV.deleteContact(contact.getId());
						}
					}
				}
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public Boolean updateCustomer(Customer Customer) {
		try{
			int retNum = CustomerDAO.update(Customer);
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
	public List<Customer> qryByName(String name,int startPage,int endPage) {
		try{
			List<Customer> CustomerList = CustomerDAO.qryByName(name,startPage,endPage);
			return CustomerList;
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Customer> qryCustomerByPageNum(int startPage,int endPage) {
		try {
			return (List<Customer>) CustomerDAO.qryCustomerByPageNum(startPage, endPage);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public com.bean.Customer qryById(int id) {
		try {
			return (Customer) CustomerDAO.qryById(id);
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public int getCustomerCountByName(String name) {
		return CustomerDAO.getCustomerCountByName(name);
	}

	@Override
	public int getCustomerCountHaveChanceByName(String name) {
		return CustomerDAO.getCustomerCountHaveChanceByName(name);
	}

	@Override
	public List<Customer> qryHaveChanceByName(String name,int startPage, int endPage) {
		try{
			List<Customer> CustomerList = CustomerDAO.qryHaveChanceByName(name,startPage,endPage);
			return CustomerList;
		}catch (Exception e) {
			return null;
		}
	}

	

}
