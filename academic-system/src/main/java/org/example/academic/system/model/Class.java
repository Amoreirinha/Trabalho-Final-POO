// Class.java
package org.example.academic.system.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Class {
    @NotBlank(message = "Código da turma é obrigatório")
    @Size(min = 3, max = 10, message = "Código deve ter entre 3 e 10 caracteres")
    private String code;
    
    @NotBlank(message = "Título da turma é obrigatório")
    @Size(min = 5, max = 100, message = "Título deve ter entre 5 e 100 caracteres")
    private String title;
    
    private List<Assessment> assessments = new ArrayList<>();
    
    public void addAssessment(Assessment assessment) {
        if (assessment != null) {
            assessments.add(assessment);
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Class aClass = (Class) o;
        return code != null && code.equals(aClass.code);
    }
    
    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }
}

// Assessment.java
package org.example.academic.system.model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assessment {
    @NotNull(message = "Tipo de avaliação é obrigatório")
    private AssessmentType type;
    
    @NotNull(message = "Valor é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "Valor deve ser maior que 0")
    @DecimalMax(value = "100.0", message = "Valor deve ser menor ou igual a 100")
    private Double value;
    
    @NotNull(message = "Peso é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "Peso deve ser maior que 0")
    @DecimalMax(value = "1.0", message = "Peso deve ser menor ou igual a 1.0")
    private Double weight;
}

// AssessmentType.java
package org.example.academic.system.model;

public enum AssessmentType {
    EXAM("Prova"),
    PRACTICAL_ASSIGNMENT("Trabalho Prático"),
    SEMINAR("Seminário"),
    ASSIGNMENT("Trabalho");
    
    private final String description;
    
    AssessmentType(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}