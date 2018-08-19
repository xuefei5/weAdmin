package com.service.interfaces;

import java.util.List;

import com.bean.Contact;

public interface IContactSV {
	
	
	//联系添加操作
	public Boolean addContact(Contact Contact);
	
	//联系删除操作
	public Boolean deleteContact(int id);
	
	//根据客户ID查询联系对象
	public List<Contact> qryByCustId(int id);
	
	//查询提醒条数
	public int qryAllTipsCount();
	
	//查询提醒信息
	public List<Contact> qryAllTips();

}
