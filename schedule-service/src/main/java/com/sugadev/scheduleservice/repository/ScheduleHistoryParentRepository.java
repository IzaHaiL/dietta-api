package com.sugadev.scheduleservice.repository;

import com.sugadev.scheduleservice.model.ScheduleHistoryParent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleHistoryParentRepository extends JpaRepository<ScheduleHistoryParent, Integer> {
}
