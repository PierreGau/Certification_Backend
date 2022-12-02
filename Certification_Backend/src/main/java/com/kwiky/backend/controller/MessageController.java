package com.kwiky.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kwiky.backend.model.Message;
import com.kwiky.backend.service.MessageDirectory;


@RestController
@RequestMapping("api")
public class MessageController
{
	@Autowired
	MessageDirectory messageDirectory;
	
	@GetMapping("messages")
	public List<Message> getMessagges()
	{
		return messageDirectory.getMessages();
	}
	
	@GetMapping("messages/{id}")
	public ResponseEntity<Message> getMessage(@PathVariable("id")Long _id)
	{		
		Optional<Message> o = messageDirectory.getMessage(_id);
		
		if(o.isPresent())		
			return ResponseEntity.ok(o.get());			
		else
			return ResponseEntity.notFound().build();
	}
	
	@PostMapping("messages")
	public Message postMessage(@RequestBody Message message)
	{
		messageDirectory.add(message);
		return message;
	}
	
	@DeleteMapping("messages/{id}")
	public ResponseEntity<Message> DeleteMessage(@PathVariable("id")Long _id)
	{	
		boolean success = messageDirectory.deleteMessage(_id);
		
		if(success)
			return ResponseEntity.ok().build();
		else
			return ResponseEntity.notFound().build();
	}
	
	@PutMapping("messages/{id}")
	public ResponseEntity<Message> updateMessage(@RequestBody Message message, @PathVariable("id") Long id)
	{	
		if(message.getId().equals(id))
		{
			boolean success = messageDirectory.updateMessage(message);
			if(success)
				return ResponseEntity.ok().build();
			else
				return ResponseEntity.notFound().build();				
		}

		return ResponseEntity.badRequest().build();
	}
}
