package org.example.academic.system.service;

<<<<<<< HEAD
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.AcademicSystem;
import org.example.academic.system.model.Assessment;
import org.example.academic.system.repository.PersistenceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Serviço responsável pela geração de relatórios (TUS-2399).
 */
public class ReportService {

    private static final Logger logger = LoggerFactory.getLogger(ReportService.class);
    private final AcademicSystem academicSystem;
    private final PersistenceService persistenceService;

    public ReportService(AcademicSystem academicSystem, PersistenceService persistenceService) {
        this.academicSystem = academicSystem;
        this.persistenceService = persistenceService;
    }

    /**
     * Relatório de avaliações por turma (US-2375).
     */
    public String generateClassSummaryReport(String userRole) {
        logger.info("Gerando relatório de avaliações por turma. Papel: {}", userRole);
        List<AcademicClass> classes = academicSystem.getClasses();
        StringBuilder sb = new StringBuilder();
        sb.append("=== RELATÓRIO DE AVALIAÇÕES POR TURMA ===\n\n");
        if (classes.isEmpty()) {
            sb.append("Nenhuma turma registrada.\n");
        } else {
            for (AcademicClass c : classes) {
                sb.append("Turma: ").append(c.getCode()).append(" - ").append(c.getTitle()).append("\n");
                if (c.getAssessments().isEmpty()) {
                    sb.append("  Sem avaliações.\n");
                } else {
                    for (Assessment a : c.getAssessments()) {
                        sb.append(String.format("  [%s] Valor: %.1f | Peso: %.2f%n",
                            a.getType(), a.getValue(), a.getWeight()));
                    }
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Relatório de peso das avaliações (US-2376).
     */
    public String generateWeightReport(String userRole) {
        logger.info("Gerando relatório de pesos. Papel: {}", userRole);
        List<AcademicClass> classes = academicSystem.getClasses();
        StringBuilder sb = new StringBuilder();
        sb.append("=== RELATÓRIO DE PESOS DAS AVALIAÇÕES ===\n\n");
        if (classes.isEmpty()) {
            sb.append("Nenhuma turma registrada.\n");
        } else {
            for (AcademicClass c : classes) {
                double totalWeight = c.getAssessments().stream()
                    .mapToDouble(Assessment::getWeight).sum();
                String valid = Math.abs(totalWeight - 1.0) < 0.001 ? "VÁLIDA" : "INVÁLIDA";
                sb.append(String.format("Turma: %s - %s%n  Peso total: %.2f — Composição: %s%n%n",
                    c.getCode(), c.getTitle(), totalWeight, valid));
            }
        }
        return sb.toString();
    }

    /**
     * Relatório de configuração de persistência (US-2377).
     */
    public String generatePersistenceReport(String userRole) {
        logger.info("Gerando relatório de persistência. Papel: {}", userRole);
        PersistenceType type = persistenceService.getCurrentType();
        return "=== RELATÓRIO DE CONFIGURAÇÃO DE PERSISTÊNCIA ===\n\n" +
               "Tipo de persistência atual: " + type.name() + "\n";
=======
import org.example.academic.system.model.AcademicSystem;
import org.example.academic.system.model.Assessment;
import org.example.academic.system.model.Turma;  // ← MUDOU import

/**
 * US-2375: Relatório de Resumo de Avaliações por Turma
 * US-2376: Relatório de Pesos das Avaliações
 * TUS-2399: Introduzido para mover lógica de relatórios do controller
 * TUS-2404: Testes automatizados
 */
public class ReportService {

    private final AcademicSystem academicSystem;

    public ReportService() {
        this.academicSystem = AcademicSystem.getInstance();
    }

    // ============================================================
    // US-2375: GERAR RELATÓRIO DE RESUMO DE AVALIAÇÕES POR TURMA
    // ============================================================

    /**
     * AC1: Lista todas as turmas registradas
     * AC2: Mostra código, título, tipo, valor e peso de cada avaliação
     * AC3: Turmas sem avaliações ainda aparecem
     * AC4: Nenhuma turma → relatório gerado sem erros
     * AC7: Não modifica os dados existentes
     */
    public String generateClassAssessmentSummary() {
        StringBuilder report = new StringBuilder();
        report.append("=".repeat(60)).append("\n");
        report.append("        RELATÓRIO DE TURMAS E AVALIAÇÕES\n");
        report.append("=".repeat(60)).append("\n\n");

        // AC4: Verifica se há turmas
        if (academicSystem.getAllTurmas().isEmpty()) {  // ← MUDOU
            report.append("📋 Nenhuma turma cadastrada no sistema.\n");
            return report.toString();
        }

        // AC1: Lista todas as turmas
        int totalTurmas = 0;
        int totalAvaliacoes = 0;

        for (Turma t : academicSystem.getAllTurmas()) {  // ← MUDOU
            totalTurmas++;
            report.append("📚 TURMA: ").append(t.getCode())
                    .append(" - ").append(t.getTitle()).append("\n");
            report.append("-".repeat(50)).append("\n");

            // AC2: Mostra avaliações
            if (t.getAssessments().isEmpty()) {
                // AC3: Turma sem avaliações
                report.append("   ⚠️ Nenhuma avaliação cadastrada\n");
            } else {
                report.append("   AVALIAÇÕES:\n");
                for (Assessment a : t.getAssessments()) {
                    totalAvaliacoes++;
                    report.append(String.format("   • %-18s | Valor: %5.2f | Peso: %4.2f\n",
                            a.getType(), a.getValue(), a.getWeight()));
                }
            }
            report.append("\n");
        }

        // Resumo final
        report.append("=".repeat(60)).append("\n");
        report.append("📊 RESUMO:\n");
        report.append("   Total de turmas: ").append(totalTurmas).append("\n");
        report.append("   Total de avaliações: ").append(totalAvaliacoes).append("\n");
        report.append("=".repeat(60)).append("\n");

        // AC7: Não modifica os dados
        return report.toString();
    }

    // ============================================================
    // US-2376: GERAR RELATÓRIO DE PESOS DAS AVALIAÇÕES
    // ============================================================

    /**
     * AC1: Lista todas as turmas
     * AC2: Calcula e exibe o peso total por turma
     * AC3: Peso total = 1.0 → composição válida
     * AC4: Peso total ≠ 1.0 → composição inválida
     * AC5: Turma sem avaliações → peso total = 0.0
     * AC8: Não modifica os dados existentes
     */
    public String generateAssessmentWeightReport() {
        StringBuilder report = new StringBuilder();
        report.append("=".repeat(60)).append("\n");
        report.append("         RELATÓRIO DE PESOS DAS AVALIAÇÕES\n");
        report.append("=".repeat(60)).append("\n\n");

        // AC1: Verifica se há turmas
        if (academicSystem.getAllTurmas().isEmpty()) {  // ← MUDOU
            report.append("📋 Nenhuma turma cadastrada no sistema.\n");
            return report.toString();
        }

        int turmasValidas = 0;
        int turmasInvalidas = 0;

        // AC1: Lista todas as turmas
        for (Turma t : academicSystem.getAllTurmas()) {  // ← MUDOU
            report.append("📚 ").append(t.getCode()).append(" - ").append(t.getTitle()).append("\n");

            // AC2: Calcula o peso total
            double totalWeight = 0.0;
            for (Assessment a : t.getAssessments()) {
                totalWeight += a.getWeight();
            }

            // AC5: Turma sem avaliações
            if (t.getAssessments().isEmpty()) {
                report.append("   ⚠️ Nenhuma avaliação cadastrada (peso total: 0.0)\n");
                report.append("   📌 Status: SEM AVALIAÇÕES\n\n");
                continue;
            }

            // AC3: Peso total = 1.0 → válida
            // AC4: Peso total ≠ 1.0 → inválida
            boolean isValid = Math.abs(totalWeight - 1.0) < 0.0001;

            if (isValid) {
                turmasValidas++;
                report.append(String.format("   ✅ Peso total: %.2f - COMPOSIÇÃO VÁLIDA!\n", totalWeight));
            } else {
                turmasInvalidas++;
                report.append(String.format("   ❌ Peso total: %.2f - COMPOSIÇÃO INVÁLIDA!\n", totalWeight));
                report.append("   💡 A soma dos pesos deve ser igual a 1.0\n");
            }

            // Mostra detalhes dos pesos
            for (Assessment a : t.getAssessments()) {
                report.append(String.format("      • %-18s | Peso: %4.2f\n",
                        a.getType(), a.getWeight()));
            }
            report.append("\n");
        }

        // Resumo final
        report.append("=".repeat(60)).append("\n");
        report.append("📊 RESUMO:\n");
        report.append("   ✅ Turmas com composição válida: ").append(turmasValidas).append("\n");
        report.append("   ❌ Turmas com composição inválida: ").append(turmasInvalidas).append("\n");
        report.append("=".repeat(60)).append("\n");

        // AC8: Não modifica os dados
        return report.toString();
>>>>>>> 9643520f26d8d62b799ddda3ea5a0d2daf1a84f6
    }
}
