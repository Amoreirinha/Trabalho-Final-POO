package org.example.academic.system;

import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.model.AcademicSystem;
<<<<<<< HEAD
import org.example.academic.system.service.TurmaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
=======
import org.example.academic.system.model.Turma;
import org.example.academic.system.service.TurmaService;
import org.junit.jupiter.api.BeforeEach;
>>>>>>> 9643520f26d8d62b799ddda3ea5a0d2daf1a84f6
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
<<<<<<< HEAD
 * TUS-2401 — Test ClassService (TurmaService) behavior.
=======
 * TUS-2401: Testes automatizados para TurmaService
>>>>>>> 9643520f26d8d62b799ddda3ea5a0d2daf1a84f6
 */
class TurmaServiceTest {

    private TurmaService turmaService;
    private AcademicSystem academicSystem;

    @BeforeEach
    void setUp() {
<<<<<<< HEAD
        // Use a fresh instance via reflection or just use the singleton  
        academicSystem = AcademicSystem.getInstance();
        turmaService = new TurmaService(academicSystem);
    }

    @Test
    @DisplayName("TUS-2401: ClassService deve registrar turma válida")
    void shouldRegisterValidClass() {
        int before = academicSystem.getClasses().size();
        turmaService.registerClass("BCC-TEST-" + System.nanoTime(), "Test Class");
        assertTrue(academicSystem.getClasses().size() > before);
    }

    @Test
    @DisplayName("TUS-2401: Código em branco deve lançar AcademicSystemException")
    void blankCodeShouldThrow() {
        assertThrows(AcademicSystemException.class,
            () -> turmaService.registerClass("", "Título válido"));
    }

    @Test
    @DisplayName("TUS-2401: Título em branco deve lançar AcademicSystemException")
    void blankTitleShouldThrow() {
        assertThrows(AcademicSystemException.class,
            () -> turmaService.registerClass("BCC001", ""));
=======
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
>>>>>>> 9643520f26d8d62b799ddda3ea5a0d2daf1a84f6
    }
}
