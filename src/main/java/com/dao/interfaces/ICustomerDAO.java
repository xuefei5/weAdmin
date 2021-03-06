package com.dao.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bean.Customer;
import com.bean.User;

@Mapper
public interface ICustomerDAO{	
	
	/*添加客户对象*/
	@Insert("insert into customer(name,nickName,sex,birthday,telephone,imgRef,addTime,remarks,state)values(#{name},#{nickName},#{sex},#{birthday},#{telephone},#{imgRef},#{addTime}, #{remarks}, '0')")
	public int insert(Customer customer);
	
	/*根据id查询客户对象*/
	@Select("select * from customer where id = #{id} and state!='1'")
	public Customer qryById(@Param("id")int id);
	
	/*根据姓名模糊查询客户对象*/
	@Select("select * from customer where name like CONCAT('%',#{name},'%') and state !='1' order by addTime desc limit #{start} , #{end}")
	public List<Customer> qryByName(@Param("name")String name,@Param("start")int id,@Param("end")int end );
	
	/*根据姓名模糊查询客户对象-有销售机会*/
	@Select("select * from customer where name like CONCAT('%',#{name},'%') and state ='2' order by addTime desc limit #{start} , #{end}")
	public List<Customer> qryHaveChanceByName(@Param("name")String name,@Param("start")int id,@Param("end")int end );
	
	/*查询所有客户对象*/
	@Select("select * from customer where state!='1'")
	public List<Customer> qryAll();
	
	/*获取客户总条数*/
	@Select("select count(*) from customer where state!='1'")
	public int getCustomerAllCount();
	
	/*根据客户名获取客户条数*/
	@Select("select count(*) from customer where name like CONCAT('%',#{name},'%') and state !='1'")
	public int getCustomerCountByName(@Param("name")String name );
	
	/*根据客户名获取客户条数-有销售机会*/
	@Select("select count(*) from customer where name like CONCAT('%',#{name},'%') and state ='2'")
	public int getCustomerCountHaveChanceByName(@Param("name")String name );
	
	/*更新客户对象*/
	@Update("UPDATE customer SET name = #{name},nickName = #{nickName},sex = #{sex},telephone = #{telephone},imgRef = #{imgRef},birthday = #{birthday},remarks = #{remarks},state = #{state} where id = #{id}")
	public int update(Customer customer);
	
	/*删除客户对象*/
	@Update("UPDATE customer SET state = '1' WHERE  id = #{id}")
	public int delete(@Param("id")int id);
	
	/*分页查询用户对象*/
	@Select("select * from customer where state != '1' order by addTime desc limit #{start} , #{end} ")
	public List<Customer> qryCustomerByPageNum(@Param("start")int id,@Param("end")int end);
	
}
