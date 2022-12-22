package com.kwiky.backend.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kwiky.backend.dao.CanalRepository;
import com.kwiky.backend.model.Canal;
import com.kwiky.backend.model.Message;

@Service
public class CanalDirectory {
	@Autowired
	private CanalRepository canalRepository;
	
	// post Canal
	public void addCanal(Canal newCanal) {
		canalRepository.save(newCanal);
	}

	// Get All Canal
	public List<Canal> getCanaux() {
		return canalRepository.findAll();
	}

	// Get Canal by id
	public Optional<Canal> getCanal(Long id) {
		
		Optional<Canal> canal = canalRepository.findById(id);
		
		if(canal.isPresent())
		{
			List<Message> sorter = new ArrayList<>(canal.get().getMessages());
			sorter.sort(Comparator.comparing(Message::getPostTime));
			Set<Message> sortedMessages = new LinkedHashSet<>(sorter);
			canal.get().setMessages(sortedMessages);
		}
		return canal;
	}

	// delete Canal by id
	public void deleteCanal(Long id) {
		if(canalRepository.existsById(id))
			canalRepository.deleteById(id);
	}

	// Put Canal by id
	public void putCanal(Canal canalToUpdate, Long id) {
		canalRepository.save(canalToUpdate);
	}

	public List<Canal> searchByName(String nameSearch) {
		return canalRepository.findAllByName(nameSearch);
	}
	
	public List<Canal> searchByNameContains(String partialNameSearch) {
		return canalRepository.findAllByNameContains(partialNameSearch);
	}
}
