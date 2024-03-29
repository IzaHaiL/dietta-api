package com.sugadev.scheduleservice.service;

import com.sugadev.scheduleservice.dto.*;
import com.sugadev.scheduleservice.model.ScheduleChild;
import com.sugadev.scheduleservice.model.ScheduleHistoryParent;

import java.util.List;

public interface ScheduleServices {

    ScheduleChildDTO saveScheduleAndVersion(ScheduleChildDTO scheduleChildDTO);

    ScheduleParentDTO saveSchedulesAndVersionParent(ScheduleParentDTO scheduleParentDTO);

    ResponseScheduleChildParentVideoDTO getScheduleById(Integer sheduleID);

    void deleteScheduleChildById(Integer sheduleID);

    List<ScheduleChildDTO> findAll();

    ScheduleChildDTO updateSchedule(int sheduleID, ScheduleChildDTO scheduleChildDTO);

    List<ScheduleChildDTO> getPrevVersion(Integer scheduleID);

    ScheduleChildDTO getPrevVersionDetail(Integer scheduleID);
    ScheduleHistoryParentDTO getScheduleParentHistoryDetailByScheduleParentHistoryId(Integer id);
    ScheduleHistoryChildDTO getScheduldeChildHistoryDetailByScheduleChildHistoryId(Integer id);
    List<ResponseScheduleChildUserVideoDTO> getVideoById (Integer userID);
    ScheduleParentDTO createSchedule (ScheduleParentDTO scheduleParentDTO);

    List<ScheduleParentDTO> getAllscheduleParent();

    ScheduleParentDTO getScheduleParentDetailById(Integer scheduleID);

    ScheduleParentDTO updateScheduleParentById(int scheduleID, ScheduleParentDTO scheduleParentDTO);

    void deleteScheduleParentById(Integer scheduleID);

//    List<ScheduleParentDTO> getAllScheduleParrentAll(Integer scheduleID);

//    List <ResponseDTO> getAllScheduleParentRest(Integer sheduleID);

//    List <ResponseDTO> getAllScheduleParentAllRest();

    List <ResponseScheduleChildParentVideoDTO> getAllScheduleList(Integer scheduleID);

    List<ResponseScheduleParentAndUserDTO> getAllscheduleParentByUser(Integer idUser);

    List<ScheduleHistoryParentDTO> getAllScheduleParentHistoryByScheduleParentId(Integer id);
    //ScheduleHistoryParentDTO getScheduleParentHistoryById(Integer idScheduleParent);

    List<ScheduleHistoryParentDTO> getallScheduleParentHistoryByUser (Integer id);




}
