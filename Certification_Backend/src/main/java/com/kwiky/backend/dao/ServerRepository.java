package com.kwiky.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kwiky.backend.model.Server;

public interface ServerRepository extends JpaRepository<Server, Long>
{
	
}