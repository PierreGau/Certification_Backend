package com.kwiky.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kwiky.backend.model.Canal;

public interface CanalRepository extends JpaRepository<Canal, Long>{
    List<Canal> findAllByName(String nameSearch);
    List<Canal> findAllByNameContains(String partialNameSearch);
}
