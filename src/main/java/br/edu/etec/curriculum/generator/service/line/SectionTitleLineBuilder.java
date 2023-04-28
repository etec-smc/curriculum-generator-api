package br.edu.etec.curriculum.generator.service.line;

import br.edu.etec.curriculum.generator.dto.LineType;
import org.apache.poi.xwpf.usermodel.LineSpacingRule;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;

@Service
public class SectionTitleLineBuilder implements LineBuilder{
    @Override
    public boolean accept(LineType type) {
        return type.equals(LineType.SECTION_TITLE);
    }

    @Override
    public XWPFRun build(XWPFDocument document) {
        var paragraph = document.createParagraph();

        paragraph.setSpacingBetween(20, LineSpacingRule.AT_LEAST);
        paragraph.setAlignment(ParagraphAlignment.BOTH);
        paragraph.setVerticalAlignment(TextAlignment.CENTER);

        var run = paragraph.createRun();
        run.setBold(true);
        run.setFontSize(14);
        run.setFontFamily("Arial");

        return run;
    }
}
