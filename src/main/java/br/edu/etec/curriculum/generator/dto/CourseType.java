package br.edu.etec.curriculum.generator.dto;

public enum CourseType {
    COURSE("Curso: "),
    LECTURE("Palestra: ");

    private String description;

    CourseType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
