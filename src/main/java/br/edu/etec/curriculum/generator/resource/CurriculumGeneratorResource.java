package br.edu.etec.curriculum.generator.resource;

import br.edu.etec.curriculum.generator.dto.Curriculum;
import br.edu.etec.curriculum.generator.dto.File;
import br.edu.etec.curriculum.generator.service.CurriculumGenerator;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/curriculum/generator", produces = MediaType.APPLICATION_JSON_VALUE)
public class CurriculumGeneratorResource {

    private final CurriculumGenerator curriculumGenerator;

    public CurriculumGeneratorResource(CurriculumGenerator curriculumGenerator) {
        this.curriculumGenerator = curriculumGenerator;
    }

    @PostMapping
    public @ResponseBody File generate(@RequestBody Curriculum curriculum){
        return curriculumGenerator.generate(curriculum);
    }
}
