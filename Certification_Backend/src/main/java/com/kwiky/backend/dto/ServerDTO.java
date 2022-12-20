package com.kwiky.backend.dto;

import java.util.Set;

import com.kwiky.backend.model.User;

public class ServerDTO 
{
	private Long id;
	private String name;
	private UserDTO creator;
	private Set<UserDTO> users;
	private Set<CanalDTO> canaux;
	
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

	public UserDTO getCreator() {
		return creator;
	}
	public void setCreator(UserDTO creator) {
		this.creator = creator;
	}
	public Set<UserDTO> getUsers() {
		return users;
	}
	public void setUsers(Set<UserDTO> users) {
		this.users = users;
	}
	public Set<CanalDTO> getCanaux() {
		return canaux;
	}
	public void setCanaux(Set<CanalDTO> canaux) {
		this.canaux = canaux;
	}
	public ServerDTO() {
		super();
	}
	@Override
	public String toString() {
		return "ServerDTO [id=" + id + ", name=" + name + ", creator=" + creator + ", users=" + users + ", canaux="
				+ canaux + "]";
	}  
}
