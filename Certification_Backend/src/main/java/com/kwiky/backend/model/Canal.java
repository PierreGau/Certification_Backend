package com.kwiky.backend.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Canal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="canal_user",nullable = true,updatable = true)
	private User user;
	
	@Column(name="general", columnDefinition="tinyint(1) default 1")
	private Boolean general = false;
	
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,orphanRemoval = true)
	@Cascade(CascadeType.DELETE)
	private Set<Message> messages;
	
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
	
	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public void addMessage(Message message)
	{
		messages.add(message);
	}
	
	public void deleteMessage(Message message)
	{
		if(messages.contains(message))
		{
			messages.remove(message);
		}
	}
	// ToString
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name +", user=" + user.getName() + "]";
	}
	
	
}
