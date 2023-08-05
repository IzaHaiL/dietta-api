package com.sugadev.scheduleservice.repository;

import com.sugadev.scheduleservice.model.ScheduleHistoryChild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleHistoryRepository extends JpaRepository<ScheduleHistoryChild,Integer> {

}
