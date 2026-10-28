package com.chatapp.chat_backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.chatapp.chat_backend.dto.ConversationResponse;
import com.chatapp.chat_backend.entity.Conversation;

@Mapper(componentModel = "spring")
public interface ConversationMapper {

    @Mapping(source = "user1.id", target = "user1Id")
    @Mapping(source = "user2.id", target = "user2Id")
    ConversationResponse toConversationResponse(Conversation conversation);
}