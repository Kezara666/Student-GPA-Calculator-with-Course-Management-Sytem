package com.example.demo2.model;

public class User {
    int id;
    String username;
    String Role;

    String email;

    public User(int id, String username, String role, String email) {
        this.id = id;
        this.username = username;
        Role = role;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return Role;
    }

    public String getEmail() {
        return email;
    }


}
