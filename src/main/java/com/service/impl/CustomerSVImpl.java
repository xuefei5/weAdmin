package com.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Customer;
import com.dao.interfaces.ICustomerDAO;
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
	private HttpServletRequest request; 
	
	@Autowired  
	private HttpSession session;  

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> qryAllCustomer() {
		return (List<Customer>) CustomerDAO.qryAll();
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
	public Boolean deleteCustomer(int id) {
		try{
			int retNum = CustomerDAO.delete(id);
			if(retNum == 1) {
				return true;
			}
		}catch (Exception e) {
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
			return false;
		}
		return false;
	}

	@Override
	public List<Customer> qryByName(String name) {
		try{
			List<Customer> CustomerList = CustomerDAO.qryByName(name);
			return CustomerList;
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Customer> qryCustomerByPageNum(int pageNum) {
		try {
			return (List<Customer>) CustomerDAO.qryCustomerByPageNum(LocalConstants.PAGE_SET.PAGE_SIZE*(pageNum-1), LocalConstants.PAGE_SET.PAGE_SIZE*pageNum);
		}catch(Exception e) {
			return null;
		}
	}

}
