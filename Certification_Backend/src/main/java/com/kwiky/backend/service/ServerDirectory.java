package com.kwiky.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kwiky.backend.dao.ServerRepository;
import com.kwiky.backend.model.Canal;
import com.kwiky.backend.model.Server;

@Service
public class ServerDirectory {
	@Autowired
	ServerRepository sr;
	
	@Autowired
	ServerUserDirectory serverUserDirectory;
	@Autowired
	ServerCanalDirectory serverCanalDirectory;
	
	
	
	// post server
	public Server addServer(Server newServer) {
		newServer.createGeneral();
		
		Server server = sr.save(newServer);
		serverUserDirectory.serverAddUser(server.getId(), server.getCreator());
		return server;
	}
		
	public List<Server> addAll(List<Server> servers)
	{
		for(Server s : servers)
			s.createGeneral();
		
		return sr.saveAll(servers);
	}
	
	public Server addCanal(Canal canal, Long serverID)
	{		
		Optional<Server> server = sr.findById(serverID);
		if(server.isPresent())
		{
			server.get().addCanal(canal);			
			return sr.save(server.get());
		}
		return null;
	}

	public List<Server> getServers() {
		List<Server> l = sr.findAll();
		return l;
	}

	// Get server by id
	public Optional<Server> getServer(Long id) {
		return sr.findById(id);
	}

	// delete server by id
	public boolean deleteServer(Long id) {
		boolean b = sr.existsById(id);
		if(b)
		{
			Server server = sr.findById(id).get();
			server = serverUserDirectory.deleteUserList(server);
			
			sr.delete(server);
		}
			
		return b;
	}

	/**Renvoie true si l'id existait*/
	public boolean updateServer(Server server)
	{
		boolean b = sr.existsById(server.getId());
		if(b)
			sr.save(server);
		
		return b;
	}
}
