package model;

import java.util.ArrayList;
import java.util.List;

public class Funcionario {
    private String nome;
    private String cargo;
    private double salario;
    private List<Avaliacao> avaliacoes;

    // Construtor com todos os atributos
    public Funcionario(String nome, String cargo, double salario) {
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
        this.avaliacoes = new ArrayList<>();
    }

    // Construtor sem salário
    public Funcionario(String nome, String cargo) {
        this(nome, cargo, 0.0);
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void adicionarAvaliacao(Avaliacao avaliacao) {
        this.avaliacoes.add(avaliacao);
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Cargo: " + cargo + ", Salário: R$ " + salario;
    }
}
