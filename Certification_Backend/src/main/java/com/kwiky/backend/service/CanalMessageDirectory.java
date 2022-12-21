package com.kwiky.backend.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kwiky.backend.dao.CanalRepository;
import com.kwiky.backend.dao.MessageRepository;
import com.kwiky.backend.model.Canal;
import com.kwiky.backend.model.Message;

@Service
public class CanalMessageDirectory {
	@Autowired 
	CanalRepository canalRepository;
	
	@Autowired 
	MessageRepository messageRepository;
	
	public Optional<Message> canalAddMessage(Long canalId, Message message)
	{
		Optional<Canal> canal = canalRepository.findById(canalId);
		
		if(canal.isPresent())
		{
			message.setPostTime(LocalDateTime.now());
			canal.get().addMessage(message);
			canalRepository.save(canal.get());
			return Optional.of(message);
		}
		else
		{
			return Optional.of(null);
		}
	}
}
