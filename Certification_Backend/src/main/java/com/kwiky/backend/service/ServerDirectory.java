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
		}
		return null;
	}

	// Get All Canal
	public List<Server> getServers() {
		return serverRepository.findAll();
	}

	// Get server by id
	public Optional<Server> getServer(Long id) {
		return serverRepository.findById(id);
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
