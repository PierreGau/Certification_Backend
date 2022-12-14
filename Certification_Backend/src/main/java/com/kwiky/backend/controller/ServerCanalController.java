package com.kwiky.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kwiky.backend.model.Canal;
import com.kwiky.backend.model.Server;
import com.kwiky.backend.service.ServerCanalDirectory;

@RestController
@RequestMapping("api")
public class ServerCanalController 
{
	@Autowired
	private ServerCanalDirectory scd;
	
	@PostMapping("serverscanal/addcanal/{serverid}")
	public ResponseEntity<Server> postCanal(@PathVariable("serverid")Long _id, @RequestBody Canal canal)
	{	
		boolean b = scd.serverAddCanal(_id, canal);
		if(!b)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok().build();
	}
	

	@DeleteMapping("serverscanal/delcanal/{serverid}")
	public ResponseEntity<Server> delCanal(@PathVariable("serverid")Long _id, @RequestBody Canal canal)
	{	
		boolean b = scd.serverDelCanal(_id, canal);
		if(!b)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok().build();
	}
}
