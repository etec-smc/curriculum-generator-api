package br.edu.etec.curriculum.generator.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record Curriculum(
        @NotNull @Valid PersonalData personalData,
        @Valid List<AcademicEducation> academicEducations,
        @Valid List<Course> courses,
        @Valid List<Language> languages,
        @Valid List<String> commonKnowledge,
        @Valid List<VolunteerWork> volunteerWorks,
        @Valid List<ProfessionalExperience> professionalExperiences
) {
}
