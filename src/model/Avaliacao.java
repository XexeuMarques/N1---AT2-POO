package model;

import java.time.LocalDate;

public class Avaliacao {
    private double nota;
    private LocalDate data;

    // Construtor com data
    public Avaliacao(double nota, LocalDate data) {
        if (nota >= 0 && nota <= 10) {
            this.nota = nota;
            this.data = data;
        } else {
            throw new IllegalArgumentException("Nota deve ser entre 0 e 10.");
        }
    }

    // Construtor sem data (usa data atual)
    public Avaliacao(double nota) {
        this(nota, LocalDate.now());
    }

    public double getNota() {
        return nota;
    }

    public String getNivelAvaliacao() {
        if (nota >= 9) {
            return "Excelente";
        } else if (nota >= 7) {
            return "Bom";
        } else if (nota >= 5) {
            return "Regular";
        } else {
            return "Insatisfatório";
        }
    }

    @Override
    public String toString() {
        return "Nota: " + nota + ", Data: " + data + ", Nível: " + getNivelAvaliacao();
    }
}
