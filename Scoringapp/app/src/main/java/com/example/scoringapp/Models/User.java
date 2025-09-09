package com.example.scoringapp.Models;

public class User {
    private String email;
    private String password;
    private String role; // "GV" hoặc "SV"

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;

    }
    // Getter / Setter
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
}
