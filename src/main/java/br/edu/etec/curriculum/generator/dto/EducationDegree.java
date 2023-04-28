package br.edu.etec.curriculum.generator.dto;

public enum EducationDegree {
    BASIC_DEGREE(5, ""),
    TECHNICAL_DEGREE(4, "Curso Técnico em "),
    COLLEGE_DEGREE(3, "Graduao em "),
    POST_GRADUATION_DEGREE(2, "Pós graduado em "),
    MASTER_DEGREE(1, "Mestre em "),
    PHD_DEGREE(0, "Doutor em ");

    private final int order;
    private final String text;

    EducationDegree(int order, String text) {
        this.order = order;
        this.text = text;
    }

    public int getOrder() {
        return order;
    }

    public String getText() {
        return text;
    }
}
