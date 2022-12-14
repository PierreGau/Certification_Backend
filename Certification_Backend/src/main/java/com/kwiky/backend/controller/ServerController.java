package com.kwiky.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kwiky.backend.model.Canal;
import com.kwiky.backend.model.Server;
import com.kwiky.backend.service.ServerDirectory;

@Controller
@RequestMapping("api")
public class ServerController {
	
	@Autowired
	private ServerDirectory serverDirectory;

	@GetMapping("servers")
	public List<Server> getAllServers() {
		return serverDirectory.getServers();
	}

	@GetMapping("servers/{id}")
	public ResponseEntity<Server> getServer(@PathVariable("id") Long id) {
		Optional<Server> server = serverDirectory.getServer(id);

		if (server.isPresent())
			return ResponseEntity.ok(server.get());
		else
			return ResponseEntity.notFound().build();
	}

	@PostMapping("servers")
	public ResponseEntity<Server> postServer(@RequestBody Server server) {
		return ResponseEntity.ok(serverDirectory.addServer(server));
	}

	@PostMapping("servers/list")
	public ResponseEntity<List<Server>> postServer(@RequestBody List<Server> servers) {
		return ResponseEntity.ok(serverDirectory.addAll(servers));
	}

	@DeleteMapping("servers/suprcanal/{id}")
	public ResponseEntity<Server> postServer(@PathVariable("id") Long id, @RequestBody Canal canal) {
		if (canal.getId() == 0 || canal.getId() == null) {
			return ResponseEntity.notFound().build();
		} else if (canal.getGeneral())
			return ResponseEntity.badRequest().build();
		else {
			Server server = serverDirectory.addCanal(canal, id);
			if (server == null)
				return ResponseEntity.notFound().build();
			else
				return ResponseEntity.ok(server);
		}
	}

	@DeleteMapping("servers/{id}")
	public ResponseEntity<Server> DeleteServer(@PathVariable("id") Long id) {
		boolean success = serverDirectory.deleteServer(id);

		if (success)
			return ResponseEntity.ok().build();
		else
			return ResponseEntity.notFound().build();
	}

	@PutMapping("servers/{id}")
	public ResponseEntity<Server> updateServer(@RequestBody Server server, @PathVariable("id") Long id) {
		if (server.getId().equals(id)) {
			boolean success = serverDirectory.updateServer(server);
			if (success)
				return ResponseEntity.ok().build();
			else
				return ResponseEntity.notFound().build();
		}

		return ResponseEntity.badRequest().build();
	}
}
