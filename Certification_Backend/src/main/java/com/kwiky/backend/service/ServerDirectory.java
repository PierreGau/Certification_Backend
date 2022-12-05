package com.kwiky.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kwiky.backend.dao.ServerRepository;
import com.kwiky.backend.model.Message;
import com.kwiky.backend.model.Server;

@Service
public class ServerDirectory {
	@Autowired
	ServerRepository sr;
	
		// post server
		public Server addServer(Server newServer) {
			return sr.save(newServer);
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
		public boolean deleteServer(Long id) {
			boolean b = sr.existsById(id);
			if(b)
				sr.deleteById(id);
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
