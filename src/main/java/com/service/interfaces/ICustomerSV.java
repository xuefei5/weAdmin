package com.service.interfaces;

import java.util.List;

import com.bean.Customer;

public interface ICustomerSV {
	
	//查询全部客户对象
	public List<Customer> qryAllCustomer();
	
	//获取总条数
	public int getCustomerAllCount();
	
	//客户添加操作
	public Boolean addCustomer(Customer Customer);
	
	//客户删除操作
	public Boolean deleteCustomer(int id);
	
	//客户修改操作
	public Boolean updateCustomer(Customer Customer);
	
	//根据客户名查询客户对象列表
	public List<Customer> qryByName(String name);
	
	//根据客户ID查询客户对象
	public Customer qryById(int id);
	
	//分页查询客户列表
	public List<Customer> qryCustomerByPageNum(int startPage,int endPage);

}
