package com.chatapp.chat_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.chatapp.chat_backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}