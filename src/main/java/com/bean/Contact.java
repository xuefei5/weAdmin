package com.bean;

import java.io.Serializable;

import org.springframework.stereotype.Repository;


@Repository("Contact")
public class Contact implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int customerId;
	private String contactTime;
	private String content;
	private String isChance;
	private String subscribeTime;
	private String state;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getContactTime() {
		return contactTime;
	}
	public void setContactTime(String contactTime) {
		this.contactTime = contactTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIsChance() {
		return isChance;
	}
	public void setIsChance(String isChance) {
		this.isChance = isChance;
	}
	public String getSubscribeTime() {
		return subscribeTime;
	}
	public void setSubscribeTime(String subscribeTime) {
		this.subscribeTime = subscribeTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
