package com.bean;

import java.io.Serializable;

import org.springframework.stereotype.Repository;


@Repository("Order")
public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int userId;
	private int customerId;
	private String ordertime;
	private int total;
	private String isCancel;
	private String productName;
	private String productTip;
	private String productImgRef;
	private String state;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getIsCancel() {
		return isCancel;
	}
	public void setIsCancel(String isCancel) {
		this.isCancel = isCancel;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductTip() {
		return productTip;
	}
	public void setProductTip(String productTip) {
		this.productTip = productTip;
	}
	public String getProductImgRef() {
		return productImgRef;
	}
	public void setProductImgRef(String productImgRef) {
		this.productImgRef = productImgRef;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
