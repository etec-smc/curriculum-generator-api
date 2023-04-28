package br.edu.etec.curriculum.generator.service.line;

import br.edu.etec.curriculum.generator.dto.LineType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;

@Component
public class LineBuilderFactory {

    private final BeanFactory factory;

    public LineBuilderFactory(BeanFactory factory) {
        this.factory = factory;
    }

    public XWPFRun build(XWPFDocument document, LineType type) {
        return factory.getBeanProvider(LineBuilder.class)
                .stream()
                .filter(l -> l.accept(type))
                .findFirst()
                .map(b -> b.build(document))
                .orElseThrow();
    }

    public void buildEmpty(XWPFDocument document, LineType type) {
        var run = factory.getBeanProvider(LineBuilder.class)
                .stream()
                .filter(l -> l.accept(type))
                .findFirst()
                .map(b -> b.build(document))
                .orElseThrow();

        run.setText(" ");
    }

}
