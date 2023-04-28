package br.edu.etec.curriculum.generator.service.section;

import br.edu.etec.curriculum.generator.dto.Curriculum;
import br.edu.etec.curriculum.generator.dto.ProfessionalExperience;
import br.edu.etec.curriculum.generator.service.line.LineBuilderFactory;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;

import java.util.Comparator;

import static br.edu.etec.curriculum.generator.dto.LineType.COMMON;
import static br.edu.etec.curriculum.generator.util.Formatters.formatYearMonth;
import static java.util.Objects.nonNull;

@Service
public class ProfessionalExperienceGenerator extends BaseSectionGenerator {
    public ProfessionalExperienceGenerator(LineBuilderFactory factory) {
        super(factory);
    }

    @Override
    protected String sectionHeader() {
        return "Experiência Profissional";
    }

    @Override
    protected boolean mayGenerate(Curriculum curriculum) {
        return nonNull(curriculum.professionalExperiences()) && !curriculum.professionalExperiences().isEmpty();
    }

    @Override
    protected void generateImpl(XWPFDocument document, Curriculum curriculum) {
        curriculum.professionalExperiences().stream()
                .sorted(Comparator.comparing(ProfessionalExperience::start).reversed())
                .forEach(item -> {
                    writeCompanyName(factory.build(document, COMMON), item);
                    writeJob(factory.build(document, COMMON), item);
                    writePeriod(factory.build(document, COMMON), item);

                    if (nonNull(item.description()) && !item.description().isBlank()){
                        writeJobDescription(factory.build(document, COMMON), item);
                    }

                    factory.buildEmpty(document, COMMON);
                });
    }

    @Override
    public int order() {
        return 2;
    }

    private void writeCompanyName(XWPFRun line, ProfessionalExperience experience) {
        line.setText(experience.company());
    }

    private void writeJob(XWPFRun line, ProfessionalExperience experience) {
        var content = String.format("Função: %s", experience.job());
        line.setText(content);
    }

    private void writePeriod(XWPFRun line, ProfessionalExperience experience) {
        var builder = new StringBuilder();

        var from = formatYearMonth(experience.start());
        var to = nonNull(experience.end()) ? formatYearMonth(experience.end()) : " atual";

        builder.append("Período: ").append(from).append(" - ").append(to);

        line.setText(builder.toString());
    }

    private void writeJobDescription(XWPFRun line, ProfessionalExperience experience) {
        var content = String.format("Atividades Desempenhadas: %s", experience.description());
        line.setText(content);
    }
}
