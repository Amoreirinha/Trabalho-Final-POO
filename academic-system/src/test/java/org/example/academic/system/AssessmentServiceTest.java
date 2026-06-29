package org.example.academic.system;

import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.model.AcademicSystem;
import org.example.academic.system.model.Turma;
import org.example.academic.system.service.AssessmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TUS-2402: Testes automatizados para AssessmentService
 */
class AssessmentServiceTest {

    private AssessmentService assessmentService;
    private AcademicSystem academicSystem;

    @BeforeEach
    void setUp() {
        academicSystem = AcademicSystem.getInstance();
        academicSystem.getAllTurmas().clear();
        assessmentService = new AssessmentService();

        // Criar turma para os testes
        academicSystem.registerTurma(new Turma("POO-2024-1", "Programação Orientada a Objetos"));
    }

    @Test
    void testRegisterValidExam() {
        assessmentService.registerAssessment("POO-2024-1", "Prova", 8.5, 0.4);

        Turma turma = academicSystem.findTurmaByCode("POO-2024-1");
        assertEquals(1, turma.getAssessments().size());
        assertEquals(8.5, turma.getAssessments().get(0).getValue());
        assertEquals(0.4, turma.getAssessments().get(0).getWeight());
        assertEquals("Prova", turma.getAssessments().get(0).getType());
    }

    @Test
    void testRegisterAssessmentWithNonExistentTurma() {
        assertThrows(AcademicSystemException.class, () -> {
            assessmentService.registerAssessment("XPTO-2024", "Prova", 8.5, 0.4);
        });
    }
}
