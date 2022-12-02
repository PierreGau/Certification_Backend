package com.kwiky.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kwiky.backend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
