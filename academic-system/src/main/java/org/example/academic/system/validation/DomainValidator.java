package org.example.academic.system.validation;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import org.example.academic.system.exception.DomainException;
import java.util.Set;
import java.util.stream.Collectors;

public class DomainValidator {
    private static DomainValidator instance;
    private final Validator validator;
    
    private DomainValidator() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            this.validator = factory.getValidator();
        }
    }
    
    public static synchronized DomainValidator getInstance() {
        if (instance == null) {
            instance = new DomainValidator();
        }
        return instance;
    }
    
    public <T> void validate(T object) throws DomainException {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        
        if (!violations.isEmpty()) {
            String errorMessage = violations.stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .collect(Collectors.joining(", "));
            throw new DomainException("Erro de validação: " + errorMessage);
        }
    }
}