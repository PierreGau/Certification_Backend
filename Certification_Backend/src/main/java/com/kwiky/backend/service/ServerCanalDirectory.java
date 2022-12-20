package com.kwiky.backend.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kwiky.backend.dao.CanalRepository;
import com.kwiky.backend.dao.ServerRepository;
import com.kwiky.backend.model.Canal;
import com.kwiky.backend.model.Server;
import com.kwiky.backend.model.User;

@Service
public class ServerCanalDirectory 
{
	@Autowired
	private CanalRepository cr;
	
	@Autowired
	private ServerRepository sr;
	
	public boolean serverAddCanal(long _serverId, Canal canal)
	{
		boolean b = false;
		Optional<Server> s = sr.findById(_serverId);
		if(s.isPresent())
		{
			Server server = s.get();
			server.addCanal(canal);
			sr.save(server);
			b=true;
		}	
		return b;
	}
	
	public boolean serverDelCanal(long _serverId, Canal canal)
	{
		boolean b = false;
		Optional<Server> s = sr.findById(_serverId);
		Optional<Canal> c = cr.findById(canal.getId());
		if(s.isPresent() && c.isPresent())
		{
			Canal toDel = c.get();
			Server server = s.get();
			if(!toDel.getGeneral())
			{
				server.delCanal(toDel);
				sr.save(server);
				cr.delete(toDel);
				b=true;
			}
		}	
		return b;
	}
}
