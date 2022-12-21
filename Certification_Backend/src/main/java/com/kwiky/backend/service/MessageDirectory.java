package com.kwiky.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kwiky.backend.dao.MessageRepository;
import com.kwiky.backend.model.Message;
import com.kwiky.backend.model.User;

@Service
public class MessageDirectory {
	@Autowired
	MessageRepository messageRepository;

	public Message add(Message message) {
		return messageRepository.save(message);
	}
	public void add(List<Message> messages) {
		messageRepository.saveAll(messages);
	}

	public Optional<Message> getMessage(Long id) {
		return messageRepository.findById(Long.valueOf(id));
	}
	
	public List<Message> getMessages() {
		return messageRepository.findAll();
	}

	/** Renvoie true si l'id existait */
	public boolean deleteMessage(Long id) {
		// 1 - verifier l'existance du message
		boolean messageExiste = messageRepository.existsById(id);
		// 2 - suppression du message s'il existe
		if (messageExiste) {			
			messageRepository.deleteById(Long.valueOf(id));
		}
		return messageExiste;
	}

	/**Renvoie true si l'id existait*/
	public boolean updateMessage(Message message)
	{
		boolean b = messageRepository.existsById(message.getId());
		if(b && message.getUser() != null)
			messageRepository.save(message);

		return b;
	}

	public List<Message> searchByContentContains(String partialContentSearch) {
		return messageRepository.findAllByContentContains(partialContentSearch);
	}

	public List<Message> searchMessageByUserId(User userId) {
		return messageRepository.findAllByUser(userId);
	}
}
