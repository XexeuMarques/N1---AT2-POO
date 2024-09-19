package controller;

import model.Avaliacao;
import model.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioController {
    private List<Funcionario> funcionarios;

    public FuncionarioController() {
        this.funcionarios = new ArrayList<>();
    }

    // Adicionar funcionário
    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    // Listar todos os funcionários
    public List<Funcionario> listarFuncionarios() {
        return funcionarios;
    }

    // Buscar funcionário por nome
    public Funcionario buscarFuncionario(String nome) {
        for (Funcionario f : funcionarios) {
            if (f.getNome().equalsIgnoreCase(nome)) {
                return f;
            }
        }
        return null;
    }

    // Adicionar avaliação a um funcionário
    public void adicionarAvaliacao(String nome, Avaliacao avaliacao) {
        Funcionario funcionario = buscarFuncionario(nome);
        if (funcionario != null) {
            funcionario.adicionarAvaliacao(avaliacao);
        } else {
            throw new IllegalArgumentException("Funcionário não encontrado.");
        }
    }
}
