package com.nyx.crade.playground.JPA_FB_Demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FbPost {

	private String id;
	private String message;
	private String created_time;
	private transient Object paging;

	@Id
	public String getId() {
		return id;
	}

	public void setId(String Id) {
		this.id = Id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCreated_time() {
		return created_time;
	}

	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}

}
