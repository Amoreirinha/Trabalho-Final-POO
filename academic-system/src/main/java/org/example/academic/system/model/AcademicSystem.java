package org.example.academic.system.model;

import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD
import java.util.Optional;

/**
 * Singleton que representa o estado do sistema acadêmico (US-0000).
 */
public class AcademicSystem {

    private static AcademicSystem instance;
    private final List<AcademicClass> classes = new ArrayList<>();

    private AcademicSystem() {}
=======

/**
 * US-0000: Singleton do sistema acadêmico
 */
public class AcademicSystem {
    private static AcademicSystem instance;
    private List<Turma> turmas;  // ← MUDOU de "classes" para "turmas"

    private AcademicSystem() {
        this.turmas = new ArrayList<>();
    }
>>>>>>> 9643520f26d8d62b799ddda3ea5a0d2daf1a84f6

    public static AcademicSystem getInstance() {
        if (instance == null) {
            instance = new AcademicSystem();
        }
        return instance;
    }

<<<<<<< HEAD
    public void registerClass(AcademicClass academicClass) {
        classes.add(academicClass);
    }

    public List<AcademicClass> getClasses() {
        return List.copyOf(classes);
    }

    public Optional<AcademicClass> findByCode(String code) {
        return classes.stream()
            .filter(c -> c.getCode().equals(code))
            .findFirst();
    }
}
=======
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
>>>>>>> 9643520f26d8d62b799ddda3ea5a0d2daf1a84f6
