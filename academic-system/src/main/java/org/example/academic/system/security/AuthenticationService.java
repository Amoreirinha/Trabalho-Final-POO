package org.example.academic.system.security;

import org.example.academic.system.exception.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationService {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
    private static AuthenticationService instance;
    private final Map<String, User> users;
    private User currentUser;
    
    private AuthenticationService() {
        users = new HashMap<>();
        initializeDefaultUsers();
    }
    
    public static synchronized AuthenticationService getInstance() {
        if (instance == null) {
            instance = new AuthenticationService();
        }
        return instance;
    }
    
    private void initializeDefaultUsers() {
        users.put("admin", new User("admin", "admin123", Role.ADMIN));
        users.put("professor", new User("professor", "prof123", Role.PROFESSOR));
    }
    
    public User authenticate(String username, String password) throws AuthenticationException {
        if (username == null || username.trim().isEmpty()) {
            logger.warn("Tentativa de login com username vazio");
            throw new AuthenticationException("Username não pode ser vazio");
        }
        
        User user = users.get(username);
        
        if (user == null) {
            logger.warn("Tentativa de login com usuário inexistente: {}", username);
            throw new AuthenticationException("Usuário ou senha inválidos");
        }
        
        if (!user.getPassword().equals(password)) {
            logger.warn("Tentativa de login com senha incorreta para usuário: {}", username);
            throw new AuthenticationException("Usuário ou senha inválidos");
        }
        
        currentUser = user;
        logger.info("Login bem-sucedido: {} ({})", username, user.getRole());
        return user;
    }
    
    public void logout() {
        if (currentUser != null) {
            logger.info("Logout realizado: {}", currentUser.getUsername());
            currentUser = null;
        }
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public boolean isAuthenticated() {
        return currentUser != null;
    }
}