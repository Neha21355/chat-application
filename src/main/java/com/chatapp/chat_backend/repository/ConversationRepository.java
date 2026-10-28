package com.chatapp.chat_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.chat_backend.entity.Conversation;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

}
