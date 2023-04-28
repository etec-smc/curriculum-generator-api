package br.edu.etec.curriculum.generator.service;

import br.edu.etec.curriculum.generator.dto.File;
import br.edu.etec.curriculum.generator.exception.FailCreatingPDFException;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import static br.edu.etec.curriculum.generator.dto.FileType.PDF;

@Service
public class PDFProducerImpl implements PDFProducer {
    @Override
    public File produce(XWPFDocument document) {
        try (var output = new ByteArrayOutputStream()) {
            writeToPDF(document, output);

            return new File(
                    PDF,
                    Base64.getEncoder().encodeToString(output.toByteArray())
            );
        } catch (Exception e) {
            throw new FailCreatingPDFException(e);
        }
    }

    private void writeToPDF(XWPFDocument document, ByteArrayOutputStream output) throws IOException {
        ZipSecureFile.setMinInflateRatio(0);

        try (var out = new ByteArrayOutputStream()){
            document.write(out);

            try(var doc = new XWPFDocument(new ByteArrayInputStream(out.toByteArray()))) {
                var options = PdfOptions.create();

                PdfConverter.getInstance().convert(
                        doc,
                        output,
                        options
                );
            }
        }
    }
}
