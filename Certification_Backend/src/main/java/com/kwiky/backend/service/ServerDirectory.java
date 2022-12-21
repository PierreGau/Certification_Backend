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
<<<<<<< HEAD
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
=======
	ServerRepository serverRepository;

	// post server
	public Server addServer(Server newServer) {
		newServer.createGeneral();
		return serverRepository.save(newServer);
	}

	public List<Server> addAll(List<Server> servers) {
		for (Server server : servers)
			server.createGeneral();

		return serverRepository.saveAll(servers);
	}

	public Server addCanal(Canal canal, Long serverID) {
		Optional<Server> server = serverRepository.findById(serverID);
		if (server.isPresent()) {
			server.get().addCanal(canal);
			return serverRepository.save(server.get());
>>>>>>> 94de4388fc71adb4f7910d736e7b4c1e422552ef
		}
		return null;
	}

<<<<<<< HEAD
	public List<Server> getServers() {
		List<Server> l = sr.findAll();
		return l;
	}
	
	public List<Server> getServersByUserId(Long id) {
		List<Server> l = sr.findByUsersId(id);
		return l;
=======
	// Get All Canal
	public List<Server> getServers() {
		return serverRepository.findAll();
>>>>>>> 94de4388fc71adb4f7910d736e7b4c1e422552ef
	}

	// Get server by id
	public Optional<Server> getServer(Long id) {
<<<<<<< HEAD
		return sr.findById(id);
=======
		return serverRepository.findById(id);
>>>>>>> 94de4388fc71adb4f7910d736e7b4c1e422552ef
	}

	// delete server by id
	public boolean deleteServer(Long id) {
<<<<<<< HEAD
		boolean b = sr.existsById(id);
		if(b)
		{
			Server server = sr.findById(id).get();
			
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
=======
		boolean serverExiste = serverRepository.existsById(id);
		if (serverExiste)
			serverRepository.deleteById(id);
		return serverExiste;
	}

	/** Renvoie true si l'id existait */
	public boolean updateServer(Server server) {
		boolean serverExiste = serverRepository.existsById(server.getId());
		if (serverExiste)
			serverRepository.save(server);

		return serverExiste;
>>>>>>> 94de4388fc71adb4f7910d736e7b4c1e422552ef
	}
}
