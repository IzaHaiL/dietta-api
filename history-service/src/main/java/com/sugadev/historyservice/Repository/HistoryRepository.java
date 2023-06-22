package com.sugadev.historyservice.Repository;

import com.sugadev.historyservice.Model.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History,Integer> {
}
