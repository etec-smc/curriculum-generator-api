package br.edu.etec.curriculum.generator.service.section;

import br.edu.etec.curriculum.generator.dto.Curriculum;
import br.edu.etec.curriculum.generator.dto.LineType;
import br.edu.etec.curriculum.generator.service.line.LineBuilderFactory;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public abstract class BaseSectionGenerator implements SectionGenerator {

    protected final LineBuilderFactory factory;

    BaseSectionGenerator(LineBuilderFactory factory) {
        this.factory = factory;
    }

    protected abstract boolean mayGenerate(Curriculum curriculum);
    protected abstract String sectionHeader();
    protected abstract void generateImpl(XWPFDocument document, Curriculum curriculum);

    @Override
    public void generate(XWPFDocument document, Curriculum curriculum) {
        if (!mayGenerate(curriculum)){
            return;
        }

        factory.build(document, LineType.SECTION_TITLE).setText(sectionHeader().toUpperCase());
        factory.buildEmpty(document, LineType.COMMON);

        generateImpl(document, curriculum);
        factory.buildEmpty(document, LineType.COMMON);
    }
}
