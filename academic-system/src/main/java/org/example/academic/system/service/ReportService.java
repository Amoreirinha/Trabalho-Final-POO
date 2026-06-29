package org.example.academic.system.service;

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
    }
}
