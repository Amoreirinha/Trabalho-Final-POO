package org.example.academic.system.controller;

import org.example.academic.system.model.User;
import org.example.academic.system.security.AuthenticationService;
import org.example.academic.system.exception.AuthenticationException;

/**
 * TUS-2414 - Introduce AuthenticationController for JavaFX login.
 *
 * Handles authentication requests from the JavaFX user interface,
 * decoupling the GUI from service implementations and following the
 * same architectural pattern used throughout the application.
 */
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Authenticates the user with the given credentials.
     *
     * @param username the username
     * @param password the password
     * @return the authenticated User
     * @throws AuthenticationException if credentials are invalid
     */
    public User authenticate(String username, String password) throws AuthenticationException {
        return authenticationService.authenticate(username, password);
    }
}
