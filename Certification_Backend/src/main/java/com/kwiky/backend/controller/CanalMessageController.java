package com.kwiky.backend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kwiky.backend.model.Message;
import com.kwiky.backend.service.CanalMessageDirectory;

@RestController
@RequestMapping("api")
public class CanalMessageController
{
	@Autowired
	private CanalMessageDirectory canalMessageDirectory;
	
	@PostMapping("message/{id}")
	public ResponseEntity<Message> postMessage(@PathVariable("id") Long canalId, @RequestBody Message message)
	{
		Optional<Message> result = canalMessageDirectory.canalAddMessage(canalId, message);
		
		if(result.isPresent())
			return ResponseEntity.ok(result.get());
		else
			return ResponseEntity.badRequest().build();
	}
}
