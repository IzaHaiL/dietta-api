package com.sugadev.scheduleservice.repository;

import com.sugadev.scheduleservice.model.ScheduleHistoryChild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleHistoryChildRepository extends JpaRepository<ScheduleHistoryChild,Integer> {

    @Query("SELECT sv FROM ScheduleHistoryChild sv WHERE sv.idScheHistoryChild =:id")
    ScheduleHistoryChild getScheduldeChildHistoryDetailByScheduleChildHistoryId(@Param("id")int id);

}
