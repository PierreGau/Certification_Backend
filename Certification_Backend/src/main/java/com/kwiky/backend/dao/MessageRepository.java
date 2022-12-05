package com.kwiky.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kwiky.backend.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
