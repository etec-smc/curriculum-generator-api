package br.edu.etec.curriculum.generator.dto;

public enum LanguageLevel {
    BASIC("Básico"),
    INTERMEDIARY("Intermediário"),
    ADVANCED("Avançado");

    private String description;

    LanguageLevel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
