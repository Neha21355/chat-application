package com.chatapp.chat_backend.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.chatapp.chat_backend.dto.UserResponse;
import com.chatapp.chat_backend.entity.User;
import com.chatapp.chat_backend.mapper.UserMapper;
import com.chatapp.chat_backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private  final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserMapper userMapper;
	
	public UserResponse createUser(User user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser =  userRepository.save(user);
		
		return userMapper.toUserResponse(savedUser);
		
	}
	
//	public List<UserResponse> getAllUsers(){
//		return userRepository.findAll()
//				.stream()
//				.map(userMapper :: toUserResponse).
//				toList();
//	}
	
	public Page<UserResponse> getAllUsers(Pageable pageable) {
	    return userRepository.findAll(pageable)
	            .map(userMapper::toUserResponse);
	}
	
	public UserResponse getUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(()-> new ResponseStatusException(
				HttpStatus.NOT_FOUND, "user not found with id: "+ id
				));
		
		return userMapper.toUserResponse(user);
	}
	
	public UserResponse updateUser(Long id, User updatedUser) {
		
		User existingUser = userRepository.findById(id)
		.orElseThrow(()-> new ResponseStatusException(
				HttpStatus.NOT_FOUND,"User not found with id: " + id
				));
		
		existingUser.setUsername(updatedUser.getUsername());
		existingUser.setEmail(updatedUser.getEmail());
		
		User savedUser = userRepository.save(existingUser);
		
		return userMapper.toUserResponse(savedUser);
	}
	
	public void deleteUser(Long id) {
	    User user = userRepository.findById(id)
	            .orElseThrow(() -> new ResponseStatusException(
	                    HttpStatus.NOT_FOUND,
	                    "User not found with id: " + id
	            ));

	    userRepository.delete(user);
	}
}
