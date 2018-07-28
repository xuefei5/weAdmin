package com.dao.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bean.ProductCart;

@Mapper
public interface IProductCartDAO{

	/*添加商品到购物车*/
	@Insert("insert into Productcart(userId,customerId,productId,productName,productTip,productImgRef,productPrice,state)values(#{userId},#{customerId},#{productId},#{productName},#{productTip},#{productImgRef},#{productPrice},'1')")
	public int insert(ProductCart productCart);
	
	/*删除购物车商品对象*/
	@Update("UPDATE productcart SET state='0' where  id = #{id}")
	public int delete(@Param("id")int id);
	
	/*查询购物车商品*/
	@Select("select * from productcart where state='1'")
	public List<ProductCart> qryAll();

}
