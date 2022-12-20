package com.kwiky.backend.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

	@NotNull
	@Column(name = "actif")
	private boolean actif;
	
	@ManyToMany
	 @JoinTable(name = "server_user",
     joinColumns = @JoinColumn(name = "user_id"),
     inverseJoinColumns = @JoinColumn(name = "server_id"))
	  private Set<Server> servers;
	
	// Constructeurs
	public User() {
		
	}
	
	public void addServer(Server server)
	{
		servers.add(server);
	}
	
	public void delServer(Server server)
	{
		servers.remove(server);
	}

	public User(Long id, String name) {
		this.id = id;
		this.name = name;
		this.setActif(true);
	}


	public Set<Server> getServers() {
		return servers;
	}

	public void setServers(Set<Server> servers) {
		this.servers = servers;
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
	
	

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	// ToString
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
}
