
package com.chatapp.chat_backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.chatapp.chat_backend.dto.MessageResponse;
import com.chatapp.chat_backend.entity.Message;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mapping(target = "conversationId",
             source = "conversation.id")
    @Mapping(target = "senderId",
             source = "sender.id")
    @Mapping(target = "senderUsername",
             source = "sender.username")
    MessageResponse toMessageResponse(Message message);
}
