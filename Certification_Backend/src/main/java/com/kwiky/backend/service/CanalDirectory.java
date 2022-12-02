package com.kwiky.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kwiky.backend.dao.CanalRepository;
import com.kwiky.backend.model.Canal;

@Service
public class CanalDirectory {
	@Autowired
	private CanalRepository canalRepository;


	// post Canal
	public void addCanal(Canal newCanal) {
		canalRepository.save(newCanal);
	}

	// Get All Canal
	public List<Canal> getCanal() {
		return canalRepository.findAll();
	}

	// Get Canal by id
	public Optional<Canal> getCanal(Long id) {
		return canalRepository.findById(id);
	}

	// delete Canal by id
	public void deleteCanal(Long id) {
		canalRepository.deleteById(id);
	}

	// Put Canal by id
	public void putCanal(Canal canalToUpdate, Long id) {
		canalRepository.save(canalToUpdate);
	}
}
