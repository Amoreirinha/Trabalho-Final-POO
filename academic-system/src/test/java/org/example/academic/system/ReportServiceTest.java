package org.example.academic.system;

<<<<<<< HEAD
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.AcademicSystem;
import org.example.academic.system.model.Exam;
import org.example.academic.system.repository.PersistenceType;
import org.example.academic.system.service.PersistenceService;
import org.example.academic.system.service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
=======
import org.example.academic.system.model.AcademicSystem;
import org.example.academic.system.model.Turma;
import org.example.academic.system.service.AssessmentService;
import org.example.academic.system.service.ReportService;
import org.junit.jupiter.api.BeforeEach;
>>>>>>> 9643520f26d8d62b799ddda3ea5a0d2daf1a84f6
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
<<<<<<< HEAD
 * TUS-2404 — Test ReportService behavior.
=======
 * TUS-2404: Testes automatizados para ReportService
>>>>>>> 9643520f26d8d62b799ddda3ea5a0d2daf1a84f6
 */
class ReportServiceTest {

    private ReportService reportService;
<<<<<<< HEAD
=======
    private AssessmentService assessmentService;
>>>>>>> 9643520f26d8d62b799ddda3ea5a0d2daf1a84f6
    private AcademicSystem academicSystem;

    @BeforeEach
    void setUp() {
        academicSystem = AcademicSystem.getInstance();
<<<<<<< HEAD
        PersistenceService persistenceService = new PersistenceService();
        reportService = new ReportService(academicSystem, persistenceService);

        // Register a class with an assessment for report testing
        if (academicSystem.findByCode("RPT-TEST").isEmpty()) {
            AcademicClass c = new AcademicClass("RPT-TEST", "Turma de Relatório");
            c.addAssessment(new Exam(8.5, 0.6));
            academicSystem.registerClass(c);
        }
    }

    @Test
    @DisplayName("TUS-2404: Relatório de avaliações deve conter dados da turma")
    void classSummaryReportShouldContainClassData() {
        String report = reportService.generateClassSummaryReport("ADMIN");
        assertNotNull(report);
        assertTrue(report.contains("RPT-TEST"));
    }

    @Test
    @DisplayName("TUS-2404: Relatório de pesos deve calcular total corretamente")
    void weightReportShouldCalculateTotalWeight() {
        String report = reportService.generateWeightReport("ADMIN");
        assertNotNull(report);
        assertFalse(report.isBlank());
    }

    @Test
    @DisplayName("TUS-2404: Relatório de persistência deve mostrar tipo configurado")
    void persistenceReportShouldShowCurrentType() {
        String report = reportService.generatePersistenceReport("ADMIN");
        assertNotNull(report);
        assertTrue(report.contains("TXT"));
=======
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
>>>>>>> 9643520f26d8d62b799ddda3ea5a0d2daf1a84f6
    }
}
