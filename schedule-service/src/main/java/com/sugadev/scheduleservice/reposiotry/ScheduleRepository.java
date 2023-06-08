package com.sugadev.scheduleservice.reposiotry;

import com.sugadev.scheduleservice.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository <Schedule, Integer> {
}
