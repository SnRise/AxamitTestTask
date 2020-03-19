package ru.axamit.statistic.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;

/**
 * Represents a {@link Date} converter from a {@link String}
 *
 * @author Madiyar Nurgazin (github.com/SnRise)
 */
@Component
public class TimeConverter {
    private SimpleDateFormat dateFormat;

    public TimeConverter() {
        this.dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
    }

    /**
     * Returns {@link Date} represents the start of the day when
     * this method is called
     *
     * @return day start {@link Date}
     */
    public Date startOfDay() {
        return java.sql.Timestamp.valueOf(LocalDate.now().atStartOfDay());
    }

    /**
     * Returns {@link Date} represents the end of the day when
     * this method is called
     *
     * @return day end {@link Date}
     */
    public Date endOfDay() {
        return java.sql.Timestamp.valueOf(LocalDate.now().atTime(LocalTime.MAX));
    }

    /**
     * Returns {@link Date} from {@link String} which
     * represents the date and time
     *
     * @param date the {@link String} represents the date and time
     * @return the {@link Date} retrieved from {@code date}
     * @throws ParseException if {@code date} in the wrong format.
     */
    public Date dateFromString(String date) throws ParseException {
        return dateFormat.parse(date);
    }
}
