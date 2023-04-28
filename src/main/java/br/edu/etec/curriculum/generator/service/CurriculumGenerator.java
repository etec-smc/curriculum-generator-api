package br.edu.etec.curriculum.generator.service;

import br.edu.etec.curriculum.generator.dto.Curriculum;
import br.edu.etec.curriculum.generator.dto.File;

public interface CurriculumGenerator {
    File generate(Curriculum curriculum);
}
