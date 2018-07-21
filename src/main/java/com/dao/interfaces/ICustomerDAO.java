package com.dao.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bean.Customer;

@Mapper
public interface ICustomerDAO{	
	
	/*添加客户对象*/
	@Insert("insert into customer(name,nickName,sex,birthday,telephone,imgRef,addTime,remarks,state)values(#{name},#{nickName},#{sex},#{birthday},#{telephone},#{imgRef},#{addTime}, #{remarks}, '1')")
	public int insert(Customer customer);
	
	/*根据id查询客户对象*/
	@Select("select * from customer where id = #{id} and state='1'")
	public Customer qryById(@Param("id")int id);
	
	/*根据姓名模糊查询客户对象*/
	@Select("select * from customer where name like CONCAT('%',#{name},'%') and state='1'")
	public List<Customer> qryByName(@Param("name")String name );
	
	/*查询所有客户对象*/
	@Select("select * from customer where state='1'")
	public List<Customer> qryAll();
	
	/*更新客户对象*/
	@Update("UPDATE customer SET name = #{name},nickName = #{nickName},sex = #{sex},telephone = #{telephone},imgRef = #{imgRef},addTime = #{addTime},remarks = #{remarks},state = #{state} wehere id = #{id}")
	public int update(Customer customer);
	
	/*删除客户对象*/
	@Update("UPDATE customer SET state='0' wehere  id = #{id}")
	public int delete(@Param("id")int id);
	
}
