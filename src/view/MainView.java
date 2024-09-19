package view;

import controller.FuncionarioController;
import model.Avaliacao;
import model.Funcionario;

import java.time.LocalDate;
import java.util.Scanner;

public class MainView {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FuncionarioController funcionarioController = new FuncionarioController();
        int opcao;

        do {
            System.out.println("==== Sistema de Avaliação de Funcionários ====");
            System.out.println("1. Cadastrar Funcionário");
            System.out.println("2. Listar Funcionários");
            System.out.println("3. Adicionar Avaliação");
            System.out.println("4. Exibir Avaliações de um Funcionário");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Cargo: ");
                    String cargo = scanner.nextLine();
                    System.out.print("Salário: ");
                    double salario = scanner.nextDouble();
                    Funcionario funcionario = new Funcionario(nome, cargo, salario);
                    funcionarioController.adicionarFuncionario(funcionario);
                    System.out.println("Funcionário cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.println("=== Lista de Funcionários ===");
                    for (Funcionario f : funcionarioController.listarFuncionarios()) {
                        System.out.println(f);
                    }
                    break;

                case 3:
                    System.out.print("Nome do funcionário: ");
                    String nomeAvaliacao = scanner.nextLine();
                    System.out.print("Nota (0 a 10): ");
                    double nota = scanner.nextDouble();
                    while (nota < 0 || nota > 10) {
                        System.out.println("Nota inválida! A nota deve estar entre 0 e 10.");
                        System.out.print("Nota (0 a 10): ");
                        nota = scanner.nextDouble();
                    }
                    Avaliacao avaliacao = new Avaliacao(nota, LocalDate.now());
                    try {
                        funcionarioController.adicionarAvaliacao(nomeAvaliacao, avaliacao);
                        System.out.println("Avaliação adicionada com sucesso!");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Nome do funcionário: ");
                    String nomeBusca = scanner.nextLine();
                    Funcionario f = funcionarioController.buscarFuncionario(nomeBusca);
                    if (f != null) {
                        System.out.println("Avaliações de " + f.getNome() + ":");
                        for (Avaliacao a : f.getAvaliacoes()) {
                            System.out.println(a);
                        }
                    } else {
                        System.out.println("Funcionário não encontrado.");
                    }
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
