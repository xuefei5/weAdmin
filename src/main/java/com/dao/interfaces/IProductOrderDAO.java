package com.dao.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bean.ProductOrder;

@Mapper
public interface IProductOrderDAO{

	/*添加商品订单对象*/
	@Insert("insert into Productorder(userId,customerId,productId,productName,productTip,productImgRef,productPrice,state)values(#{userId},#{customerId},#{productId},#{productName},#{productTip},#{productImgRef},#{productPrice},'1')")
	public int insert(ProductOrder productorder);
	
	/*删除商品订单对象*/
	@Update("UPDATE productorder SET state='0' where  id = #{id}")
	public int delete(@Param("id")int id);
	
	/*订单号查询商品订单对象*/
	@Select("select * from productorder where orderId=#{orderId}")
	public List<ProductOrder> qryProductOrderByOrderId();

}
