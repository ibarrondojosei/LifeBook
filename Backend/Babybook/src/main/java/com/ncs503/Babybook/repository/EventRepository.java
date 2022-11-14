package com.ncs503.Babybook.repository;

import com.ncs503.Babybook.models.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
    

}
