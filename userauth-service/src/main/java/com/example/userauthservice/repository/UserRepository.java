package com.example.userauthservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.userauthservice.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
