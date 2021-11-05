package com.example.lost_found.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Role{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public Role( UserType userType) {
        super();
        this.userType = userType;
    }

    public Role() {
        super();
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    public void setUsers(Set<User> users) {
        this.users = users;
    }
    public int getId() {
        return id;
    }
    public UserType getUserType() {
        return userType;
    }
    public Set<User> getUsers() {
        return users;
    }
    
    
}