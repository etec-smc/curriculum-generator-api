package br.edu.etec.curriculum.generator.service.section;

import br.edu.etec.curriculum.generator.dto.Curriculum;
import br.edu.etec.curriculum.generator.dto.LineType;
import br.edu.etec.curriculum.generator.service.line.LineBuilderFactory;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeaderGenerator implements SectionGenerator{

    private final LineBuilderFactory factory;

    public HeaderGenerator(LineBuilderFactory factory) {
        this.factory = factory;
    }

    @Override
    public int order() {
        return 0;
    }

    @Override
    public void generate(XWPFDocument document, Curriculum curriculum) {
        writePersonName(document, curriculum);
        writePersonDetails(document, curriculum);
    }

    private void writePersonName(XWPFDocument document, Curriculum curriculum){
        var run = factory.build(document, LineType.PERSON_TITLE);
        run.setText(curriculum.personalData().name().toUpperCase());
    }

    private void writePersonDetails(XWPFDocument document, Curriculum curriculum){
        List.of(
                curriculum.personalData().getBasicInfo(),
                curriculum.personalData().getAddressFormatted(),
                curriculum.personalData().getPhoneFormatted(),
                curriculum.personalData().email()
        ).forEach(part -> factory.build(document, LineType.PERSON_DETAIL).setText(part));

        factory.build(document, LineType.PERSON_DETAIL).setText(" ");
    }
}
