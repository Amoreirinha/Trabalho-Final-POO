package org.example.academic.system.controller;

import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.model.AcademicSystem;
import org.example.academic.system.model.Turma;
import org.example.academic.system.service.AssessmentService;;

public class AcademicSystemController {

    private AcademicSystem academicSystem;
    private AssessmentService assessmentService;

    public AcademicSystemController() {
        this.academicSystem = AcademicSystem.getInstance();
        this.assessmentService = new AssessmentService();
    }

    // Método que o usuário chama para cadastrar avaliação
    public void registerAssessment(
            String classCode,
            String assessmentType,
            Double value,
            Double weight
    ) throws AcademicSystemException {

        // Delega para o serviço (que tem a lógica)
        assessmentService.registerAssessment(classCode, assessmentType, value, weight);
    }

    // Método para cadastrar turma
    public void registerClass(String code, String title) throws AcademicSystemException {
        Turma newTurma = new Turma(code, title);
        academicSystem.registerClass(newTurma);
    }
}
