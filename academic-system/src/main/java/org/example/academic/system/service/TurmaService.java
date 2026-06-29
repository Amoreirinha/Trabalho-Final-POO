package org.example.academic.system.service;

import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.model.AcademicSystem;
import org.example.academic.system.model.Turma;  // ← MUDOU import
import org.example.academic.system.validation.DomainValidator;

/**
 * US-2363: Serviço para cadastro de turmas
 * TUS-2396: Introduzido para mover lógica de turmas do controller
 * TUS-2401: Testes automatizados
 */
public class TurmaService {  // ← MUDOU nome da classe

    private final AcademicSystem academicSystem;

    public TurmaService() {
        this.academicSystem = AcademicSystem.getInstance();
    }

    /**
     * AC1: Admin autenticado + dados válidos → turma registrada
     * AC2: Turma armazenada no AcademicSystem
     * AC3: Dados inválidos → AcademicSystemException
     * AC4: Validação via DomainValidator
     * AC5: Usuário sem permissão → nega operação
     */
    public void registerTurma(String code, String title) {  // ← MUDOU nome do método
        // AC3: Validar entrada
        if (code == null || code.trim().isEmpty()) {
            throw new AcademicSystemException("Código da turma é obrigatório");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new AcademicSystemException("Título da turma é obrigatório");
        }

        // Verificar se turma já existe
        Turma existingTurma = academicSystem.findTurmaByCode(code);  // ← MUDOU
        if (existingTurma != null) {
            throw new AcademicSystemException("Turma com código '" + code + "' já existe!");
        }

        // AC1: Criar e validar a turma
        Turma newTurma = new Turma(code, title);  // ← MUDOU
        DomainValidator.validateTurma(newTurma);  // ← MUDOU

        // AC2: Armazenar no sistema
        academicSystem.registerTurma(newTurma);  // ← MUDOU
    }
}
