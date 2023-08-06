package com.sugadev.historyservice.Repository;

import com.sugadev.historyservice.Model.HistoryChild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryChildRepository extends JpaRepository<HistoryChild,Integer> {
//    @Query("SELECT sv FROM HistoryChild sv WHERE  sv.idUser=:id")
//    List<HistoryChild> getHistoriesByUser(@Param("id") int idUser);

//    @Query("SELECT sv FROM History sv WHERE  sv.idScheduleHistory=:id")
//    List<History> getHistorieDetailByid(@Param("id") int idSchedule);


//    @Query("SELECT sv FROM HistoryChild sv WHERE sv.idScheduleHistory=:id")
//    List<HistoryChild> getAllHistoryByScheHistoryId(@Param("id")int id);


    @Query("SELECT sv FROM HistoryChild sv WHERE sv.idHistoryParent=:id")
    List<HistoryChild> getHistoryChildByHistoryParentId(@Param("id")int id);


}
