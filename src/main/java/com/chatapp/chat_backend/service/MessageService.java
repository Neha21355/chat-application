
package com.chatapp.chat_backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chatapp.chat_backend.dto.MessageRequest;
import com.chatapp.chat_backend.dto.MessageResponse;
import com.chatapp.chat_backend.entity.Conversation;
import com.chatapp.chat_backend.entity.Message;
import com.chatapp.chat_backend.entity.User;
import com.chatapp.chat_backend.exception.ResourceNotFoundException;
import com.chatapp.chat_backend.mapper.MessageMapper;
import com.chatapp.chat_backend.repository.ConversationRepository;
import com.chatapp.chat_backend.repository.MessageRepository;
import com.chatapp.chat_backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;
    private final MessageMapper messageMapper;

    @Transactional
    public MessageResponse sendMessage(
            MessageRequest request, Long senderId) {

        Conversation conversation = conversationRepository
                .findById(request.getConversationId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Conversation not found: "
                                + request.getConversationId()));

        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found: " + senderId));

        boolean isParticipant =
                sender.getId().equals(conversation.getUser1().getId())
                || sender.getId().equals(conversation.getUser2().getId());

        if (!isParticipant) {
            throw new IllegalArgumentException(
                    "You are not a participant of this conversation");
        }

        Message message = new Message();
        message.setContent(request.getContent().trim());
        message.setConversation(conversation);
        message.setSender(sender);

        Message savedMessage = messageRepository.save(message);

        return messageMapper.toMessageResponse(savedMessage);
    }

    @Transactional(readOnly = true)
    public Page<MessageResponse> getConversationMessages(
            Long conversationId, Long requesterId, Pageable pageable) {

        Conversation conversation = conversationRepository
                .findById(conversationId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Conversation not found: " + conversationId));

        boolean isParticipant =
                requesterId.equals(conversation.getUser1().getId())
                || requesterId.equals(conversation.getUser2().getId());

        if (!isParticipant) {
            throw new IllegalArgumentException(
                    "You are not a participant of this conversation");
        }

        return messageRepository
                .findByConversationIdOrderByCreatedAtDesc(
                        conversationId, pageable)
                .map(messageMapper::toMessageResponse);
    }
}
