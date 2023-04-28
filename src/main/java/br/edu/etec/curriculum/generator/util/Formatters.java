package br.edu.etec.curriculum.generator.util;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Formatters {
    private Formatters() {
    }

    public static String formatPhone(String phone){
        var pattern = phone.length() == 11 ? "(\\d{2})(\\d{5})(\\d{4})" : "(\\d{2})(\\d{4})(\\d{4})";

        return phone.replaceAll(pattern, "($1) $2-$3");
    }

    public static String formatYearMonth(YearMonth yearMonth){
        return yearMonth.format(DateTimeFormatter.ofPattern("MM/yyyy"));
    }
}
