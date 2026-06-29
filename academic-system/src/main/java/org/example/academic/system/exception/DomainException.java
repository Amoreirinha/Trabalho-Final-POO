// DomainException.java - Para erros de domínio acadêmico (US-2367)
package org.example.academic.system.exception;

public class DomainException extends AcademicSystemException {
    public DomainException(String message) {
        super(message);
    }
    
    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
