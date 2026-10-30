package com.chatapp.chat_backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.chat_backend.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
	
	 Page<Message> findByConversationIdOrderByCreatedAtDesc(
	            Long conversationId, Pageable pageable);
	
}
