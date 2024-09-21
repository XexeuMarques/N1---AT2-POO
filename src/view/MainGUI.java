package view;

import controller.FuncionarioController;
import model.Funcionario;
import model.Avaliacao;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class MainGUI {
    private JFrame frame;
    private JPanel mainPanel;
    private FuncionarioController funcionarioController;

    public MainGUI() {
        funcionarioController = new FuncionarioController();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Sistema de Avaliação de Funcionários");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1));

        JButton btnCadastrarFuncionario = new JButton("Cadastrar Funcionário");
        JButton btnListarFuncionarios = new JButton("Listar Funcionários");
        JButton btnAdicionarAvaliacao = new JButton("Adicionar Avaliação");
        JButton btnExibirAvaliacoes = new JButton("Exibir Avaliações");

        mainPanel.add(btnCadastrarFuncionario);
        mainPanel.add(btnListarFuncionarios);
        mainPanel.add(btnAdicionarAvaliacao);
        mainPanel.add(btnExibirAvaliacoes);

        frame.getContentPane().add(mainPanel);

        // Ações dos botões
        btnCadastrarFuncionario.addActionListener(e -> showCadastrarFuncionarioPanel());
        btnListarFuncionarios.addActionListener(e -> showListarFuncionariosPanel());
        btnAdicionarAvaliacao.addActionListener(e -> showAdicionarAvaliacaoPanel());
        btnExibirAvaliacoes.addActionListener(e -> showExibirAvaliacoesPanel());

        frame.setVisible(true);
    }

    // Painel para cadastrar funcionários
    private void showCadastrarFuncionarioPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2));

        JTextField nomeField = new JTextField();
        JTextField cargoField = new JTextField();
        JTextField salarioField = new JTextField();

        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);
        panel.add(new JLabel("Cargo:"));
        panel.add(cargoField);
        panel.add(new JLabel("Salário:"));
        panel.add(salarioField);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Cadastrar Funcionário", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String nome = nomeField.getText().trim();
            String cargo = cargoField.getText().trim();
            String salarioStr = salarioField.getText().trim();

            // Validação de campos vazios
            if (nome.isEmpty() || cargo.isEmpty() || salarioStr.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validação de salário numérico
            try {
                double salario = Double.parseDouble(salarioStr);
                Funcionario funcionario = new Funcionario(nome, cargo, salario);
                funcionarioController.adicionarFuncionario(funcionario);
                JOptionPane.showMessageDialog(frame, "Funcionário cadastrado com sucesso!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Salário deve ser um valor numérico!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    // Painel para listar funcionários
    private void showListarFuncionariosPanel() {
        List<Funcionario> funcionarios = funcionarioController.listarFuncionarios();
        StringBuilder sb = new StringBuilder();
        for (Funcionario f : funcionarios) {
            sb.append(f).append("\n");
        }

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JOptionPane.showMessageDialog(frame, scrollPane, "Lista de Funcionários", JOptionPane.INFORMATION_MESSAGE);
    }

    // Painel para adicionar avaliação
    private void showAdicionarAvaliacaoPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));

        List<Funcionario> funcionarios = funcionarioController.listarFuncionarios();
        if (funcionarios.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Nenhum funcionário cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] nomes = funcionarios.stream().map(Funcionario::getNome).toArray(String[]::new);

        JComboBox<String> comboBoxFuncionarios = new JComboBox<>(nomes);
        JTextField notaField = new JTextField();

        panel.add(new JLabel("Funcionário:"));
        panel.add(comboBoxFuncionarios);
        panel.add(new JLabel("Nota (0-10):"));
        panel.add(notaField);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Adicionar Avaliação", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String nomeSelecionado = (String) comboBoxFuncionarios.getSelectedItem();
            String notaStr = notaField.getText().trim();

            // Validação de campo vazio
            if (notaStr.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "A nota deve ser preenchida!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validação de nota numérica e entre 0 e 10
            try {
                double nota = Double.parseDouble(notaStr);
                if (nota < 0 || nota > 10) {
                    JOptionPane.showMessageDialog(frame, "A nota deve estar entre 0 e 10!", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    Avaliacao avaliacao = new Avaliacao(nota, LocalDate.now());
                    funcionarioController.adicionarAvaliacao(nomeSelecionado, avaliacao);
                    JOptionPane.showMessageDialog(frame, "Avaliação adicionada com sucesso!");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "A nota deve ser um valor numérico!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    // Painel para exibir avaliações
    private void showExibirAvaliacoesPanel() {
        List<Funcionario> funcionarios = funcionarioController.listarFuncionarios();
        StringBuilder sb = new StringBuilder();

        for (Funcionario f : funcionarios) {
            sb.append("Funcionário: ").append(f.getNome()).append("\n");
            List<Avaliacao> avaliacoes = f.getAvaliacoes();
            if (avaliacoes.isEmpty()) {
                sb.append("  Sem avaliações.\n");
            } else {
                for (Avaliacao a : avaliacoes) {
                    sb.append("  ").append(a).append("\n");
                }
            }
            sb.append("\n");
        }

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JOptionPane.showMessageDialog(frame, scrollPane, "Avaliações dos Funcionários", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        // Executar a GUI
        SwingUtilities.invokeLater(MainGUI::new);
    }
}
