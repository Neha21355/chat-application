package com.chatapp.chat_backend.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.chatapp.chat_backend.dto.UserResponse;
import com.chatapp.chat_backend.entity.User;
import com.chatapp.chat_backend.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	@PostMapping
	public UserResponse createuser(@RequestBody User user) {
	    if (user.getUsername() == null || user.getUsername().isBlank()
	            || user.getEmail() == null || user.getEmail().isBlank()
	            || user.getPassword() == null || user.getPassword().isBlank()) {

	        throw new ResponseStatusException(
	                HttpStatus.BAD_REQUEST,
	                "Username, email and password are required"
	        );
	    }

	    return userService.createUser(user);
	}
	
	
//	@GetMapping
//	public List<UserResponse> getAllUsers() {
//	    return userService.getAllUsers();
//	}
	
	@GetMapping
	public Page<UserResponse> getAllUsers(
	        @PageableDefault(
	                size = 10,
	                sort = "id",
	                direction = Sort.Direction.ASC
	        ) Pageable pageable) {

	    return userService.getAllUsers(pageable);
	}
	
	
	@GetMapping("/{id}")
	public UserResponse getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}
	
	@PutMapping("/{id}")
	public UserResponse updateUser(@PathVariable Long id, @RequestBody User user) {
		
		return userService.updateUser(id, user);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
	    userService.deleteUser(id);
	    return ResponseEntity.noContent().build();
	}
	
}
