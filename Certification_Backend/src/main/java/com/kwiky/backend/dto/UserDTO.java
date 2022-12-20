package com.kwiky.backend.dto;


public class UserDTO {
	private Long id;
	private String name;
	private boolean actif;

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

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", name=" + name + ", actif=" + actif + "]";
	}

	public UserDTO() {
		super();
	}
	
	
}
