package org.example.academic.system.controller;

import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.model.AcademicSystem;
import org.example.academic.system.model.Turma;
import org.example.academic.system.service.TurmaService;
import org.example.academic.system.service.AssessmentService;
import org.example.academic.system.service.ReportService;

/**
 * US-2364: Controlador principal do sistema acadêmico
 * TUS-2370: Refatorado para delegar operações aos serviços
 * TUS-2400: Simplificado para atuar apenas como camada de coordenação
 */
public class AcademicSystemController {

    private final TurmaService turmaService;
    private final AssessmentService assessmentService;
    private final ReportService reportService;

    public AcademicSystemController() {
        this.turmaService = new TurmaService();
        this.assessmentService = new AssessmentService();
        this.reportService = new ReportService();
    }

    // ============================================================
    // US-2363: CADASTRO DE TURMAS
    // ============================================================

    public void registerTurma(String code, String title) {
        turmaService.registerTurma(code, title);  //
    }

    // ============================================================
    // US-2361: CADASTRO DE AVALIAÇÕES
    // ============================================================

    public void registerAssessment(String classCode, String assessmentType,
                                   Double value, Double weight) {
        assessmentService.registerAssessment(classCode, assessmentType, value, weight);
    }

    // ============================================================
    // CONSULTAS
    // ============================================================

    public Turma findTurmaByCode(String code) {
        return AcademicSystem.getInstance().findTurmaByCode(code);
    }

    public java.util.List<Turma> getAllTurmas() {
        return AcademicSystem.getInstance().getAllTurmas();
    }

    // ============================================================
    // US-2375: RELATÓRIO DE RESUMO DE AVALIAÇÕES
    // ============================================================

    public String generateClassAssessmentSummary() {
        return reportService.generateClassAssessmentSummary();
    }

    // ============================================================
    // US-2376: RELATÓRIO DE PESOS
    // ============================================================

    public String generateAssessmentWeightReport() {
        return reportService.generateAssessmentWeightReport();
    }
}