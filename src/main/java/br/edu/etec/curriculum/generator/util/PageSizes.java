package br.edu.etec.curriculum.generator.util;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;

public class PageSizes {
    private PageSizes() {
    }

    public static CTPageSz currentPageSize(CTSectPr section){
        if(!section.isSetPgSz()) {
            section.addNewPgSz();
        }

        return section.getPgSz();
    }
}
