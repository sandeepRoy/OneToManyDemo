package com.sandeep.onetomany.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sandeep.onetomany.model.User;
// import com.sandeep.onetomany.repository.PostRepository;
import com.sandeep.onetomany.repository.UserRepository;
import com.sandeep.onetomany.exception.*;

@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private PostRepository postRepository;
	
	@GetMapping("/users")
	public Page<User> getAllUsers(Pageable pagable) {
		return userRepository.findAll(pagable);
	}
	
	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}
	
	@PutMapping("/users/{userId}")
	public User updateUser(@PathVariable Long userId, @Valid @RequestBody User userRequest) {
		return userRepository.findById(userId).map(user -> {
			user.setName(userRequest.getName());
			return userRepository.save(user);
		}).orElseThrow(() -> new ResourceNotFoundException("UserId:" + userId + "Not Found."));
	}
	
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
		return userRepository.findById(userId).map(user -> {
			userRepository.delete(user);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("UserId: " + userId + "Not Found."));
	}
}
