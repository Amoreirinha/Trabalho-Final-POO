package org.example.academic.system.service;

import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.model.*;

/**
 * US-2361: Serviço para cadastro de avaliações
 * TUS-2397: Introduzido para mover lógica de avaliações do controller
 * TUS-2402: Testes automatizados
 */
public class AssessmentService {

    private final AcademicSystem academicSystem;

    public AssessmentService() {
        this.academicSystem = AcademicSystem.getInstance();
    }

    /**
     * AC1: Turma existe + dados válidos → avaliação adicionada
     * AC2: Cria Exam, PracticalAssignment, Seminar, Assignment
     * AC3: Valor e peso armazenados
     * AC4: Turma inexistente → não cadastra
     * AC5: Tipo inválido → não cadastra
     * AC6: Dados inválidos → AcademicSystemException
     * AC7: Avaliação aparece na lista da turma
     * AC8: Sem permissão → nega operação
     */
    public void registerAssessment(String classCode, String assessmentType,
                                   Double value, Double weight) {

        // AC3: Validar entrada
        if (classCode == null || classCode.trim().isEmpty()) {
            throw new AcademicSystemException("Código da turma é obrigatório");
        }
        if (value == null) {
            throw new AcademicSystemException("Valor da avaliação é obrigatório");
        }
        if (weight == null) {
            throw new AcademicSystemException("Peso da avaliação é obrigatório");
        }

        // AC4: Buscar turma (se não existe, NÃO cadastra)
        Turma targetTurma = academicSystem.findTurmaByCode(classCode);  // ← MUDOU
        if (targetTurma == null) {
            throw new AcademicSystemException("Turma com código '" + classCode + "' não encontrada!");
        }

        // AC2: Criar a avaliação conforme o tipo
        Assessment assessment = createAssessment(assessmentType, value, weight);

        // AC6: Validar a avaliação
        if (value < 0 || value > 10) {
            throw new AcademicSystemException("Valor deve estar entre 0 e 10");
        }
        if (weight < 0 || weight > 1) {
            throw new AcademicSystemException("Peso deve estar entre 0 e 1");
        }

        // AC1: Adicionar à turma
        targetTurma.addAssessment(assessment);  // ← MUDOU
    }

    /**
     * AC2: Cria Exam, PracticalAssignment, Seminar, Assignment
     */
    private Assessment createAssessment(String type, Double value, Double weight) {
        switch (type.toLowerCase()) {
            case "prova":
            case "exam":
                return new Exam(value, weight);
            case "trabalho prático":
            case "practicalassignment":
                return new PracticalAssignment(value, weight);
            case "seminário":
            case "seminar":
                return new Seminar(value, weight);
            case "trabalho":
            case "assignment":
                return new Assignment(value, weight);
            default:
                // AC5: Tipo inválido → não cadastra
                throw new AcademicSystemException("Tipo de avaliação inválido: " + type);
        }
    }
}
