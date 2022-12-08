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

import com.kwiky.backend.model.Message;
import com.kwiky.backend.model.Server;
import com.kwiky.backend.service.ServerDirectory;

@Controller
@RequestMapping("api")
public class ServerController 
{
	@Autowired
	private ServerDirectory sd;
	
	@GetMapping("servers")
	public List<Server> getAllServers()
	{
		return sd.getServers();
	}
	
	@GetMapping("servers/{id}")
	public ResponseEntity<Server> getServer(@PathVariable("id") Long id)
	{
		Optional<Server> o = sd.getServer(id);
		
		if(o.isPresent())
			return ResponseEntity.ok(o.get());
		else
			return ResponseEntity.notFound().build();
	}

	
	@PostMapping("servers")
	public ResponseEntity<Server> postServer(@RequestBody Server server)
	{
		return ResponseEntity.ok(sd.addServer(server));
	}
	
	@PostMapping("servers/list")
	public ResponseEntity<List<Server>> postServer(@RequestBody List<Server> servers)
	{
		return ResponseEntity.ok(sd.addAll(servers));
	}
	
	
	@DeleteMapping("servers/{id}")
	public ResponseEntity<Server> DeleteServer(@PathVariable("id")Long _id)
	{	
		boolean success = sd.deleteServer(_id);
		
		if(success)
			return ResponseEntity.ok().build();
		else
			return ResponseEntity.notFound().build();
	}
	
	@PutMapping("servers/{id}")
	public ResponseEntity<Server> updateServer(@RequestBody Server server, @PathVariable("id") Long id)
	{	
		if(server.getId().equals(id))
		{
			boolean success = sd.updateServer(server);
			if(success)
				return ResponseEntity.ok().build();
			else
				return ResponseEntity.notFound().build();				
		}

		return ResponseEntity.badRequest().build();
	}
}
