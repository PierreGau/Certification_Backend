package com.kwiky.backend.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kwiky.backend.dao.ServerRepository;
import com.kwiky.backend.dao.UserRepository;
import com.kwiky.backend.model.Server;
import com.kwiky.backend.model.User;

@Service
public class ServerUserDirectory {
	@Autowired
	ServerRepository sr;
	
	@Autowired
	UserRepository ur;

	public boolean serverAddUser(long _serverId, User user)
	{
		boolean b = false;
		Optional<Server> s = sr.findById(_serverId);
		Optional<User> userToAdd = ur.findById(user.getId());
		if(s.isPresent() && userToAdd.isPresent())
		{
			User u = userToAdd.get();
			u.addServer(s.get());
			ur.save(u);
			b=true;
		}	
		return b;
	}
	
	public boolean serverDelUser(long _serverId, User user)
	{
		boolean b = false;
		Optional<Server> s = sr.findById(_serverId);
		Optional<User> u = ur.findById(user.getId());
		if(s.isPresent() && u.isPresent())
		{
			User toDel = u.get();
			Server server = s.get();
			toDel.delServer(server);
			ur.save(toDel);
			b=true;
		}	
		return b;
	}
	
	public Server deleteUserList(Server server)
	{
		Set<User> list = server.getUsers();
		
		for(User u : list)
		{
			u.delServer(server);
		}
		
		return sr.save(server);	
	}
}
