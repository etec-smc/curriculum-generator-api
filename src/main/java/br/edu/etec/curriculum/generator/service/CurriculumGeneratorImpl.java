package br.edu.etec.curriculum.generator.service;

import br.edu.etec.curriculum.generator.dto.Curriculum;
import br.edu.etec.curriculum.generator.dto.File;
import br.edu.etec.curriculum.generator.exception.FailCreatingPDFException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CurriculumGeneratorImpl implements CurriculumGenerator {

    private final DocumentGenerator documentGenerator;
    private final PDFProducer pdfProducer;

    public CurriculumGeneratorImpl(DocumentGenerator documentGenerator, PDFProducer pdfProducer) {
        this.documentGenerator = documentGenerator;
        this.pdfProducer = pdfProducer;
    }

    @Override
    public File generate(Curriculum curriculum) {
        try (var document = documentGenerator.generate(curriculum)){
            return pdfProducer.produce(document);
        } catch (IOException e) {
            throw new FailCreatingPDFException(e);
        }
    }
}
