package com.bean;

import java.io.Serializable;

import org.springframework.stereotype.Repository;


@Repository("Product")
public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String tip;
	private int price;
	private String imgRef;
	private String addTime;
	private int viewcount;
	private String state;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImgRef() {
		return imgRef;
	}
	public void setImgRef(String imgRef) {
		this.imgRef = imgRef;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public int getViewcount() {
		return viewcount;
	}
	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
