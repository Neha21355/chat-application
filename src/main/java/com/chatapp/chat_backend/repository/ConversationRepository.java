package com.chatapp.chat_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chatapp.chat_backend.entity.Conversation;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

	@Query("""
		    SELECT c FROM Conversation c
		    WHERE (c.user1.id = :user1Id AND c.user2.id = :user2Id)
		       OR (c.user1.id = :user2Id AND c.user2.id = :user1Id)
		    ORDER BY c.createdAt ASC
		    """)
		List<Conversation> findConversationsBetweenUsers(
		        @Param("user1Id") Long user1Id,
		        @Param("user2Id") Long user2Id
		);
}