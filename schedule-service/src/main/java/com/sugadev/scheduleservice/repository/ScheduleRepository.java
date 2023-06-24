package com.sugadev.scheduleservice.repository;

import com.sugadev.scheduleservice.model.Schedule;
import com.sugadev.scheduleservice.model.ScheduleHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    @Query("SELECT sv FROM com.sugadev.scheduleservice.model.ScheduleHistory sv WHERE sv.schedule.id = :id_schedule ORDER BY sv.version ASC")
    List<ScheduleHistory> getProductVersionHistory(@Param("id") int idSchedule);

}
