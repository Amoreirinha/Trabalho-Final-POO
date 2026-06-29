package org.example.academic.system;

import org.example.academic.system.model.AcademicSystem;
import org.example.academic.system.model.Turma;
import org.example.academic.system.service.AssessmentService;
import org.example.academic.system.service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TUS-2404: Testes automatizados para ReportService
 */
class ReportServiceTest {

    private ReportService reportService;
    private AssessmentService assessmentService;
    private AcademicSystem academicSystem;

    @BeforeEach
    void setUp() {
        academicSystem = AcademicSystem.getInstance();
        academicSystem.getAllTurmas().clear();
        reportService = new ReportService();
        assessmentService = new AssessmentService();
    }

    @Test
    void testGenerateClassAssessmentSummaryWithNoTurmas() {
        String report = reportService.generateClassAssessmentSummary();
        assertNotNull(report);
        assertTrue(report.contains("Nenhuma turma cadastrada"));
    }

    @Test
    void testGenerateClassAssessmentSummaryWithTurmas() {
        academicSystem.registerTurma(new Turma("POO-2024-1", "Programação Orientada a Objetos"));
        assessmentService.registerAssessment("POO-2024-1", "Prova", 8.5, 0.4);

        String report = reportService.generateClassAssessmentSummary();
        assertNotNull(report);
        assertTrue(report.contains("POO-2024-1"));
        assertTrue(report.contains("Prova"));
    }
}
