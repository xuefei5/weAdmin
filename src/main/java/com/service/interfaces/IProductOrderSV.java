package com.service.interfaces;

import java.util.List;

import com.bean.ProductOrder;


public interface IProductOrderSV {
	
	//订单商品添加操作
	public Boolean addProductOrder(ProductOrder productOrder);
	
	//订单商品删除操作
	public Boolean deleteProductOrder(int id);

	//订单编号查询订单商品
	public List<ProductOrder> qryProductOrderByOrderId(int orderId);
}
