package com.kwiky.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Canal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@ManyToOne(optional = true)
	@JoinColumn(name="canal_user",nullable = true,updatable = true)
	private User user;
	
	@Column(name="general", columnDefinition="tinyint(1) default 1")
	private Boolean general = false;
	
	// Constructeurs
	public Canal() {
		
	}

	public Canal( String name, User user) {
		this.name = name;
		this.user = user;
		this.general = false;
	}

	// Getters & Setters
	public Boolean getGeneral() {
		return general;
	}

	public void setGeneral(Boolean general) {
		this.general = general;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// ToString
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name +", user=" + user.getName() + "]";
	}
	
	
}
