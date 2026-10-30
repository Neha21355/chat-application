package com.chatapp.chat_backend.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;
import com.chatapp.chat_backend.dto.ConversationResponse;
import com.chatapp.chat_backend.repository.ConversationRepository;
import com.chatapp.chat_backend.service.ConversationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/conversations")
@RequiredArgsConstructor
public class ConversationController {   
	
	private final ConversationService conversationService; 
	
	@PostMapping
	public ConversationResponse createConversation(@RequestParam Long user1Id,@RequestParam Long user2Id) {
		
		return conversationService.createConversation(user1Id, user2Id);
	}
	
	@GetMapping
	public Page<ConversationResponse> getAllConversations(
	        @PageableDefault(
	                size = 10,
	                sort = "createdAt",
	                direction = Sort.Direction.DESC
	        ) Pageable pageable) {

	    return conversationService.getAllConversations(pageable);
	}
}
