package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.bean.Product;
import com.bean.User;
import com.dao.interfaces.IProductDAO;
import com.dao.interfaces.IUserDAO;
import com.service.interfaces.IProductSV;
import com.service.interfaces.IUserSV;
import com.utils.LocalConstants;
import com.utils.RooCommonUtils;

@Service
public class ProductSVImpl implements IProductSV{

	private static final transient Logger logger = Logger.getLogger(ProductSVImpl.class);
	
	@Autowired
	Product product;
	
	@Autowired
	IProductDAO productDAO;
	
	@Autowired  
	private HttpServletRequest request; 
	
	@Autowired  
	private HttpSession session;  

	@Override
	public List<Product> qryProductByPageNum(int startPage, int count) {
		try {
			return (List<Product>) productDAO.qryProductByPageNum(startPage, count);
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public List<Product> qryProductByNamePageNum(String name, int startPage, int count) {
		try {
			return (List<Product>) productDAO.qryProductByNamePageNum(name,startPage, count);
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public Boolean addProduct(Product product) {
		try{
			product.setAddTime(RooCommonUtils.getCurrentDate());
			product.setState("1");
			product.setViewcount(1);
			int retNum = productDAO.insert(product);
			if(retNum == 1) {
				return true;
			}
		}catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public Boolean deleteProduct(int id) {
		try{
			int retNum = productDAO.delete(id);
			if(retNum == 1) {
				return true;
			}
		}catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public Boolean updateProduct(Product product) {
		try{
			int retNum = productDAO.update(product);
			if(retNum == 1) {
				return true;
			}
		}catch (Exception e) {
			return false;
		}
		return false;
	}
	
	@Override
	public int qryProductCount() {
		try {
			return productDAO.qryProductCount();
		}catch(Exception e){
			return 0;
		}
	}

	@Override
	public int qryProductByNameCount(String name) {
		try {
			return productDAO.qryProductByNameCount(name);
		}catch(Exception e){
			return 0;
		}
	}

	@Override
	@Transactional
	public Boolean purchaseProduct(JSONObject jsonObject) {
		Boolean falg = false;
		if(null == jsonObject.get("customerId")) {
			return falg;
		}
		int customerId = (Integer) (jsonObject.get("customerId"));
		
		@SuppressWarnings("unchecked")
		List<JSONObject> productList = (List<JSONObject>) jsonObject.get("productList");
		if(null == productList||productList.size() == 0) {
			return falg;
		}
		
		
		
		
		
		return null;
	}
}
