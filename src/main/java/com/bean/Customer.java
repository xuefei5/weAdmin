package com.bean;

import java.io.Serializable;

import org.springframework.stereotype.Repository;


@Repository("Customer")
public class Customer implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String nickName;
	private String sex;
	private String birthday;
	private String telephone;
	private String imgRef;
	private String addTime;
	private String remarks;
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
