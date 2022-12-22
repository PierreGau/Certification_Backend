package com.kwiky.backend.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kwiky.backend.dao.ServerRepository;
import com.kwiky.backend.model.Canal;
import com.kwiky.backend.model.Message;
import com.kwiky.backend.model.Server;

@Service
public class ServerDirectory {
	@Autowired
	ServerRepository serverRepository;
	
	@Autowired
	ServerUserDirectory serverUserDirectory;
	@Autowired
	ServerCanalDirectory serverCanalDirectory;
	
	
	
	// post server
	public Server addServer(Server newServer) {
		newServer.createGeneral();	
		Server server = serverRepository.save(newServer);
		serverUserDirectory.serverAddUser(server.getId(), server.getCreator());
		return server;
	}

	public Server addCanal(Canal canal, Long serverID) {
		Optional<Server> server = serverRepository.findById(serverID);
		if (server.isPresent()) {
			server.get().addCanal(canal);
			return serverRepository.save(server.get());
		}
		return null;
	}
	
	public List<Server> getServersByUserId(Long id) {
		List<Server> liste = serverRepository.findByUsersId(id);
		for(Server s : liste)
		{
			List<Canal> sorter = new ArrayList<>(s.getCanaux());
			sorter.sort(Comparator.comparing(Canal::getId));
			Set<Canal> sortedCanaux = new LinkedHashSet<>(sorter);
			s.setCanaux(sortedCanaux);	
		}
		
		return liste;
	}
	// Get All servers
	public List<Server> getServers() {
		List<Server> liste =  serverRepository.findAll();
		
		for(Server s : liste)
		{
			List<Canal> sorter = new ArrayList<>(s.getCanaux());
			sorter.sort(Comparator.comparing(Canal::getId));
			Set<Canal> sortedCanaux = new LinkedHashSet<>(sorter);
			s.setCanaux(sortedCanaux);	
		}
		return liste;
	}

	// Get server by id
	public Optional<Server> getServer(Long id) {
		
		Optional<Server> s = serverRepository.findById(id);
		if(s.isPresent()) {
			List<Canal> sorter = new ArrayList<>(s.get().getCanaux());
			sorter.sort(Comparator.comparing(Canal::getId));
			Set<Canal> sortedCanaux = new LinkedHashSet<>(sorter);
			s.get().setCanaux(sortedCanaux);	
		}
		
		return s;
	}

	// delete server by id
	public boolean deleteServer(Long id) {
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
	}
		

	
}
