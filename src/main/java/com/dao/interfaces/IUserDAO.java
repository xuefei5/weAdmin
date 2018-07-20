package com.dao.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.bean.User;

@Mapper
public interface IUserDAO{	
	
	/*添加用户对象*/
	@Insert("insert into user(id,name,password,telephone,nickName,remarks,registerTime,state)values(#{id}, #{name},#{password},#{telephone},#{nickName},#{remarks},#{registerTime},#{state})")
	public int insert(User user);
	
	/*根据id查询用户对象*/
	@Select("select * from user where id = #{id}")
	public User qryById(@Param("id")int id	);
	
	/*查询所有用户对象*/
	@Select("select * from user")
	public List<User> qryAll();
}
