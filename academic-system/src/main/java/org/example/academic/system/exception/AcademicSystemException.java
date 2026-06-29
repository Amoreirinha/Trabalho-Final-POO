// AcademicSystemException.java - Classe base
package org.example.academic.system.exception;

public class AcademicSystemException extends Exception {
    public AcademicSystemException(String message) {
        super(message);
    }
    
    public AcademicSystemException(String message, Throwable cause) {
        super(message, cause);
    }
}