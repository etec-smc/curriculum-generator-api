package br.edu.etec.curriculum.generator.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.YearMonth;

public record Course(
        @NotNull
        String institution,

        @NotNull
        String course,

        @NotNull
        CourseType type,

        String shift,

        @Schema(pattern = "yyyy-MM", example = "2008-06", format = "yyyy-MM", type = "string")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM")
        @NotNull
        YearMonth from,

        @Schema(pattern = "yyyy-MM", example = "2008-06", format = "yyyy-MM", type = "string")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM")
        YearMonth to,

        @NotNull
        Integer amountHours
) {
}
