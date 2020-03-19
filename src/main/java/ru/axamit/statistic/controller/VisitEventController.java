package ru.axamit.statistic.controller;

import org.springframework.web.bind.annotation.*;
import ru.axamit.statistic.domain.DayResponse;
import ru.axamit.statistic.domain.PeriodForm;
import ru.axamit.statistic.domain.PeriodResponse;
import ru.axamit.statistic.domain.VisitEvent;
import ru.axamit.statistic.exceptions.IncorrectDateFormatException;
import ru.axamit.statistic.service.VisitEventService;

import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


/**
 * Rest controller for {@link VisitEvent} entity. Allows you to create entities
 * and get entities created in a given period.
 *
 * @author Madiyar Nurgazin (github.com/SnRise)
 */
@RestController
@RequestMapping("/api/0")
public class VisitEventController {
    private final VisitEventService visitEventService;

    public VisitEventController(VisitEventService visitEventService) {
        this.visitEventService = visitEventService;
    }

    private DayResponse createResponse(List<VisitEvent> visitEvents) {
        long visitCount = visitEvents.size();
        long uniqueUsersCount = visitEvents.stream().mapToLong(VisitEvent::getUserId).distinct().count();
        return new DayResponse(visitCount, uniqueUsersCount);
    }

    private long getRegular(List<VisitEvent> visitEvents) {
        HashMap<Long, HashSet<Long>> countingMap = new HashMap<>();
        for (VisitEvent event : visitEvents) {
            if (!countingMap.containsKey(event.getUserId())) {
                countingMap.put(event.getUserId(), new HashSet<>());
            }
            countingMap.get(event.getUserId()).add(event.getPageId());
        }
        return countingMap.entrySet().stream().filter(entry -> entry.getValue().size() >= 10).count();
    }

    /**
     * POST request handle.
     * Creates the {@link VisitEvent} in the repository and returns {@link List} of
     * {@link VisitEvent} created today.
     *
     * @param visitEvent the {@link VisitEvent} that will be saved
     * @return the {@link List} of {@link VisitEvent} entities created today
     */
    @PostMapping("events/create")
    public DayResponse create(@RequestBody VisitEvent visitEvent) {
        visitEventService.create(visitEvent);
        List<VisitEvent> visitEvents = visitEventService.findToday();
        return createResponse(visitEvents);
    }

    /**
     * GET request handle.
     * Searches entities created in a given period.
     *
     * @param periodForm the {@link PeriodForm} represents a given period
     * @return the {@link List} of {@link VisitEvent} created in a given period
     * @throws IncorrectDateFormatException if the given period in the wrong format
     * @see VisitEventService#findByPeriod(PeriodForm)
     */
    @GetMapping("events")
    public PeriodResponse getForPeriod(@RequestBody PeriodForm periodForm) throws IncorrectDateFormatException {
        try {
            List<VisitEvent> visitEvents = visitEventService.findByPeriod(periodForm);
            PeriodResponse response = new PeriodResponse(createResponse(visitEvents));
            response.setRegularUsersCount(getRegular(visitEvents));
            return response;
        } catch (ParseException e) {
            throw new IncorrectDateFormatException();
        }
    }
}
