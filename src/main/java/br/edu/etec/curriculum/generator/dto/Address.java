package br.edu.etec.curriculum.generator.dto;

import jakarta.validation.constraints.NotNull;

import static java.util.Objects.nonNull;

public record Address(
        @NotNull String street,
        @NotNull String number,
        String complement,
        @NotNull String neighborhood,
        @NotNull String city,
        @NotNull String state
) {
    public String formatted(){
        var builder = new StringBuilder();

        builder.append(street).append(", ").append(number);

        if (nonNull(complement)){
            builder.append(", ").append(complement);
        }

        builder.append(" - ").append(neighborhood);

        builder.append(". ").append(city).append(" - ").append(state);

        return builder.toString();
    }
}
