package com.service.interfaces;

import java.util.List;

import com.bean.Order;
import com.bean.ProductOrder;


public interface IOrderSV {
	
	//订单添加操作
	public Boolean addOrder(Order order);
	
	//订单删除操作
	public Boolean deleteOrder(int id);
	
	//分页查询客户订单列表
	public List<Order> qryOrderByPageNum(int customerId,int startPage,int count);
	
	//订单商品添加
	public Boolean addProductOrder(ProductOrder productOrder);

}
