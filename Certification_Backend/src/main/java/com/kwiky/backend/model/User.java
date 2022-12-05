package com.kwiky.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@NotNull
	@Column(name="name", unique=true)
	private String name;
	
	// Constructeur par defaut
	public User() {
		
	}

	// constructeur avec tous les attributs
	public User(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	// ToString
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
}
