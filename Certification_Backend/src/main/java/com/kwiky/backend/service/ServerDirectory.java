package com.kwiky.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kwiky.backend.dao.ServerRepository;
import com.kwiky.backend.model.Server;

@Service
public class ServerDirectory {
	@Autowired
	ServerRepository sr;
	
		// post server
		public void addServer(Server newServer) {
			sr.save(newServer);
		}

		// Get All Canal
		public List<Server> getServers() {
			return sr.findAll();
		}

		// Get server by id
		public Optional<Server> getServer(Long id) {
			return sr.findById(id);
		}

		// delete server by id
		public void deleteServer(Long id) {
			sr.deleteById(id);
		}

		// Put server by id
		public void putServer(Server serverToUpdate, Long id) {
			sr.save(serverToUpdate);
		}
}
