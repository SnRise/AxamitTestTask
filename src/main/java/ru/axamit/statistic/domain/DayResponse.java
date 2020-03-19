package ru.axamit.statistic.domain;

/**
 * The class {@code DayResponse} is a response form for
 * {@link ru.axamit.statistic.controller.VisitEventController#create(VisitEvent)}
 *
 * @author Madiyar Nurgazin (github.com/SnRise)
 */
public class DayResponse {
    private final long visitCount;

    private final long uniqueUsersCount;

    public DayResponse(long visitCount, long uniqueUsersCount) {
        this.visitCount = visitCount;
        this.uniqueUsersCount = uniqueUsersCount;
    }

    public long getVisitCount() {
        return visitCount;
    }

    public long getUniqueUsersCount() {
        return uniqueUsersCount;
    }
}
