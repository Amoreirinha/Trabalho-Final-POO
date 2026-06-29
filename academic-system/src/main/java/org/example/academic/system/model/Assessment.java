package org.example.academic.system.model;

// Assessment - A CLASSE MÃE
public abstract class Assessment {

    // ATRIBUTOS (características)
    private Double value;    // Ex: 8.5
    private Double weight;   // Ex: 0.4

    // CONSTRUTOR (como criar uma avaliação)
    public Assessment(Double value, Double weight) {
        this.value = value;
        this.weight = weight;
    }

    // MÉTODOS (comportamentos)
    public Double getValue() {
        return value;
    }

    public Double getWeight() {
        return weight;
    }

    // MÉTODO ABSTRATO (cada tipo vai implementar diferente)
    public abstract String getType();
}
