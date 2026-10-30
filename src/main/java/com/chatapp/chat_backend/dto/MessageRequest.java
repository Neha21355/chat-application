
package com.chatapp.chat_backend.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageRequest {

    @NotNull
    private Long conversationId;

    @NotBlank
    @Size(max = 5000)
    private String content;
}

