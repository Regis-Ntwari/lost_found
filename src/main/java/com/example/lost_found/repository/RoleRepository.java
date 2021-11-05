package com.example.lost_found.repository;

import com.example.lost_found.domain.Role;
import com.example.lost_found.domain.UserType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    public Role findByUserType(UserType userType);
}
