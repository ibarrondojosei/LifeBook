package com.ncs503.Babybook.repository;

import com.ncs503.Babybook.models.entity.EventEntity;
import com.ncs503.Babybook.models.utility.TagsEventEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity, Long>, JpaSpecificationExecutor<EventEntity> {

    @Query(value = "SELECT * FROM events e INNER JOIN subject_event se \n" +
                    "ON e.event_id = se.event_id WHERE se.subject_id = ? " +
                    "ORDER BY e.date DESC", nativeQuery = true)
    List<EventEntity> findAllBySubject(Long subject_id);

    @Query(value = "SELECT * FROM events e INNER JOIN subject_event se \n" +
                    "ON e.event_id = se.event_id WHERE se.subject_id = ? " +
                    "AND e.event_enum = ? ORDER BY e.date DESC" , nativeQuery = true)
    List<EventEntity> findAllByTags(Long subject_id, String eventEnum);

    @Query(value = "SELECT * FROM events e INNER JOIN subject_event se \n" +
                    "ON e.event_id = se.event_id WHERE se.subject_id = ? " +
                    "AND e.highlight_moment = ? ORDER BY e.date DESC" , nativeQuery = true)
    List<EventEntity> findAllByHighlightMoment(Long subject_id, Boolean highlightMoment);

//    @Query(value = "SELECT * FROM events e INNER JOIN subject_event se \n" +
//            "\t\t\tON e.event_id = se.event_id WHERE se.subject_id = ? AND e.date = ? ORDER BY e.date DESC" , nativeQuery = true)
//    List<EventEntity> findAllByDate(Long subject_id, LocalDate date);

}
