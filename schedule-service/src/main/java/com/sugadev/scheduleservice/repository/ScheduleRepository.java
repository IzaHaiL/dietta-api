package com.sugadev.scheduleservice.repository;

import com.sugadev.scheduleservice.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
}
