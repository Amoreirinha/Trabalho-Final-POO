package org.example.academic.system.service;

import org.example.academic.system.exception.AcademicSystemException;
<<<<<<< HEAD
import org.example.academic.system.exception.AssessmentException;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.AcademicSystem;
import org.example.academic.system.model.Assessment;
import org.example.academic.system.model.Assignment;
import org.example.academic.system.model.Exam;
import org.example.academic.system.model.PracticalAssignment;
import org.example.academic.system.model.Seminar;
import org.example.academic.system.validation.DomainValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Serviço responsável pelo registro de avaliações (TUS-2397).
 */
public class AssessmentService {

    private static final Logger logger = LoggerFactory.getLogger(AssessmentService.class);
=======
import org.example.academic.system.model.*;

/**
 * US-2361: Serviço para cadastro de avaliações
 * TUS-2397: Introduzido para mover lógica de avaliações do controller
 * TUS-2402: Testes automatizados
 */
public class AssessmentService {

>>>>>>> 9643520f26d8d62b799ddda3ea5a0d2daf1a84f6
    private final AcademicSystem academicSystem;

    public AssessmentService() {
        this.academicSystem = AcademicSystem.getInstance();
    }

<<<<<<< HEAD
    public AssessmentService(AcademicSystem academicSystem) {
        this.academicSystem = academicSystem;
    }

    /**
     * Registra uma avaliação na turma indicada pelo código (US-2361).
     */
    public void registerAssessment(String classCode, String assessmentType,
                                   Double value, Double weight) {
        Optional<AcademicClass> optClass = academicSystem.findByCode(classCode);
        if (optClass.isEmpty()) {
            logger.warn("Tentativa de registrar avaliação em turma inexistente: {}", classCode);
            return; // AC6/AC7: não adiciona se turma não existe
        }

        Assessment assessment = createAssessment(assessmentType, value, weight);
        if (assessment == null) {
            logger.warn("Tipo de avaliação inválido: {}", assessmentType);
            return; // AC5/AC6: tipo inválido não adiciona
        }

        DomainValidator.validate(assessment);
        optClass.get().addAssessment(assessment);
        logger.info("Avaliação {} registrada na turma {}.", assessmentType, classCode);
    }

    private Assessment createAssessment(String type, Double value, Double weight) {
        return switch (type.toUpperCase()) {
            case "EXAM" -> new Exam(value, weight);
            case "PRACTICAL_ASSIGNMENT" -> new PracticalAssignment(value, weight);
            case "SEMINAR" -> new Seminar(value, weight);
            case "ASSIGNMENT" -> new Assignment(value, weight);
            default -> null;
        };
=======
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
>>>>>>> 9643520f26d8d62b799ddda3ea5a0d2daf1a84f6
    }
}
