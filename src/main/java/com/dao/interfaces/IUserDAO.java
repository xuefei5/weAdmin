package com.dao.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bean.User;

@Mapper
public interface IUserDAO{	
	
	/*添加用户对象*/
	@Insert("insert into user(name,password,telephone,nickName,remarks,registerTime,state)values(#{name},#{password},#{telephone},#{nickName},#{remarks},#{registerTime},'1')")
	public int insert(User user);
	
	/*根据id查询用户对象*/
	@Select("select * from user where id = #{id} and state='1'")
	public User qryById(@Param("id")int id);
	
	/*根据姓名模糊查询用户对象*/
	@Select("select * from user where name like CONCAT('%',#{name},'%') and state='1'")
	public List<User> qryByName(@Param("name")String name );
	
	/*查询所有用户对象*/
	@Select("select * from user where state='1'")
	public List<User> qryAll();
	
	/*更新用户对象*/
	@Update("UPDATE user SET name = #{name},password = #{password},telephone = #{telephone},nickName = #{nickName},remarks = #{remarks} where id = #{id}")
	public int update(User user);
	
	/*删除用户对象*/
	@Update("UPDATE user SET state='0' where  id = #{id}")
	public int delete(@Param("id")int id);
	
	/*根据用户名和密码查询用户对象*/
	@Select("select * from user where name = #{name} and password = #{password} and state='1'")
	public User qryByNameAndPswd(@Param("name")String name	,@Param("password")String password	);
	
	/*分页查询用户对象*/
	@Select("select * from user where state='1' order by registerTime desc limit #{start} , #{end} ")
	public List<User> qryUserByPageNum(@Param("start")int start,@Param("end")int end);

	/*查询用户数据条数*/
	@Select("select count(*) from user where state='1'")
	public int qryUserCount();
}
