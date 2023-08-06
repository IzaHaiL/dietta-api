package com.sugadev.historyservice.Repository;

import com.sugadev.historyservice.Dto.HistoryDTO;
import com.sugadev.historyservice.Model.History;
import com.sugadev.historyservice.Model.HistoryParent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History,Integer> {

    @Query("SELECT sv FROM History sv WHERE sv.idUser=:id")
    List<History> getAllBiasaUser(@Param("id") int id);
}
