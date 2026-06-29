// AuthorizationException.java - Para erros de autorização (US-2369)
package org.example.academic.system.exception;

public class AuthorizationException extends AcademicSystemException {
    public AuthorizationException(String message) {
        super(message);
    }
    
    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }
}