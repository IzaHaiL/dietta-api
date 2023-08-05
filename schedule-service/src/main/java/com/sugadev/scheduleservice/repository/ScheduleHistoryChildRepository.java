package com.sugadev.scheduleservice.repository;

import com.sugadev.scheduleservice.model.ScheduleChild;
import com.sugadev.scheduleservice.model.ScheduleHistoryChild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleHistoryChildRepository extends JpaRepository<ScheduleHistoryChild,Integer> {



}
