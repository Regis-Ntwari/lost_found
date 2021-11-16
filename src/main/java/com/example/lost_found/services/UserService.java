package com.example.lost_found.services;

import com.example.lost_found.domain.User;
import com.example.lost_found.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }
    public User findById(int id){
        return userRepository.findById(id).get();
    }
}
