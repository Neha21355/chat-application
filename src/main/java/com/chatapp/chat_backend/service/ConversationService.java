package com.chatapp.chat_backend.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.chatapp.chat_backend.dto.ConversationResponse;
import com.chatapp.chat_backend.entity.Conversation;
import com.chatapp.chat_backend.entity.User;
import com.chatapp.chat_backend.exception.ResourceNotFoundException;
import com.chatapp.chat_backend.mapper.ConversationMapper;
import com.chatapp.chat_backend.repository.ConversationRepository;
import com.chatapp.chat_backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConversationService {
	private final ConversationRepository conversationRepository;
	
	private final UserRepository userRepository;
	private final ConversationMapper conversationMapper;
	
	public ConversationResponse createConversation(Long user1Id, Long user2Id) {

	    if (user1Id.equals(user2Id)) {
	        throw new IllegalArgumentException(
	                "Cannot create conversation with yourself");
	    }

	    User user1 = userRepository.findById(user1Id)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("User not found: " + user1Id));

	    User user2 = userRepository.findById(user2Id)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("User not found: " + user2Id));

	    List<Conversation> conversations =
	            conversationRepository.findConversationsBetweenUsers(
	                    user1Id, user2Id);

	    if (!conversations.isEmpty()) {
	        return conversationMapper.toConversationResponse(
	                conversations.get(0));
	    }

	    Conversation conversation = new Conversation();
	    conversation.setUser1(user1);
	    conversation.setUser2(user2);

	    Conversation savedConversation =
	            conversationRepository.save(conversation);

	    return conversationMapper.toConversationResponse(savedConversation);
	}
	
	public Page<ConversationResponse> getAllConversations(Pageable pageable) {
	    return conversationRepository.findAll(pageable)
	            .map(conversationMapper::toConversationResponse);
	}
	
}
