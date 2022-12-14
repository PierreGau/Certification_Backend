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
		newUser.setActif(true);
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
	
		Optional<User> user = userRepository.findById(id);
		
		// Si le user n'existe pas ou n'est pas actif
		if (!user.isPresent() || !user.get().isActif()) {
			// on sort du if
			return;
		}
		
		// le user devient inactif (il est toujours présent dans la BDD
		user.get().setActif(false);

		// Sauvegarde de l'objet user en tant que user inactif
		userRepository.save(user.get());
	}

	// Put User by id
	public void putUser(User newUser, Long id) {
		userRepository.save(newUser);
	}
}
