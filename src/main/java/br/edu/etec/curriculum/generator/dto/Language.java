package br.edu.etec.curriculum.generator.dto;

import jakarta.validation.constraints.NotNull;

public record Language(
        @NotNull String language,
        @NotNull LanguageLevel level,
        LanguageLevel writeReadLevel,
        LanguageLevel speakListenLevel
) {
}
