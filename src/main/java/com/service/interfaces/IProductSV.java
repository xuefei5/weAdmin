package com.service.interfaces;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.bean.Product;
import com.bean.ProductCart;


public interface IProductSV {
	
	//分页查询商品列表
	public List<Product> qryProductByPageNum(int startPage,int count);
	
	//分页商品名称模糊查询商品列表
	public List<Product> qryProductByNamePageNum(String name,int startPage,int count);
	
	//商品添加操作
	public Boolean addProduct(Product product);
	
	//商品删除操作
	public Boolean deleteProduct(int id);

	//商品更新操作
	public Boolean updateProduct(Product product);
	
	//查询商品对象条数
	public int qryProductCount();
	
	//商品名称模糊查询商品对象条数
	public int qryProductByNameCount(String name);
	
	//商品购买
	public Boolean purchaseProduct(JSONObject jsonObject);
	
	//商品添加购物车
	public Boolean addProdToCart(int productId);
	
	//id查询商品对象
	public Product qryProductById(int id);
	
	//查询所有购物车商品
	public List<ProductCart> qryAllProductCart();
	
	//通过id删除购物车商品
	public Boolean deleteProductCartByProdId(int id);
	
}
