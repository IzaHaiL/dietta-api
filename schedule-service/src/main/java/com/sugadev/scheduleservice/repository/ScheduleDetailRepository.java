package com.sugadev.scheduleservice.repository;

import com.sugadev.scheduleservice.model.ScheduleDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleDetailRepository extends JpaRepository<ScheduleDetail, Integer> {
}
