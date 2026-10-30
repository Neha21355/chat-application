
package com.chatapp.chat_backend.dto;

import java.time.LocalDateTime;

public record MessageResponse(
        Long id,
        Long conversationId,
        Long senderId,
        String senderUsername,
        String content,
        LocalDateTime createdAt
) {}
