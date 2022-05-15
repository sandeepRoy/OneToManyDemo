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
import com.sandeep.onetomany.model.Comment;
import com.sandeep.onetomany.model.Post;
import com.sandeep.onetomany.model.User;
import com.sandeep.onetomany.repository.CommentRepository;
import com.sandeep.onetomany.repository.PostRepository;
import com.sandeep.onetomany.repository.UserRepository;

@RestController
public class CommentController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@GetMapping("/users/{userId}/posts/{postId}/comments")
	public Page<Comment> getAllCommentsByUserIdAndPostId(@PathVariable (value = "userId") Long userId,
														 @PathVariable (value = "postId") Long postId,
														 Pageable pageable){
		return commentRepository.findByPostId(postId, pageable);
		}
	@PostMapping("/users/{userId}/posts/{postId}/comments")
	public Comment createComment(@PathVariable (value = "userId") Long userId,
								 @PathVariable (value = "postId") Long postId,
								 @Valid @RequestBody Comment comment) {
		return postRepository.findById(postId).map( post -> {
			comment.setPost(post);
			return commentRepository.save(comment);
		}).orElseThrow(() -> new ResourceNotFoundException("PostId: " + postId + "Not Found"));
	}
	}

