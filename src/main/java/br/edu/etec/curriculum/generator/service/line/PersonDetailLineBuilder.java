package br.edu.etec.curriculum.generator.service.line;

import br.edu.etec.curriculum.generator.dto.LineType;
import org.apache.poi.xwpf.usermodel.LineSpacingRule;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;

@Service
public class PersonDetailLineBuilder implements LineBuilder{
    @Override
    public boolean accept(LineType type) {
        return type.equals(LineType.PERSON_DETAIL);
    }

    @Override
    public XWPFRun build(XWPFDocument document) {
        var paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        paragraph.setSpacingBetween(14, LineSpacingRule.AT_LEAST);

        var run = paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Arial");

        return run;
    }
}
