package br.edu.etec.curriculum.generator.service;

import br.edu.etec.curriculum.generator.dto.File;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public interface PDFProducer {
    File produce(XWPFDocument document);
}
