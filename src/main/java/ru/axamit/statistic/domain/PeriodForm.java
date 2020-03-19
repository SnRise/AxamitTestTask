package ru.axamit.statistic.domain;


/**
 * Represents the start and end dates
 * of a time period
 *
 * @author Madiyar Nurgazin (github.com/SnRise)
 */
public class PeriodForm {
    private String dateFrom;

    private String dateTo;

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
}
