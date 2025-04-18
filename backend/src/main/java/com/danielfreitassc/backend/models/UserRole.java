package com.danielfreitassc.backend.models;

public enum UserRole {
    ADMIN("Admin"),
    USER_BASIC("User");
    
    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    } 
}
