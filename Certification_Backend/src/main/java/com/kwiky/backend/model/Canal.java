package com.kwiky.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
@Entity
public class Canal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name", unique=true)
	private String name;
	
	@ManyToOne
	@NotNull
	private User user;
	
	@Column(name="general")
	private Boolean general;
	
	// Constructeur par defaut
	public Canal() {
		
	}

	// constructeur avec tous les attributs
	public Canal( String name, User user) {
		this.name = name;
		this.user = user;
		this.general = false;
	}

	public Boolean getGeneral() {
		return general;
	}

	public void setGeneral(Boolean general) {
		this.general = general;
	}

	// Getters & Setters
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
