package br.edu.etec.curriculum.generator.service.section;

import br.edu.etec.curriculum.generator.dto.AcademicEducation;
import br.edu.etec.curriculum.generator.dto.Curriculum;
import br.edu.etec.curriculum.generator.dto.LineType;
import br.edu.etec.curriculum.generator.service.line.LineBuilderFactory;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;

import java.util.Comparator;

import static br.edu.etec.curriculum.generator.util.Formatters.formatYearMonth;
import static java.lang.Math.min;
import static java.util.Objects.nonNull;

@Service
public class AcademicEducationGenerator extends BaseSectionGenerator {
    public AcademicEducationGenerator(LineBuilderFactory factory) {
        super(factory);
    }

    @Override
    protected String sectionHeader() {
        return "Formação Acadêmica";
    }

    @Override
    protected boolean mayGenerate(Curriculum curriculum) {
        return nonNull(curriculum.academicEducations()) && !curriculum.academicEducations().isEmpty();
    }

    @Override
    protected void generateImpl(XWPFDocument document, Curriculum curriculum) {
        var items = curriculum.academicEducations().stream()
                .sorted(Comparator.comparing(a -> a.degree().getOrder()))
                .toList();

        items.subList(0, min(3, items.size())).forEach(item -> {
            writeCourseContent(factory.build(document, LineType.COMMON), item);
            factory.build(document, LineType.COMMON).setText(item.institution());
            writePeriodContent(factory.build(document, LineType.COMMON), item);
            factory.buildEmpty(document, LineType.COMMON);
        });
    }

    @Override
    public int order() {
        return 1;
    }

    private void writeCourseContent(XWPFRun line, AcademicEducation education){
        line.setText(String.format("%s%s", education.degree().getText(), education.course()).trim());
    }

    private void writePeriodContent(XWPFRun line, AcademicEducation education){
        var builder = new StringBuilder();

        var from = formatYearMonth(education.from());
        var to = nonNull(education.to()) ? formatYearMonth(education.to()) : " até hoje";

        builder.append("Período: ").append(from).append(" - ").append(to);

        if (nonNull(education.shift())){
            builder.append(" (").append(education.shift()).append(") ");
        }

        line.setText(builder.toString());
        line.getCTR().addNewTab();

        builder = new StringBuilder();
        builder.append("Carga horária: ").append(education.amountHours()).append(" horas");

        line.setText(builder.toString());
    }
}
