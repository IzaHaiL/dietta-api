package com.sugadev.scheduleservice.repository;

import com.sugadev.scheduleservice.model.ScheduleChild;
import com.sugadev.scheduleservice.model.ScheduleHistoryChild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleChild, Integer> {

    @Query("SELECT sv FROM ScheduleHistoryChild sv WHERE sv.scheduleChild.id_schedule_child = :id")
    List<ScheduleHistoryChild> getProductVersionHistory(@Param("id") int idSchedule);

    @Query("SELECT svd FROM ScheduleHistoryChild svd WHERE svd.idScheHistory = :id")
    List<ScheduleHistoryChild> getProductVersionDetail(@Param("id") int idScheHistory);

    @Query("SELECT svd FROM ScheduleChild svd WHERE svd.id_user = :id")
    List <ScheduleChild> getVideoById(@Param("id") int IdUser);

    @Query("SELECT svd FROM ScheduleChild svd WHERE svd.id_schedule_parent = :id")
    List <ScheduleChild> getAllScheduleList(@Param("id") int id_schedule);

}
