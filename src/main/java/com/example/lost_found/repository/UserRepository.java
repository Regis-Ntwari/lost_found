package com.example.lost_found.repository;

import com.example.lost_found.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    public User findByUsername(String username);
    public boolean existsByUsername(String username);
    public boolean existsByEmail(String email);
}
