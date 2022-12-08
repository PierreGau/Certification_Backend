package com.kwiky.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kwiky.backend.dao.MessageRepository;
import com.kwiky.backend.model.Canal;
import com.kwiky.backend.model.Message;
import com.kwiky.backend.model.User;




@Service
public class MessageDirectory
{
	@Autowired
	MessageRepository mr;
	
	public Message add(Message message)
	{		
			return mr.save(message);		
	}
	
	public void add(List<Message> messages)
	{
		mr.saveAll(messages);
	}
	
	public List<Message> getMessages()
	{	
		return mr.findAll();
	}
	
	public Optional<Message> getMessage(Long _id)
	{
		return mr.findById(Long.valueOf(_id));
	}
	
	/**Renvoie true si l'id existait*/
	public boolean deleteMessage(Long _id)
	{
		boolean b = mr.existsById(_id);
		if(b)
			mr.deleteById(Long.valueOf(_id));
		return b;
	}

	/**Renvoie true si l'id existait*/
	public boolean updateMessage(Message message)
	{
		boolean b = mr.existsById(message.getId());
		if(b && message.getCanal() != null && message.getUser() != null)
			mr.save(message);
		
		return b;
	}

	public List<Message> searchByContentContains(String partialContentSearch) {
		return mr.findAllByContentContains(partialContentSearch);
	}
	
	public List<Message> searchMessageByUserId(User userId) {
		return mr.findAllByUser(userId);
	}
}
