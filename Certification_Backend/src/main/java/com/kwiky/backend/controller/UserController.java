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

import com.kwiky.backend.model.User;
import com.kwiky.backend.service.UserDirectory;

@RestController
@RequestMapping("api")
public class UserController {
	@Autowired
	UserDirectory userDirectory;

	@GetMapping("users")
	public List<User> getUsers() {
		return userDirectory.getUsers();
	}

	@GetMapping("users/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") Long _id) {
		Optional<User> o = userDirectory.getUser(_id);

		if (o.isPresent())
			return ResponseEntity.ok(o.get());
		else
			return ResponseEntity.notFound().build();
	}

	@PostMapping("users")
	public User postUser(@RequestBody User user) {
		userDirectory.addUser(user);
		return user;
	}

	@DeleteMapping("users/{id}")
	public void deleteUser(@PathVariable("id") Long _id) {
		userDirectory.deleteUser(_id);
	}

	@PutMapping("users/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") Long id) {
		if (id != user.getId()) {
			return ResponseEntity.badRequest().build();
		} else {

			userDirectory.putUser(user, id);
			return ResponseEntity.ok().build();

		}
	}

}
