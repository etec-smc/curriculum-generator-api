package br.edu.etec.curriculum.generator.service.section;

import br.edu.etec.curriculum.generator.dto.Curriculum;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public interface SectionGenerator {
    int order();
    void generate(XWPFDocument document, Curriculum curriculum);
}
