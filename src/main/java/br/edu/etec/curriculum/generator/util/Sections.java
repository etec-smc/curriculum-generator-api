package br.edu.etec.curriculum.generator.util;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;

public class Sections {
    private Sections() {
    }

    public static CTSectPr currentSection(XWPFDocument document){
        var body = document.getDocument().getBody();

        if (!body.isSetSectPr()){
            body.addNewSectPr();
        }

        return body.getSectPr();
    }
}
