package com.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.bean.Test;

@Mapper
public interface TestDao {	
	
	@Select("select * from test where id = #{id}")
	public Test getById(@Param("id")int id	);

	@Insert("insert into test(id, name)values(#{id}, #{name})")
	public int insert(Test test);
}
