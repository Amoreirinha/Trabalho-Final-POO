// KeyboardInputException.java - Para erros de entrada do teclado (US-2368)
package org.example.academic.system.exception;

public class KeyboardInputException extends AcademicSystemException {
    public KeyboardInputException(String message) {
        super(message);
    }
    
    public KeyboardInputException(String message, Throwable cause) {
        super(message, cause);
    }
}

