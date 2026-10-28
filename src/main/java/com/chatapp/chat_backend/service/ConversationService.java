package com.chatapp.chat_backend.service;

import org.springframework.stereotype.Service;

import com.chatapp.chat_backend.repository.ConversationRepository;
import com.chatapp.chat_backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConversationService {
	private final ConversationRepository conversationRepository;
	
	private final UserRepository userRepository;
	
	
}
