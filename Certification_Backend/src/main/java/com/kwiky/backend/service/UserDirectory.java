package com.kwiky.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kwiky.backend.dao.UserRepository;
import com.kwiky.backend.model.User;

@Service
public class UserDirectory {

	@Autowired
	private UserRepository userRepository;


	// post User
	public void addUser(User newUser) {
		userRepository.save(newUser);
	}

	// Get All User
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	// Get User by id
	public Optional<User> getUser(Long id) {
		return userRepository.findById(id);
	}

	// delete User by id
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	// Put User by id
	public void putUser(User userToUpdate, Long id) {
		userRepository.save(userToUpdate);
	}
}
