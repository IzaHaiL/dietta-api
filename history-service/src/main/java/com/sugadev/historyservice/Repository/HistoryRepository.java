package com.sugadev.historyservice.Repository;

import com.sugadev.historyservice.Model.History;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History,Integer> {
    @Query("SELECT sv FROM History sv WHERE  sv.idUser=:id")
    List<History> getHistoriesByUser(@Param("id") int idUser);

}
