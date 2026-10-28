package com.chatapp.chat_backend.mapper;

import org.mapstruct.Mapper;

import com.chatapp.chat_backend.dto.UserResponse;
import com.chatapp.chat_backend.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserResponse toUserResponse(User user);
}
