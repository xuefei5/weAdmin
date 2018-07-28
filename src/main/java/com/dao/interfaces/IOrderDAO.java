package com.dao.interfaces;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bean.Order;

@Mapper
public interface IOrderDAO{	
	
	/*添加订单对象*/
	@Insert("insert into order(userId,customerId,ordertime,total,isCancel,productName,productTip,productImgRef,state)values(#{userId},#{customerId},#{ordertime},#{total},#{isCancel},#{productName},#{productTip},#{productImgRef},'1')")
	public int insert(Order user);
	
	/*根据id查询订单对象*/
	@Select("select * from order where id = #{id} and state='1'")
	public Order qryById(@Param("id")int id);
	
	/*删除订单对象*/
	@Update("UPDATE order SET state='0' where  id = #{id}")
	public int delete(@Param("id")int id);
	
	/*查询订单数据条数*/
	@Select("select count(*) from order")
	public int qryOrderCount();
}
