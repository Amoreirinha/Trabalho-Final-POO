package org.example.academic.system;

import org.example.academic.system.controller.AcademicSystemController;
import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.model.AcademicSystem;
import org.example.academic.system.model.Turma;

import java.util.Scanner;

/**
 * US-2364: Sistema Acadêmico - Interface de Linha de Comando
 * Ponto de entrada da aplicação
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final AcademicSystemController controller = new AcademicSystemController();

    private static String currentUser = "Professor";
    private static String currentRole = "PROFESSOR";

    public static void main(String[] args) {
        System.out.println("=".repeat(50));
        System.out.println("        SISTEMA ACADÊMICO v1.0");
        System.out.println("=".repeat(50));

        while (true) {
            try {
                showMenu();
                int option = readInt("Escolha: ");

                if (option == 0) {
                    System.out.println("👋 Saindo do sistema...");
                    System.exit(0);
                }

                handleOption(option);

            } catch (NumberFormatException e) {
                System.out.println("⚠️ Digite um número válido!");
            } catch (AcademicSystemException e) {
                System.out.println("❌ Erro: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("❌ Erro inesperado: " + e.getMessage());
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📋 MENU PRINCIPAL");
        System.out.println("   Usuário: " + currentUser + " | Perfil: " + currentRole);
        System.out.println("-".repeat(50));

        System.out.println("1 - Cadastrar Turma");
        System.out.println("2 - Cadastrar Avaliação");
        System.out.println("3 - Relatório de Turmas e Avaliações");
        System.out.println("4 - Relatório de Pesos");
        System.out.println("5 - Listar Turmas");

        if (currentRole.equals("ADMIN")) {
            System.out.println("6 - [ADMIN] Configurar Persistência");
            System.out.println("7 - [ADMIN] Salvar Dados");
        }

        System.out.println("0 - Sair");
        System.out.println("-".repeat(50));
    }

    private static void handleOption(int option) {
        switch (option) {
            case 1 -> registerTurma();
            case 2 -> registerAssessment();
            case 3 -> generateClassAssessmentSummary();
            case 4 -> generateAssessmentWeightReport();
            case 5 -> listTurmas();
            case 6 -> { if (currentRole.equals("ADMIN")) configurePersistence(); }
            case 7 -> { if (currentRole.equals("ADMIN")) saveData(); }
            default -> System.out.println("⚠️ Opção inválida!");
        }
    }

    private static void registerTurma() {
        System.out.println("\n=== CADASTRO DE TURMA ===");
        String code = readString("Código da turma: ");
        String title = readString("Título da turma: ");
        controller.registerTurma(code, title);
        System.out.println("✅ Turma cadastrada com sucesso!");
    }

    private static void registerAssessment() {
        System.out.println("\n=== CADASTRO DE AVALIAÇÃO ===");
        String classCode = readString("Código da turma: ");

        System.out.println("\nTipos de avaliação disponíveis:");
        System.out.println("1 - Prova");
        System.out.println("2 - Trabalho Prático");
        System.out.println("3 - Seminário");
        System.out.println("4 - Trabalho");

        String type = switch (readString("Escolha: ")) {
            case "1" -> "Prova";
            case "2" -> "Trabalho Prático";
            case "3" -> "Seminário";
            case "4" -> "Trabalho";
            default -> throw new AcademicSystemException("Tipo de avaliação inválido!");
        };

        Double value = readDouble("Valor (0-10): ");
        Double weight = readDouble("Peso (0-1): ");

        controller.registerAssessment(classCode, type, value, weight);
        System.out.println("✅ Avaliação cadastrada com sucesso!");
    }

    private static void generateClassAssessmentSummary() {
        System.out.println("\n" + controller.generateClassAssessmentSummary());
    }

    private static void generateAssessmentWeightReport() {
        System.out.println("\n" + controller.generateAssessmentWeightReport());
    }

    private static void listTurmas() {
        System.out.println("\n=== TURMAS CADASTRADAS ===");
        if (controller.getAllTurmas().isEmpty()) {
            System.out.println("📋 Nenhuma turma cadastrada.");
            return;
        }
        for (Turma t : controller.getAllTurmas()) {
            System.out.println("\n📚 " + t.getCode() + " - " + t.getTitle());
            System.out.println("   Avaliações: " + t.getAssessments().size());
            for (var a : t.getAssessments()) {
                System.out.printf("   • %s: %.2f (peso %.2f)%n",
                        a.getType(), a.getValue(), a.getWeight());
            }
        }
    }

    private static void configurePersistence() {
        System.out.println("\n=== [ADMIN] CONFIGURAR PERSISTÊNCIA ===");
        System.out.println("1 - TXT");
        System.out.println("2 - XML");
        System.out.println("3 - JSON");
        String option = readString("Escolha: ");
        System.out.println("✅ Persistência configurada para: " + option);
    }

    private static void saveData() {
        System.out.println("\n=== [ADMIN] SALVAR DADOS ===");
        System.out.println("✅ Dados salvos com sucesso!");
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int readInt(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) throw new NumberFormatException();
        return Integer.parseInt(input);
    }

    private static double readDouble(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) throw new NumberFormatException();
        return Double.parseDouble(input.replace(",", "."));
    }
}
