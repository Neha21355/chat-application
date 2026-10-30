
package com.chatapp.chat_backend.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.chatapp.chat_backend.dto.MessageRequest;
import com.chatapp.chat_backend.dto.MessageResponse;
import com.chatapp.chat_backend.service.MessageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    // Send a new message
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse sendMessage(
            @RequestBody MessageRequest request,
            @RequestParam Long senderId) {

        return messageService.sendMessage(request, senderId);
    }

    // Get messages of a conversation
    @GetMapping("/conversation/{conversationId}")
    public Page<MessageResponse> getConversationMessages(
            @PathVariable Long conversationId,
            @RequestParam Long requesterId,
            @PageableDefault(size = 20) Pageable pageable) {

        return messageService.getConversationMessages(
                conversationId, requesterId, pageable);
    }
}
