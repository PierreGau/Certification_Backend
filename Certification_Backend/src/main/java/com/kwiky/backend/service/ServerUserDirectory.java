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
	ServerRepository serverRepository;
	
	@Autowired
	UserRepository userRepository;

	public boolean serverAddUser(long _serverId, User user)
	{
		boolean b = false;
		Optional<Server> s = serverRepository.findById(_serverId);
		Optional<User> userToAdd = userRepository.findById(user.getId());
		if(s.isPresent() && userToAdd.isPresent())
		{
			s.get().addUser(userToAdd.get());
			serverRepository.save(s.get());
			b=true;
		}	
		return b;
	}
	
	public boolean serverDelUser(long _serverId, User user)
	{
		boolean b = false;
		Optional<Server> s = serverRepository.findById(_serverId);
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
