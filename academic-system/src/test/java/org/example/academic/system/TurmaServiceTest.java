package org.example.academic.system;

import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.model.AcademicSystem;
import org.example.academic.system.model.Turma;
import org.example.academic.system.service.TurmaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TUS-2401: Testes automatizados para TurmaService
 */
class TurmaServiceTest {

    private TurmaService turmaService;
    private AcademicSystem academicSystem;

    @BeforeEach
    void setUp() {
        academicSystem = AcademicSystem.getInstance();
        academicSystem.getAllTurmas().clear();
        turmaService = new TurmaService();
    }

    @Test
    void testRegisterValidTurma() {
        turmaService.registerTurma("POO-2024-1", "Programação Orientada a Objetos");

        Turma turma = academicSystem.findTurmaByCode("POO-2024-1");
        assertNotNull(turma);
        assertEquals("POO-2024-1", turma.getCode());
        assertEquals("Programação Orientada a Objetos", turma.getTitle());
    }

    @Test
    void testRegisterTurmaWithBlankCode() {
        assertThrows(AcademicSystemException.class, () -> {
            turmaService.registerTurma("", "Título");
        });
    }

    @Test
    void testRegisterTurmaWithBlankTitle() {
        assertThrows(AcademicSystemException.class, () -> {
            turmaService.registerTurma("POO-2024", "");
        });
    }

    @Test
    void testRegisterDuplicateTurma() {
        turmaService.registerTurma("POO-2024-1", "POO");

        assertThrows(AcademicSystemException.class, () -> {
            turmaService.registerTurma("POO-2024-1", "Outro título");
        });
    }
}
