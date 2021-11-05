package com.example.lost_found.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.example.lost_found.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImplementation implements UserDetails{
    private static final long serialVersionUID = 1L;

    private int id;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImplementation(int id, String username, String email, String password,
                        Collection<? extends GrantedAuthority> authorities) {
                this.authorities = authorities;
                this.id = id;
                this.username = username;
                this.email = email;
                this.password = password;
    }

    public static UserDetailsImplementation build (User user){
        List<GrantedAuthority> authorities = user.getRoles().stream()
                                            .map(role -> new SimpleGrantedAuthority(role.getUserType().name()))
                                            .collect(Collectors.toList());
        return new UserDetailsImplementation(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), authorities);


    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public int getId() {
        return id;
    }
    
}
