package br.edu.etec.curriculum.generator.service;

import br.edu.etec.curriculum.generator.dto.PageSize;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static br.edu.etec.curriculum.generator.util.PageSizes.currentPageSize;
import static br.edu.etec.curriculum.generator.util.Sections.currentSection;

@Service
public class PageConfigurationServiceImpl implements PageConfigurationService {

    @Value("${curriculum.generator.config.page.size}")
    private String paper;

    @Override
    public void configure(XWPFDocument document) {
        var pageSize = PageSize.valueOf(paper);

        pageSize.configure(currentPageSize(currentSection(document)), true);
    }
}
