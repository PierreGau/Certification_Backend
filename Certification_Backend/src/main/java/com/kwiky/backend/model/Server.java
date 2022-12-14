package com.kwiky.backend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="server")
public class Server {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	@Column(name="name")
	private String name;
	
	@ManyToMany()
	@JoinColumn(name="server_users", nullable = true,updatable = true)
	private List<User> users;
	
	@ManyToOne
	@Cascade(CascadeType.DETACH)
	private User creator;
	
	@OneToMany
	@Cascade(CascadeType.ALL)
	private List<Canal> canaux;

	public Server() {
		this.canaux = new ArrayList<>();
		this.users = new ArrayList<>();
		Canal general = new Canal("General", creator);
		general.setGeneral(true);
		this.canaux.add(general);
	}

	public Server(Long id, @NotNull String name, User creator) {
		this.id = id;
		this.name = name;
		this.creator = creator;
		this.canaux = new ArrayList<>();
		Canal general = new Canal("General", creator);
		general.setGeneral(true);
		this.canaux.add(general);
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



	public List<User> getUsers() {
		return users;
	}



	public void setUsers(List<User> users) {
		this.users = users;
	}



	public User getCreator() {
		return creator;
	}



	public void setCreator(User creator) {
		this.creator = creator;
	}



	public List<Canal> getCanaux() {
		return canaux;
	}



	public void setCanaux(List<Canal> canaux) {
		this.canaux = canaux;
	}

	
	public void createGeneral()
	{
		ArrayList<Canal> l = new ArrayList<>();
		Canal c = new Canal("General", this.getCreator());
		c.setGeneral(true);
		l.add(c);
		this.setCanaux(l);
	}
	
	public Server addCanal(Canal canal)
	{
		boolean canAdd = false;
		if (canal.getUser().getId() == creator.getId())
			canAdd=true;
		else
		{
			for(User u : users)
			{
				if(u.getId() == canal.getUser().getId())
				{
					canAdd = true;
					break;
				}
			}
		}
		
		if(canAdd)
			canaux.add(canal);
		
		return this;
	}
	
	public Server delCanal(Canal canal)
	{
		canaux.remove(canal);
		return this;
	}
	
	public Server addUser(User user)
	{
		boolean canAdd = true;
		
		for(User u : users)
		{
			if(u.getId() == user.getId() || user.getId() == creator.getId())
			{
				canAdd = false;
				break;
			}
		}
		if(canAdd)
			users.add(user);
		return this;
	}
	
	public Server delUser(User user)
	{
		if(users.contains(user))
			users.remove(user);
		return this;
	}


	@Override
	public String toString() {
		return String.format("Server [id=%s, name=%s, creator=%s, nombre de canaux=%s]", id, name, creator.getName(),
				canaux.size());
	}
}
