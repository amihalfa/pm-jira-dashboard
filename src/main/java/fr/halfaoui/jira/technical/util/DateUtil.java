package fr.halfaoui.jira.technical.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author amirouche
 */
public class DateUtil {
    private DateUtil(){}

    public static LocalDate dateToLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date localDateToDate(LocalDate date) {
        if (date == null) {
            return null;
        }
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date getStartOfDay(Date date) {
        if (date == null) {
            return null;
        }
        return Date.from(date.toInstant().atZone(ZoneId.systemDefault()).truncatedTo(ChronoUnit.DAYS).toInstant());
    }
}
