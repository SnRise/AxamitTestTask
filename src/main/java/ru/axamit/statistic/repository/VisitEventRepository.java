package ru.axamit.statistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.axamit.statistic.domain.VisitEvent;

import java.util.Date;
import java.util.List;

/**
 * Repository  for {@link VisitEvent} entity.
 *
 * @author Madiyar Nurgazin (github.com/SnRise)
 */
public interface VisitEventRepository extends JpaRepository<VisitEvent, Long> {
    /**
     * Searches {@link VisitEvent} entities created in a given period.
     *
     * @param startOfDay the period start {@link Date}
     * @param endOfDate  the period end {@link Date}
     * @return a {@link List} of {@link VisitEvent} entities whose creation time is
     * between {@code startOfDate} and {@code endOfDate}
     */
    @Query(value = "SELECT * FROM visit_event WHERE creation_time BETWEEN ?1 AND ?2", nativeQuery = true)
    List<VisitEvent> findByPeriod(Date startOfDay, Date endOfDate);
}
