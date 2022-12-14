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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kwiky.backend.model.Canal;
import com.kwiky.backend.model.Message;
import com.kwiky.backend.model.User;
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
	public ResponseEntity<Message> getMessage(@PathVariable("id")Long id)
	{		
		Optional<Message> message = messageDirectory.getMessage(id);
		
		if(message.isPresent())		
			return ResponseEntity.ok(message.get());			
		else
			return ResponseEntity.notFound().build();
	}
	
	@PostMapping("messages")
	public ResponseEntity<Message> postMessage(@RequestBody Message message)
	{
	
		if(message.getUser()==null || message.getCanal()==null)
			return ResponseEntity.badRequest().build();
		else
		{
			return ResponseEntity.ok(messageDirectory.add(message));
		}
		
	}
	
	@DeleteMapping("messages/{id}")
	public ResponseEntity<Message> DeleteMessage(@PathVariable("id")Long id)
	{	
		boolean success = messageDirectory.deleteMessage(id);
		
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
	
	
	// Recherche de messages en utilisant seulement une partie de son contenu
	// Par exemple, si on cherche les messages contenant "M2i" dans leur contenu, le résultat sera une liste 
	// de messages ayant le mot "M2i" à l'interieur de leur contenu
	@GetMapping("searchcontentbycontains")
	public List<Message> searchContentByContains(@RequestParam("content") String partialContentSearch){
		System.out.println(partialContentSearch);
		return messageDirectory.searchByContentContains(partialContentSearch);
	}
	
	// Recherche des messages écrits par un utilisaeur en particulier
	@GetMapping("searchmessagebyuserid")
	public List<Message> searchByMessageUserId(@RequestBody User userId){
		System.out.println(userId);
		return messageDirectory.searchMessageByUserId(userId);
	}
}
