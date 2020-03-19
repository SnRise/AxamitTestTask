package ru.axamit.statistic.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.axamit.statistic.domain.DayResponse;
import ru.axamit.statistic.domain.PeriodForm;
import ru.axamit.statistic.domain.PeriodResponse;
import ru.axamit.statistic.domain.VisitEvent;
import ru.axamit.statistic.exceptions.IncorrectDateFormatException;
import ru.axamit.statistic.service.VisitEventService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Basic tests for {@link VisitEventController}
 *
 * @author Madiyar Nurgazin (github.com/SnRise)
 */
public class VisitEventControllerTest {
    private VisitEventService visitEventService;
    private VisitEventController visitEventController;
    private List<VisitEvent> visitEvents;
    private VisitEvent visitEvent;

    @Before
    public void init() {
        visitEventService = mock(VisitEventService.class);
        visitEventController = new VisitEventController(visitEventService);
        visitEvent = new VisitEvent();
        visitEvents = new ArrayList<>();
        for (int i = 1; i <= 100; ++i) {
            for (int j = i; j <= 100; ++j) {
                VisitEvent visitEvent = new VisitEvent();
                visitEvent.setPageId(i);
                visitEvent.setUserId(j);
                visitEvents.add(visitEvent);
            }
        }
    }

    @Test
    public void createTest() {
        when(visitEventService.findToday()).thenReturn(visitEvents);
        DayResponse response = new DayResponse(5050, 100);
        DayResponse actualResponse = visitEventController.create(visitEvent);
        Assert.assertEquals(response.getUniqueUsersCount(), actualResponse.getUniqueUsersCount());
        Assert.assertEquals(response.getVisitCount(), actualResponse.getVisitCount());
    }

    @Test
    public void getForPeriodTest() throws IncorrectDateFormatException, ParseException {
        PeriodForm testForm = new PeriodForm();
        when(visitEventService.findByPeriod(testForm)).thenReturn(visitEvents);
        PeriodResponse response = new PeriodResponse(5050, 100, 91);
        PeriodResponse actualResponse = visitEventController.getForPeriod(testForm);
        Assert.assertEquals(response.getRegularUsersCount(), actualResponse.getRegularUsersCount());
        Assert.assertEquals(response.getUniqueUsersCount(), actualResponse.getUniqueUsersCount());
        Assert.assertEquals(response.getVisitCount(), actualResponse.getVisitCount());
    }
}