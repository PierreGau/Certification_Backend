package com.kwiky.backend.dto;

public class CanalDTO {
	Long id;
	String name;
	Long userID;
	
	
	@Override
	public String toString() {
		return "CanalDTO [id=" + id + ", name=" + name + ", userID=" + userID + "]";
	}
	public CanalDTO() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	
	
}
