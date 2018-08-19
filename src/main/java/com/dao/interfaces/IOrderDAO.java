package com.dao.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.bean.Order;

@Mapper
public interface IOrderDAO{	
	
	/*添加订单对象*/
	@Insert("insert into orderC(userId,customerId,ordertime,total,isCancel,productName,productTip,productImgRef,state)values(#{userId},#{customerId},#{ordertime},#{total},#{isCancel},#{productName},#{productTip},#{productImgRef},'1')")
	public int insert(Order user);
	
	/*根据客户id分页查询订单对象*/
	@Select("select * from orderC where customerId = #{customerId} and state='1' order by ordertime desc limit #{start} , #{end}")
	public List<Order> qryOrderByPageNum(@Param("customerId")int customerId,@Param("start")int start,@Param("end")int end);
	
	/*删除订单对象*/
	@Update("UPDATE orderC SET state='0' where  id = #{id}")
	public int delete(@Param("id")int id);
	
	/*查询客户订单数据条数*/
	@Select("select count(*) from orderC where customerId = #{customerId} and  state='1'")
	public int qryOrderCount();
	
	/*添加订单对象-返回主键*/
	@Insert("insert into orderC(userId,customerId,ordertime,total,isCancel,productName,productTip,productImgRef,state)values(#{userId},#{customerId},#{ordertime},#{total},#{isCancel},#{productName},#{productTip},#{productImgRef},'1')")
	@SelectKey(keyColumn="id", keyProperty="id", resultType=int.class, before=false, statement="select last_insert_id()")
	public int backOrder(Order user);
}
