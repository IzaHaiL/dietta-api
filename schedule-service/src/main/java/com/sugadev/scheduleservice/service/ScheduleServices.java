package com.sugadev.scheduleservice.service;

import com.sugadev.scheduleservice.dto.ResponseDTO;
import com.sugadev.scheduleservice.dto.ScheduleDTO;
import com.sugadev.scheduleservice.dto.ScheduleHistoryDTO;

import java.util.List;

public interface ScheduleServices {

    ScheduleDTO saveScheduleAndVersion(ScheduleDTO scheduleDTO);

    ResponseDTO getScheduleById(Integer sheduleID);

    void deleteById(Integer sheduleID);

    List<ScheduleDTO> findAll();

    ScheduleDTO updateSchedule( int sheduleID,ScheduleDTO scheduleDTO);

    List<ScheduleHistoryDTO> getPrevVersion(Integer scheduleID);

    ScheduleHistoryDTO getPrevVersionDetail(Integer scheduleID);

}
