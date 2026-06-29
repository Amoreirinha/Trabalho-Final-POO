// AuthenticationException.java - Para erros de autenticação (US-2369)
package org.example.academic.system.exception;

public class AuthenticationException extends AcademicSystemException {
    public AuthenticationException(String message) {
        super(message);
    }
    
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}

