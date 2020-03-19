package ru.axamit.statistic.domain;


/**
 * The class {@code PeriodResponse} is a response form for
 * {@link ru.axamit.statistic.controller.VisitEventController#getForPeriod(PeriodForm)}
 *
 * @author Madiyar Nurgazin (github.com/SnRise)
 */
public class PeriodResponse extends DayResponse {
    private long regularUsersCount;

    public PeriodResponse(DayResponse response) {
        super(response.getVisitCount(), response.getUniqueUsersCount());
    }

    public PeriodResponse(long visitCount, long uniqueUsersCount, long regularUsersCount) {
        super(visitCount, uniqueUsersCount);
        this.regularUsersCount = regularUsersCount;
    }

    public long getRegularUsersCount() {
        return regularUsersCount;
    }

    public void setRegularUsersCount(long regularUsersCount) {
        this.regularUsersCount = regularUsersCount;
    }

}
