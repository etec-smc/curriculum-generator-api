package br.edu.etec.curriculum.generator.service;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

public interface PageConfigurationService {
    void configure(XWPFDocument document);
}
