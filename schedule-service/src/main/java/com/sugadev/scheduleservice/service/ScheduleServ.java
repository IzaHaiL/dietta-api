package com.sugadev.scheduleservice.service;

import com.sugadev.scheduleservice.model.Schedule;
import com.sugadev.scheduleservice.reposiotry.ScheduleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class ScheduleServ implements ScheduleService{
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule GetScheduleById(Integer scheduleId) {
        return scheduleRepository.findById(scheduleId).get();
    }

    @Override
    public void deleteById(Integer scheduleId) {
        scheduleRepository.deleteById(scheduleId);

    }

    @Override
    public Iterable<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule updatedSchedule(Schedule updatedSchedule) {
         Schedule existingSchedule = scheduleRepository.findById(updatedSchedule.getId())
                .orElseThrow(() -> new IllegalArgumentException("Video not found with id: " + updatedSchedule.getId()));

        existingSchedule.setId(updatedSchedule.getId());
        existingSchedule.setName(updatedSchedule.getName());


        return scheduleRepository.save(existingSchedule);
    }
}
