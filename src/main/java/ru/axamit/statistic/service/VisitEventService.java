package ru.axamit.statistic.service;

import org.springframework.stereotype.Service;
import ru.axamit.statistic.domain.PeriodForm;
import ru.axamit.statistic.domain.VisitEvent;
import ru.axamit.statistic.repository.VisitEventRepository;
import ru.axamit.statistic.util.TimeConverter;

import java.text.ParseException;
import java.util.List;


/**
 * Service for {@link VisitEvent} entity
 *
 * @author Madiyar Nurgazin (github.com/SnRise)
 */
@Service
public class VisitEventService {
    private final VisitEventRepository visitEventRepository;
    private final TimeConverter timeConverter;

    public VisitEventService(VisitEventRepository visitEventRepository, TimeConverter timeConverter) {
        this.visitEventRepository = visitEventRepository;
        this.timeConverter = timeConverter;
    }

    /**
     * Searches in the repository for all {@link VisitEvent} created today.
     *
     * @return the {@link List} of {@link VisitEvent} created today
     */
    public List<VisitEvent> findToday() {
        return visitEventRepository.findByPeriod(timeConverter.startOfDay(), timeConverter.endOfDay());
    }

    /**
     * Searches in the repository for all {@link VisitEvent} in a given period.
     *
     * @param periodForm the {@link PeriodForm} represents the beginning and end of period
     * @return the {@link List} of {@link VisitEvent} created in a given period
     * @throws ParseException if {@code periodForm} represents fields in the wrong format
     * @see TimeConverter#dateFromString(String)
     */
    public List<VisitEvent> findByPeriod(PeriodForm periodForm) throws ParseException {
        return visitEventRepository.findByPeriod(timeConverter.dateFromString(periodForm.getDateFrom()),
                timeConverter.dateFromString(periodForm.getDateTo()));
    }

    /**
     * Saves the {@link VisitEvent} in the repository.
     *
     * @param visitEvent the {@link VisitEvent} that will be saved
     * @return the {@link VisitEvent} saved in repository with generated {@code id} and {@code creationTime}
     */
    public VisitEvent create(VisitEvent visitEvent) {
        return visitEventRepository.save(visitEvent);
    }

}
