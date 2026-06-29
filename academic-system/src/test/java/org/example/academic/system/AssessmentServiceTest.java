package org.example.academic.system;

<<<<<<< HEAD
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.AcademicSystem;
import org.example.academic.system.service.AssessmentService;
import org.example.academic.system.service.TurmaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
=======
import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.model.AcademicSystem;
import org.example.academic.system.model.Turma;
import org.example.academic.system.service.AssessmentService;
import org.junit.jupiter.api.BeforeEach;
>>>>>>> 9643520f26d8d62b799ddda3ea5a0d2daf1a84f6
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
<<<<<<< HEAD
 * TUS-2402 — Test AssessmentService behavior.
=======
 * TUS-2402: Testes automatizados para AssessmentService
>>>>>>> 9643520f26d8d62b799ddda3ea5a0d2daf1a84f6
 */
class AssessmentServiceTest {

    private AssessmentService assessmentService;
    private AcademicSystem academicSystem;
<<<<<<< HEAD
    private static final String CLASS_CODE = "BCC-ASSMT-TST";
=======
>>>>>>> 9643520f26d8d62b799ddda3ea5a0d2daf1a84f6

    @BeforeEach
    void setUp() {
        academicSystem = AcademicSystem.getInstance();
<<<<<<< HEAD
        assessmentService = new AssessmentService(academicSystem);
        // Ensure test class exists
        if (academicSystem.findByCode(CLASS_CODE).isEmpty()) {
            new TurmaService(academicSystem).registerClass(CLASS_CODE, "Classe de Teste");
        }
    }

    @Test
    @DisplayName("TUS-2402: Deve registrar avaliação em turma existente")
    void shouldRegisterAssessmentInExistingClass() {
        AcademicClass before = academicSystem.findByCode(CLASS_CODE).orElseThrow();
        int count = before.getAssessments().size();
        assessmentService.registerAssessment(CLASS_CODE, "EXAM", 7.0, 0.5);
        assertEquals(count + 1, before.getAssessments().size());
    }

    @Test
    @DisplayName("TUS-2402: Tipo de avaliação inválido não deve adicionar avaliação")
    void invalidTypeShouldNotAddAssessment() {
        AcademicClass turma = academicSystem.findByCode(CLASS_CODE).orElseThrow();
        int count = turma.getAssessments().size();
        assessmentService.registerAssessment(CLASS_CODE, "INVALID_TYPE", 7.0, 0.5);
        assertEquals(count, turma.getAssessments().size());
    }

    @Test
    @DisplayName("TUS-2402: Código de turma inexistente não deve adicionar avaliação")
    void nonExistentClassShouldNotAddAssessment() {
        // Should not throw, just silently ignore
        assertDoesNotThrow(() ->
            assessmentService.registerAssessment("NAOEXISTE", "EXAM", 7.0, 0.5));
=======
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
>>>>>>> 9643520f26d8d62b799ddda3ea5a0d2daf1a84f6
    }
}
