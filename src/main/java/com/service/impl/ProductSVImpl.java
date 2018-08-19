package com.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.bean.Order;
import com.bean.Product;
import com.bean.ProductCart;
import com.bean.ProductOrder;
import com.bean.User;
import com.dao.interfaces.IOrderDAO;
import com.dao.interfaces.IProductDAO;
import com.dao.interfaces.IUserDAO;
import com.service.interfaces.IOrderSV;
import com.service.interfaces.IProductCartSV;
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
	IOrderDAO id;
	
	@Autowired  
	private HttpServletRequest request; 
	
	@Autowired  
	private HttpSession session;  
	
	@Autowired
	private IProductCartSV productCartSV;
	
	@Autowired
	private IOrderSV orderSV;

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
	public Boolean addProdToCart(int productId) {
		Boolean flag = false;
		
		ProductCart productCart = new ProductCart();
		session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(null != user) {
			productCart.setUserId(String.valueOf(user.getId()));
		}
		
		Product product =productDAO.qryById(productId);
		
		productCart.setProductId(String.valueOf(productId));
		productCart.setProductName(product.getName());
		productCart.setProductTip(product.getTip());
		productCart.setProductImgRef(product.getImgRef());
		productCart.setProductPrice(String.valueOf(product.getPrice()));
		
		flag = productCartSV.addProductCart(productCart);
		return flag;
	}

	@Override
	@Transactional
	public Boolean purchaseProduct(JSONObject jsonObject) {
		Boolean falg = false;
		jsonObject = jsonObject.getJSONObject("params");
		if(null == jsonObject.get("customerId")) {
			return falg;
		}
		int customerId = (Integer) (jsonObject.get("customerId"));
		
		@SuppressWarnings("unchecked")
		List<JSONObject> productList = (List<JSONObject>) jsonObject.get("productList");
		if(null == productList||productList.size() == 0) {
			return falg;
		}
		int orderId;
		
		//生成订单，并返回订单编号
		String orderProductName = "";
		String orderProductTip = "";
		String orderProductImgRef = "";
		int orderTotal = 0;
		for(int i=0;i<productList.size();i++) {
			JSONObject object=productList.get(i).getJSONObject("product");
			if(i == 0) {
				orderProductName=(String) object.get("productName");
				orderProductTip=(String) object.get("productTip");
				orderProductImgRef=(String) object.get("productImgRef");
			}
			orderTotal += Integer.parseInt(String.valueOf(object.get("productPrice")))
							*Integer.parseInt(String.valueOf(object.get("productCount")));
		}
		
		//生成订单
		Order order = new Order();
		session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(null != user) {
			order.setUserId(Integer.parseInt(String.valueOf(user.getId())));
		}
		order.setCustomerId(customerId);
		order.setOrdertime(RooCommonUtils.getCurrentDate());
		order.setTotal(orderTotal);
		order.setIsCancel("0");
		order.setProductName(orderProductName);
		order.setProductTip(orderProductTip);
		order.setProductImgRef(orderProductImgRef);
		/*Boolean addOrder = orderSV.addOrder(order);
		if(!addOrder) {
			return falg;
		}*/
		
		
		System.out.println(id.backOrder(order));
		
		
		//订单商品
		ProductOrder productOrder = new ProductOrder();
		
		
		//已经获取订单号，添加订单商品
		/*for(int i=0;i<productList.size();i++) {
			JSONObject object=productList.get(i).getJSONObject("product");
			
			//进行订单商品添加
			//ProductOrder productOrder = new ProductOrder();
			
			int customerId = 
			
		}*/
		
		/*for(JSONObject object:productList) {
			HashMap<String,Object> product = (HashMap<String, Object>) object.get("product");
			
			
			
		}*/
		
		
		
		return null;
	}

	@Override
	public Product qryProductById(int id) {
		try {
			return productDAO.qryById(id);
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public List<ProductCart> qryAllProductCart() {
		try {
			return productCartSV.qryAll();
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public Boolean deleteProductCartByProdId(int id) {
		try{
			if(productCartSV.deleteProductCart(id)) {
				return true;
			}
		}catch (Exception e) {
			return false;
		}
		return false;
	}
}
