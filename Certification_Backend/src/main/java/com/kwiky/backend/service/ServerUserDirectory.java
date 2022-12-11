package com.kwiky.backend.service;

import java.util.Optional;

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
		if(s.isPresent())
		{
			Server server = s.get();
			server.addUser(user);
			sr.save(server);
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
			server.delUser(toDel);
			sr.save(server);
			b=true;
		}	
		return b;
	}
}
