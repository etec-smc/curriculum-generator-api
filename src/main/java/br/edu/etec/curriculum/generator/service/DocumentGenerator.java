package br.edu.etec.curriculum.generator.service;

import br.edu.etec.curriculum.generator.dto.Curriculum;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public interface DocumentGenerator {
    XWPFDocument generate(Curriculum curriculum);
}
