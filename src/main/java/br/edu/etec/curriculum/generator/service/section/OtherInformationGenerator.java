package br.edu.etec.curriculum.generator.service.section;

import br.edu.etec.curriculum.generator.dto.Curriculum;
import br.edu.etec.curriculum.generator.dto.Language;
import br.edu.etec.curriculum.generator.dto.VolunteerWork;
import br.edu.etec.curriculum.generator.service.line.LineBuilderFactory;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;

import static br.edu.etec.curriculum.generator.dto.LineType.COMMON;
import static br.edu.etec.curriculum.generator.util.Formatters.formatYearMonth;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class OtherInformationGenerator extends BaseSectionGenerator {
    public OtherInformationGenerator(LineBuilderFactory factory) {
        super(factory);
    }

    @Override
    protected String sectionHeader() {
        return "Outras Informações";
    }

    @Override
    protected boolean mayGenerate(Curriculum curriculum) {
        return (nonNull(curriculum.volunteerWorks()) && !curriculum.volunteerWorks().isEmpty()) ||
                (nonNull(curriculum.languages()) && !curriculum.languages().isEmpty()) ||
                (nonNull(curriculum.commonKnowledge()) && !curriculum.commonKnowledge().isEmpty());
    }

    @Override
    protected void generateImpl(XWPFDocument document, Curriculum curriculum) {
        if (nonNull(curriculum.commonKnowledge())){
            curriculum.commonKnowledge()
                    .forEach(item -> writeCommonKnowledge(factory.build(document, COMMON), item));
        }

        if (nonNull(curriculum.languages())){
            curriculum.languages()
                    .forEach(item -> writeLanguage(factory.build(document, COMMON), item));
        }

        if (nonNull(curriculum.volunteerWorks())){
            curriculum.volunteerWorks()
                    .forEach(item -> writeVolunteerWork(factory.build(document, COMMON), item));
        }
    }

    @Override
    public int order() {
        return 4;
    }

    private void writeCommonKnowledge(XWPFRun line, String knowledge){
        line.setText(String.format("Conhecimento em %s", knowledge));
    }

    private void writeLanguage(XWPFRun line, Language language){
        var builder = new StringBuilder();

        builder.append("Idioma: ")
                .append(language.language())
                .append(" (")
                .append(language.level().getDescription())
                .append(")");

        line.setText(builder.toString());
    }

    private void writeVolunteerWork(XWPFRun line, VolunteerWork volunteerWork) {
        var builder = new StringBuilder();

        builder.append(isNull(volunteerWork.end()) ? "Exerço" : "Exerci")
                .append(" trabalho voluntário de ").append(volunteerWork.description())
                .append(" na ").append(volunteerWork.institution())
                .append(isNull(volunteerWork.end()) ? " desde " : " no período de ")
                .append(getPeriodContent(volunteerWork));

        line.setText(builder.toString());
    }

    private String getPeriodContent(VolunteerWork volunteerWork){
        var builder = new StringBuilder();

        builder.append(formatYearMonth(volunteerWork.start()));

        if (nonNull(volunteerWork.end())){
            builder.append(" à ").append(formatYearMonth(volunteerWork.end()));
        }

        return builder.toString();
    }
}
