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
	ServerRepository serverRepository;
	
	@Autowired
	UserRepository userRepository;

	public boolean serverAddUser(long serverId, User user)
	{
		boolean b = false;
		Optional<Server> s = serverRepository.findById(serverId);
		if(s.isPresent())
		{
			Server server = s.get();
			server.addUser(user);
			serverRepository.save(server);
			b=true;
		}	
		return b;
	}
	
	public boolean serverDelUser(long serverId, User user)
	{
		boolean b = false;
		Optional<Server> s = serverRepository.findById(serverId);
		Optional<User> u = userRepository.findById(user.getId());
		if(s.isPresent() && u.isPresent())
		{
			User toDel = u.get();
			Server server = s.get();
			server.delUser(toDel);
			serverRepository.save(server);
			b=true;
		}	
		return b;
	}
}
