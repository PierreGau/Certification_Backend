package com.kwiky.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kwiky.backend.model.Server;
import com.kwiky.backend.model.User;
import com.kwiky.backend.service.ServerUserDirectory;

@RestController
@RequestMapping("api")
public class ServerUserController {
	
	@Autowired
	ServerUserDirectory sud;//là ou il fais chaud
	
	@PostMapping("serversuser/adduser/{serverid}")
	public ResponseEntity<Server> postUser(@PathVariable("serverid")Long id, @RequestBody User user)
	{	
		boolean b = sud.serverAddUser(id, user);
		if(!b)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("serversuser/deluser/{serverid}")
	public ResponseEntity<Server> delUser(@PathVariable("serverid")Long id, @RequestBody User user)
	{	
		boolean b = sud.serverDelUser(id, user);
		if(!b)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok().build();
	}
}
