package com.bean;

import java.io.Serializable;

import org.springframework.stereotype.Repository;


@Repository("ProductOrder")
public class ProductOrder implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int orderId;
	private int productId;
	private int price;
	private int amount;
	private String productImgRef;
	private String productName;
	private String productTip;
	private String state;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getProductImgRef() {
		return productImgRef;
	}
	public void setProductImgRef(String productImgRef) {
		this.productImgRef = productImgRef;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
		
}
