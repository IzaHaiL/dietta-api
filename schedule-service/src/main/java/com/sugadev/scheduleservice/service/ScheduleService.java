package com.sugadev.scheduleservice.service;

import com.sugadev.scheduleservice.model.Schedule;

public interface ScheduleService {

    Schedule saveSchedule(Schedule schedule);

    Schedule GetScheduleById(Integer scheduleId);

    void deleteById(Integer scheduleId);

    Iterable<Schedule> findAll();

    Schedule updatedSchedule (Schedule updatedSchedule);


}
