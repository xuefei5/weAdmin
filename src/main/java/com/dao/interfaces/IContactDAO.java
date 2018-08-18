package com.dao.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bean.Contact;

@Mapper
public interface IContactDAO{	
	
	/*添加联系信息对象*/
	@Insert("insert into contact(customerId,contactTime,content,isChance,subscribeTime,state)values(#{customerId},#{contactTime},#{content},#{isChance},#{subscribeTime},'1')")
	public int insert(Contact contact);
	
	/*删除联系信息对象*/
	@Update("UPDATE contact SET state='0' where  id = #{id}")
	public int delete(@Param("id")int id);
	
	/*根据客户id查询联系信息对象*/
	@Select("select * from contact where customerId = #{id} and state='1'")
	public List<Contact> qryByCustId(@Param("id")int id);
	
	/*查询联系提醒对象*/
	@Select("select * from contact where to_days(contactTime) -  to_days(now()) >= 0 and to_days(contactTime) -  to_days(now()) < 3  and state='1'")
	public List<Contact> qryContactTips();
	
	/*查询联系提醒条数*/
	@Select("select count(*) from contact where to_days(contactTime) -  to_days(now()) >= 0 and to_days(contactTime) -  to_days(now()) < 3  and state='1'")
	public int qryContactTipsCount();
	
}
