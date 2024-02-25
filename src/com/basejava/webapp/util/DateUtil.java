package com.basejava.webapp.util;

import com.basejava.webapp.model.Period;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/yyyy");
    public static final LocalDate NOW = LocalDate.of(2040, 1, 1);

    public static String format(@NonNull LocalDate date) {
        return date.equals(NOW) ? "NOW" : date.format(FORMATTER);
    }

    public static LocalDate parseDate(String date) {
        if (HtmlUtil.isEmpty(date) || date.equals("NOW")) return NOW;
        YearMonth yM = YearMonth.parse(date, FORMATTER);
        return of(yM.getYear(), yM.getMonth());
    }

    public static LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }

    public static class HtmlUtil {
        public static boolean isEmpty(String text) {
            return text == null || text.trim().isEmpty();
        }
        public static String format(Period period) {
            return DateUtil.format(period.getDateOfStart()) + " - " + DateUtil.format(period.getDateOfEnd());
        }
    }
}