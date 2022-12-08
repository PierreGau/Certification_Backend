package com.kwiky.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kwiky.backend.model.Canal;
import com.kwiky.backend.model.Message;
import com.kwiky.backend.model.User;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByContentContains(String partialContentSearch);
    List<Message> findAllByUser(User userId);


}
