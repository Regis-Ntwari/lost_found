package com.example.lost_found.services;

import com.example.lost_found.domain.User;
import com.example.lost_found.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService{

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        try {
            user = userRepository.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO: handle exception
        }
        return UserDetailsImplementation.build(user);
    }
    
}
