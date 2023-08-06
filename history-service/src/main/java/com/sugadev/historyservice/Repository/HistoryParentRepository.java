package com.sugadev.historyservice.Repository;

import com.sugadev.historyservice.Model.HistoryParent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryParentRepository extends JpaRepository<HistoryParent,Integer> {


    @Query("SELECT sv FROM HistoryParent sv WHERE sv.idUser=:id")
    List<HistoryParent> getHistoryParentByUserId(@Param("id") int id);

//    @Modifying
//    @Query("DELETE FROM  HistoryParent sv  where sv.idUser=:id")
//    void deleteAllByUserId(Integer id);

//    List<HistoryParent> deleteAllByIdUser(Integer id);
}
