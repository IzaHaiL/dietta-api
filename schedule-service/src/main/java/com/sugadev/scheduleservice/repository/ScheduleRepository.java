package com.sugadev.scheduleservice.repository;

import com.sugadev.scheduleservice.dto.ScheduleDTO;
import com.sugadev.scheduleservice.model.Schedule;
import com.sugadev.scheduleservice.model.ScheduleHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    @Query("SELECT sv FROM com.sugadev.scheduleservice.model.ScheduleHistory sv WHERE sv.schedule.id = :id ORDER BY sv.version ASC")
    List<ScheduleHistory> getProductVersionHistory(@Param("id") int idSchedule);

    @Query("SELECT svd FROM com.sugadev.scheduleservice.model.ScheduleHistory svd WHERE svd.idScheHistory = :id")
    List<ScheduleHistory> getProductVersionDetail(@Param("id") int idScheHistory);

}
