package org.example.academic.system.model;

import java.util.ArrayList;
import java.util.List;

/**
 * US-0000: Singleton do sistema acadêmico
 */
public class AcademicSystem {
    private static AcademicSystem instance;
    private List<Turma> turmas;  // ← MUDOU de "classes" para "turmas"

    private AcademicSystem() {
        this.turmas = new ArrayList<>();
    }

    public static AcademicSystem getInstance() {
        if (instance == null) {
            instance = new AcademicSystem();
        }
        return instance;
    }

    public void registerTurma(Turma turma) {  // ← MUDOU nome do método
        if (turma != null) turmas.add(turma);
    }

    public Turma findTurmaByCode(String code) {  // ← MUDOU nome do método
        for (Turma t : turmas) {
            if (t.getCode().equals(code)) return t;
        }
        return null;
    }

    public List<Turma> getAllTurmas() {  // ← MUDOU nome do método
        return new ArrayList<>(turmas);
    }
}