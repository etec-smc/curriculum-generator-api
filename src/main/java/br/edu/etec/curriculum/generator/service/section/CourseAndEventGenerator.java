package br.edu.etec.curriculum.generator.service.section;

import br.edu.etec.curriculum.generator.dto.Course;
import br.edu.etec.curriculum.generator.dto.Curriculum;
import br.edu.etec.curriculum.generator.dto.LineType;
import br.edu.etec.curriculum.generator.service.line.LineBuilderFactory;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;

import java.util.Comparator;

import static br.edu.etec.curriculum.generator.util.Formatters.formatYearMonth;
import static java.util.Objects.nonNull;

@Service
public class CourseAndEventGenerator extends BaseSectionGenerator {
    public CourseAndEventGenerator(LineBuilderFactory factory) {
        super(factory);
    }

    @Override
    protected String sectionHeader() {
        return "Cursos e/ou Participações em Eventos";
    }

    @Override
    protected boolean mayGenerate(Curriculum curriculum) {
        return nonNull(curriculum.courses()) && !curriculum.courses().isEmpty();
    }

    @Override
    protected void generateImpl(XWPFDocument document, Curriculum curriculum) {
        curriculum.courses().stream()
                .sorted(Comparator.comparing(Course::from).reversed())
                .forEach(item -> {
                    writeCourseContent(factory.build(document, LineType.COMMON), item);
                    factory.build(document, LineType.COMMON).setText(item.institution());
                    writePeriodContent(factory.build(document, LineType.COMMON), item);

                    factory.buildEmpty(document, LineType.COMMON);
                });
    }

    @Override
    public int order() {
        return 3;
    }

    private void writeCourseContent(XWPFRun line, Course course) {
        line.setText(String.format("%s%s", course.type().getDescription(), course.course()).trim());
    }

    private void writePeriodContent(XWPFRun line, Course course) {
        var builder = new StringBuilder();

        var from = formatYearMonth(course.from());
        var to = nonNull(course.to()) ? formatYearMonth(course.to()) : " até hoje";

        builder.append("Período: ").append(from).append(" - ").append(to);

        if (nonNull(course.shift())) {
            builder.append(" (").append(course.shift()).append(") ");
        }

        line.setText(builder.toString());
        line.getCTR().addNewTab();

        builder = new StringBuilder();
        builder.append("Carga horária: ").append(course.amountHours()).append(" horas");

        line.setText(builder.toString());
    }
}
