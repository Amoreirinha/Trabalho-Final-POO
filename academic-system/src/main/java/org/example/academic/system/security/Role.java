package org.example.academic.system.security;

public enum Role {
    ADMIN,
    PROFESSOR;
    
    public boolean isAdmin() {
        return this == ADMIN;
    }
    
    public boolean isProfessor() {
        return this == PROFESSOR;
    }
}