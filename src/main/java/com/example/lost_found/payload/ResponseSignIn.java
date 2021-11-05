package com.example.lost_found.payload;

import java.util.List;

public class ResponseSignIn {
    private String jwt;
    private String type = "Bearer";
    private int id;
    private String username;
    private String email;
    private List<String> roles;

    public ResponseSignIn(String jwt, int id, String username, String email, List<String> roles) {
        super();
        this.jwt = jwt;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public int getId() {
        return id;
    }
    public String getJwt() {
        return jwt;
    }
    public List<String> getRoles() {
        return roles;
    }
    public String getType() {
        return type;
    }
    public String getUsername() {
        return username;
    }
}
