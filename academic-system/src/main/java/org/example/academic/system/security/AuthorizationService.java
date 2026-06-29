package org.example.academic.system.security;

import org.example.academic.system.exception.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthorizationService {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationService.class);
    private static AuthorizationService instance;
    private final AuthenticationService authService;
    
    private AuthorizationService() {
        this.authService = AuthenticationService.getInstance();
    }
    
    public static synchronized AuthorizationService getInstance() {
        if (instance == null) {
            instance = new AuthorizationService();
        }
        return instance;
    }
    
    public void checkPermission(Role requiredRole, String operation) throws AuthorizationException {
        User currentUser = authService.getCurrentUser();
        
        if (currentUser == null) {
            logger.warn("Tentativa de operação sem autenticação: {}", operation);
            throw new AuthorizationException("Usuário não autenticado");
        }
        
        if (!currentUser.hasRole(requiredRole)) {
            logger.warn("Falha de autorização: {} tentou acessar {} com papel {}", 
                currentUser.getUsername(), operation, currentUser.getRole());
            throw new AuthorizationException(
                String.format("Usuário %s não tem permissão para %s", 
                    currentUser.getUsername(), operation)
            );
        }
        
        logger.debug("Autorização concedida: {} para {}", currentUser.getUsername(), operation);
    }
    
    public boolean hasPermission(Role requiredRole) {
        User currentUser = authService.getCurrentUser();
        return currentUser != null && currentUser.hasRole(requiredRole);
    }
}