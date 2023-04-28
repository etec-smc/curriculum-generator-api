package br.edu.etec.curriculum.generator.dto;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageOrientation;

import java.math.BigInteger;

public enum PageSize {
    A4(BigInteger.valueOf(11900), BigInteger.valueOf(16840));

    private final BigInteger h;
    private final BigInteger w;

    PageSize(BigInteger h, BigInteger w) {
        this.h = h;
        this.w = w;
    }

    public void configure(CTPageSz pageSz, boolean vertical) {
        if (vertical) {
            pageSz.setOrient(STPageOrientation.PORTRAIT);
            pageSz.setH(w);
            pageSz.setW(h);
        } else {
            pageSz.setOrient(STPageOrientation.LANDSCAPE);
            pageSz.setH(h);
            pageSz.setW(w);
        }
    }
}
