package com.sugadev.scheduleservice.service;

import com.sugadev.scheduleservice.dto.*;

import java.util.List;

public interface ScheduleServices {

    ScheduleDTO saveScheduleAndVersion(ScheduleDTO scheduleDTO);

    ResponseDTO getScheduleById(Integer sheduleID);

    void deleteById(Integer sheduleID);

    List<ScheduleDTO> findAll();

    ScheduleDTO updateSchedule( int sheduleID,ScheduleDTO scheduleDTO);

    List<ScheduleHistoryDTO> getPrevVersion(Integer scheduleID);

    ScheduleHistoryDTO getPrevVersionDetail(Integer scheduleID);

    List<ResponseDTO> getVideoById (Integer userID);

    ScheduleParentDTO createSchedule (ScheduleParentDTO scheduleParentDTO);


    List<ScheduleParentDTO> getAllscheduleParent();

    ScheduleParentDTO getScheduleParentDetailById(Integer scheduleID);

    ScheduleParentDTO updateScheduleParentById(int scheduleID, ScheduleParentDTO scheduleParentDTO);

    void deleteScheduleParentById(Integer scheduleID);

//    List<ScheduleParentDTO> getAllScheduleParrentAll(Integer scheduleID);

    List <ResponseDTO> getAllScheduleParentRest(Integer sheduleID);

    List <ResponseDTO> getAllScheduleParentAllRest();

    List <ResponseDTO> getAllScheduleList(Integer scheduleID);

    List<ResponseDTO1> getAllscheduleParentByUser(Integer idUser);





}
