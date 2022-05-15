package com.sandeep.onetomany.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sandeep.onetomany.exception.ResourceNotFoundException;
import com.sandeep.onetomany.model.Post;
import com.sandeep.onetomany.repository.PostRepository;
import com.sandeep.onetomany.repository.UserRepository;

@RestController
public class PostController {
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/users/{userId}/posts")
	public Page<Post> getAllPostsByUserId(@PathVariable (value = "userId") Long userId, 
										  Pageable pagable) {
		return postRepository.findByUserId(userId, pagable);
	}
	
	@PostMapping("/users/{userId}/posts")
	public Post createPost(@PathVariable (value = "userId") Long userId,
					       @Valid @RequestBody Post post,
			               Pageable pageable) {
		return userRepository.findById(userId).map( user -> {
			post.setUser(user);
			return postRepository.save(post);
		}).orElseThrow(() -> new ResourceNotFoundException("User Id: " + userId + "Not Found."));
	}
}
