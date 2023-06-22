package com.sugadev.scheduleservice.service;

import com.sugadev.scheduleservice.dto.ResponseDTO;
import com.sugadev.scheduleservice.dto.ResponseDTOv2;
import com.sugadev.scheduleservice.dto.ScheduleDTO;
import com.sugadev.scheduleservice.dto.ScheduleDTOv2;

import java.util.List;

public interface ScheduleServicesv2 {

    ScheduleDTOv2 saveSchedule(ScheduleDTOv2 scheduleDTOv2);

    ResponseDTOv2 getScheduleById(Integer sheduleID);

    ScheduleDTOv2 updateSchedule(int sheduleID, ScheduleDTOv2 scheduleDTO);

    void deleteById(Integer sheduleID);

    List<ScheduleDTOv2> findAll();

}
