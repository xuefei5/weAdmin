package com.service.interfaces;

import java.util.List;

import com.bean.Order;
import com.bean.ProductOrder;


public interface IOrderSV {
	
	//根据订单Id查询订单信息
	public Order qryOrderInfoByOrderId(int orderId);
	
	//订单添加操作
	public Boolean addOrder(Order order);
	
	//订单删除操作
	public Boolean deleteOrder(int id);
	
	//分页查询客户订单列表
	public List<Order> qryOrderByPageNum(int customerId,int startPage,int count);
	
	//查询全部客户订单列表
	public List<Order> qryOrderByCustId(int customerId);
	
	//订单商品添加
	public Boolean addProductOrder(ProductOrder productOrder);

}
