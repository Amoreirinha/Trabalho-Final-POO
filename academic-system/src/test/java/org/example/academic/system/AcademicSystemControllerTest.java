package org.example.academic.system;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * TUS-2405 — Test AcademicSystemController delegation behavior.
 *
 * <p><b>Atualização:</b> o {@code AcademicSystemController} já foi criado
 * (commit "Implementado AcademicSystemController com methodos assessment"),
 * mas ele importa três classes que ainda não existem em nenhum lugar do
 * repositório:
 * <ul>
 *   <li>{@code org.example.academic.system.model.AcademicSystem}</li>
 *   <li>{@code org.example.academic.system.model.Turma}</li>
 *   <li>{@code org.example.academic.system.service.AssessmentService}</li>
 * </ul>
 * Ou seja, o controller em si ainda não compila — não é só esse teste que
 * está bloqueado, é o build inteiro. Vale avisar o grupo: o pacote
 * {@code model} (com a classe de domínio principal) nunca foi criado, e
 * várias telas JavaFX e o PersistenceService já importam
 * {@code model.AcademicClass} / {@code model.Assessment} / {@code model.User}
 * / {@code model.Role} — só que {@code User}/{@code Role} hoje existem em
 * {@code security}, não em {@code model}. E o controller usa o nome
 * {@code Turma} enquanto o resto do código usa {@code AcademicClass} pro
 * que parece ser o mesmo conceito — essa inconsistência de nomes precisa
 * ser resolvida pelo time antes de qualquer coisa compilar.
 *
 * <p>Por isso esta classe de teste não referencia nenhum desses tipos —
 * fazer isso quebraria a compilação do módulo de teste pra todo mundo.
 *
 * <p><b>Plano de teste</b> (implementar assim que o domínio estabilizar):
 * <ol>
 *   <li>Mockar {@code AssessmentService} (e qualquer outro service que o
 *       controller passar a receber) com {@code @Mock}.</li>
 *   <li>Instanciar o controller injetando os mocks pelo construtor (hoje
 *       ele usa {@code new AssessmentService()} direto — para ser
 *       testável por mock, isso precisa virar injeção de dependência).</li>
 *   <li>Chamar {@code registerAssessment(...)} e {@code registerClass(...)}
 *       e usar {@code verify(mock).metodo(argumentos)} para confirmar a
 *       delegação correta.</li>
 *   <li>Cobrir o caminho de erro: o controller deve propagar
 *       {@code AcademicSystemException} corretamente.</li>
 * </ol>
 */
class AcademicSystemControllerTest {

    @Test
    @Disabled("Bloqueado: AcademicSystemController referencia model.AcademicSystem/Turma e service.AssessmentService, que não existem no repositório ainda")
    void delegatesMenuActionsToServices() {
        // Intencionalmente vazio — ver plano de teste no Javadoc da classe.
    }
}
