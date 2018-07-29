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
	
	/*根据客户id分页查询订单对象*/
	@Select("select * from order where customerId = #{customerId} and state='1' order by ordertime desc limit #{start} , #{end}")
	public Order qryOrderByPageNum(@Param("id")int id,@Param("start")int start,@Param("end")int end);
	
	/*删除订单对象*/
	@Update("UPDATE order SET state='0' where  id = #{id}")
	public int delete(@Param("id")int id);
	
	/*查询客户订单数据条数*/
	@Select("select count(*) from order where customerId = #{customerId} and  state='1'")
	public int qryOrderCount();
}
