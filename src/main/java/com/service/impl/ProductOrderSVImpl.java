package com.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.ProductOrder;
import com.dao.interfaces.IProductOrderDAO;
import com.service.interfaces.IProductOrderSV;

@Service
public class ProductOrderSVImpl implements IProductOrderSV{

	private static final transient Logger logger = Logger.getLogger(ProductOrderSVImpl.class);
	
	@Autowired
	ProductOrder productOrder;
	
	@Autowired
	IProductOrderDAO productOrderDAO;
	
	@Autowired  
	private HttpServletRequest request; 
	
	@Autowired  
	private HttpSession session;  

	@Override
	public Boolean addProductOrder(ProductOrder productOrder) {
		try{
			productOrder.setState("1");
			int retNum = productOrderDAO.insert(productOrder);
			if(retNum == 1) {
				return true;
			}
		}catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public Boolean deleteProductOrder(int id) {
		try{
			int retNum = productOrderDAO.delete(id);
			if(retNum == 1) {
				return true;
			}
		}catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public List<com.bean.ProductOrder> qryProductOrderByOrderId(int orderId) {
		try {
			return productOrderDAO.qryProductOrderByOrderId(orderId);
		}catch(Exception e) {
			return null;
		}
	}
}
