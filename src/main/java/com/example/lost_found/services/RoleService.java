package com.example.lost_found.services;

import com.example.lost_found.domain.Role;
import com.example.lost_found.domain.UserType;
import com.example.lost_found.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role findByUserType(UserType userType){
        return roleRepository.findByUserType(userType);
    }

    public Role saveNewRole(Role role){
        return roleRepository.saveAndFlush(role);
    }
}
