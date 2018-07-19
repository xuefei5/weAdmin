package com.bean;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

@Repository
public class Test  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private int id;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
