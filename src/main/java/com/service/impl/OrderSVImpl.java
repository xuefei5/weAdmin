package com.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Order;
import com.bean.ProductOrder;
import com.bean.User;
import com.dao.interfaces.IOrderDAO;
import com.service.interfaces.IOrderSV;
import com.service.interfaces.IProductOrderSV;
import com.utils.RooCommonUtils;

@Service
public class OrderSVImpl implements IOrderSV{

	private static final transient Logger logger = Logger.getLogger(OrderSVImpl.class);
	
	@Autowired
	Order order;
	
	@Autowired
	IOrderDAO orderDAO;
	
	@Autowired
	IProductOrderSV productOrderSV;
	
	@Autowired  
	private HttpServletRequest request; 
	
	@Autowired  
	private HttpSession session;  

	@Override
	public Boolean addOrder(Order order) {
		
	   try{
			session=request.getSession();
			User user = (User) session.getAttribute("user");
			int userId = user.getId();
			
			order.setUserId(userId);
			order.setOrdertime(RooCommonUtils.getCurrentDate());
			order.setIsCancel("0");
			order.setState("1");
			
			int retNum = orderDAO.backOrder(order);
			if(retNum == 1) {
				return true;
			}
		}catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public Boolean deleteOrder(int id) {
	    try{
			int retNum = orderDAO.delete(id);
			if(retNum == 1) {
				return true;
			}
		}catch (Exception e) {
			return false;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> qryOrderByPageNum(int customerId, int startPage, int count) {
		try {
			return (List<Order>) orderDAO.qryOrderByPageNum(customerId, startPage, count);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean addProductOrder(ProductOrder productOrder) {
		
		try{
			Boolean retNum = productOrderSV.addProductOrder(productOrder);
			return retNum;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Order> qryOrderByCustId(int customerId) {
		try {
			return (List<Order>) orderDAO.qryOrderByCustId(customerId);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Order qryOrderInfoByOrderId(int orderId) {
		try {
			return (Order) orderDAO.qryOrderInfoByOrderId(orderId);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
