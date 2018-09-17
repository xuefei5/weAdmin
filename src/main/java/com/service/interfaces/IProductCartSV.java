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
	
	//查询所有商品购物车信息
	public List<ProductCart> qryAll();
	
	//商品id查询购物车信息
	public List<ProductCart> qryProductCartByProductId(int productId);
	
	//商品id删除购物车商品
	public Boolean deleteByProductId(int id);
}
