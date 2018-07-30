package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.ProductCart;
import com.bean.User;
import com.dao.interfaces.IProductCartDAO;
import com.dao.interfaces.IUserDAO;
import com.service.interfaces.IProductCartSV;
import com.service.interfaces.IUserSV;
import com.utils.LocalConstants;
import com.utils.RooCommonUtils;

@Service
public class ProductCartSVImpl implements IProductCartSV{

	private static final transient Logger logger = Logger.getLogger(ProductCartSVImpl.class);
	
	@Autowired
	ProductCart productCart;
	
	@Autowired
	IProductCartDAO productCartDAO;
	
	@Autowired  
	private HttpServletRequest request; 
	
	@Autowired  
	private HttpSession session;  

	@Override
	public Boolean addProductCart(ProductCart productCart) {
		try{
			productCart.setState("1");
			int retNum = productCartDAO.insert(productCart);
			if(retNum == 1) {
				return true;
			}
		}catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public Boolean deleteProductCart(int id) {
		try{
			int retNum = productCartDAO.delete(id);
			if(retNum == 1) {
				return true;
			}
		}catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public List<ProductCart> qryProductCartByPageNum(int startPage, int count) {
		try {
			return productCartDAO.qryProductCartByPageNum(startPage, count);
		}catch(Exception e) {
			return null;
		}
	}
}
