package com.service.interfaces;

import java.util.List;

import com.bean.ProductCart;


public interface IProductCartSV {
	
	//商品购物车添加操作
	public Boolean addProductCart(ProductCart productCart);
	
	//商品购物车删除操作
	public Boolean deleteProductCart(int id);

	//商品购物车分页查询操作
	public List<ProductCart> qryProductCartByPageNum(int startPage,int count);
}
