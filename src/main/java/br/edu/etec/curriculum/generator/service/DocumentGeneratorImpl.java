package br.edu.etec.curriculum.generator.service;

import br.edu.etec.curriculum.generator.dto.Curriculum;
import br.edu.etec.curriculum.generator.service.section.SectionGenerator;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Service
public class DocumentGeneratorImpl implements DocumentGenerator{

    private final PageConfigurationService configurationService;
    private final List<SectionGenerator> sectionGenerators;

    public DocumentGeneratorImpl(PageConfigurationService configurationService, List<SectionGenerator> sectionGenerators) {
        this.configurationService = configurationService;
        this.sectionGenerators = sectionGenerators;
    }

    @Override
    public XWPFDocument generate(Curriculum curriculum) {
        var document = new XWPFDocument();
        document.createStyles();
        document.createHeader(HeaderFooterType.DEFAULT);
        document.createFooter(HeaderFooterType.DEFAULT);

        configurationService.configure(document);

        generatorsStream()
                .forEach(gen -> gen.generate(document, curriculum));

        return document;
    }

    private Stream<SectionGenerator> generatorsStream(){
        return sectionGenerators.stream().sorted(Comparator.comparing(SectionGenerator::order));
    }
}
