package com.sugadev.scheduleservice.repository;

import com.sugadev.scheduleservice.model.ScheduleParent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleDetailRepository extends JpaRepository<ScheduleParent, Integer> {

    @Query("SELECT sv FROM ScheduleParent sv WHERE  sv.id_schedule=:id")
    List<ScheduleParent> getAllScheduleParentRest(@Param("id") int idSchedule);

    @Query("SELECT sp FROM ScheduleParent sp")
    List<ScheduleParent> getAllScheduleParentAllRest();

}

