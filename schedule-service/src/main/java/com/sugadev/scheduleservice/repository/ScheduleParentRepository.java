package com.sugadev.scheduleservice.repository;

import com.sugadev.scheduleservice.model.ScheduleHistoryParent;
import com.sugadev.scheduleservice.model.ScheduleParent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleParentRepository extends JpaRepository<ScheduleParent, Integer> {

//    @Query("SELECT sv FROM ScheduleParent sv WHERE  sv.id_schedule=:id")
//    List<ScheduleParent> getAllScheduleParentRest(@Param("id") int idSchedule);

//    @Query("SELECT sp FROM ScheduleParent sp")
//    List<ScheduleParent> getAllScheduleParentAllRest();

    @Query("SELECT sv FROM ScheduleParent sv WHERE  sv.id_user=:id")
    List<ScheduleParent> getAllscheduleParentByUser(@Param("id") int idUser);

    @Query("SELECT sc FROM ScheduleHistoryParent sc WHERE sc.id_schedule_parent =:id")
    List<ScheduleHistoryParent> getAllScheduleParentHistoryByScheduleParentId(@Param("id")int idScheduleParent);

    @Query("SELECT sc FROM ScheduleHistoryParent sc WHERE sc.idScheHistoryParent =:id")
    ScheduleHistoryParent getScheduleParentHistoryDetailByScheduleParentHistoryId(@Param("id")int idScheduleParent);



}

