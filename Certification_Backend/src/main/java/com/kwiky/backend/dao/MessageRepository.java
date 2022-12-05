package com.kwiky.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kwiky.backend.model.Canal;
import com.kwiky.backend.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Canal> findAllByContentContains(String partialContentSearch);

}
