package com.dao.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bean.Product;
import com.bean.ProductCart;

@Mapper
public interface IProductCartDAO{

	/*添加商品到购物车*/
	@Insert("insert into productcart(userId,productId,productName,productTip,productImgRef,productPrice,state)values(#{userId},#{productId},#{productName},#{productTip},#{productImgRef},#{productPrice},'1')")
	public int insert(ProductCart productCart);
	
	/*删除购物车商品对象*/
	@Update("UPDATE productcart SET state='0' where id = #{id}")
	public int delete(@Param("id")int id);
	
	/*分页查询购物车商品*/
	@Select("select * from productcart where state='1' limit #{start} , #{end} ")
	public List<ProductCart> qryProductCartByPageNum(@Param("start")int start,@Param("end")int end);
	
	/*查询所有购物车商品*/
	@Select("select * from productcart where state='1'")
	public List<ProductCart> qryAll();
	
	/*商品id删除购物车商品对象*/
	@Update("update productcart set state = '0' where productId = #{productId}")
	public int deleteByProductId(@Param("productId")int productId);
	
	/*商品id查询商品购物车是否存在*/
	@Select("select * from productcart where state='1' and productId = #{productId} ")
	public List<ProductCart> qryProductCartByProductId(@Param("productId")int productId);
}
