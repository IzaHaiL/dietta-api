package com.sugadev.scheduleservice.repository;

import com.sugadev.scheduleservice.model.ScheduleHistoryParent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleHistoryParentRepository extends JpaRepository<ScheduleHistoryParent, Integer> {



}
