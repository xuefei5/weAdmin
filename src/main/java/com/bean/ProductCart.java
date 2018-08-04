package com.bean;

import java.io.Serializable;

import org.springframework.stereotype.Repository;


@Repository("ProductCart")
public class ProductCart implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String userId;
	private String productId;
	private String productName;
	private String productTip;
	private String productImgRef;
	private String productPrice;
	private String state;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
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
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
