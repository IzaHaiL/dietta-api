package com.sugadev.scheduleservice.controller;

import com.sugadev.scheduleservice.dto.*;
import com.sugadev.scheduleservice.model.Schedule;
import com.sugadev.scheduleservice.service.ScheduleServices;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/schedule")
@RestController
public class ScheduleController {


    @Autowired
    private ScheduleServices scheduleServices;

    private ModelMapper modelMapper;


    @PostMapping("history/child/add")
    public ResponseEntity<ScheduleDTO> saveScheduleAndVersion(@RequestBody ScheduleDTO scheduleDTO) {
        ScheduleDTO sche = scheduleServices.saveScheduleAndVersion(scheduleDTO);
        return new ResponseEntity<>(sche, HttpStatus.CREATED);
    }

    @PostMapping("history/parent/add")
    public ResponseEntity<ScheduleParentDTO> saveScheduleAndVersionParent(@RequestBody ScheduleParentDTO scheduleParentDTO) {
        ScheduleParentDTO sche = scheduleServices.saveSchedulesAndVersionParent(scheduleParentDTO);
        return new ResponseEntity<>(sche, HttpStatus.CREATED);
    }




    @GetMapping("/child/all")
    public ResponseEntity<List<ScheduleDTO>> findAll() {
        List<ScheduleDTO> scheduleDTO = scheduleServices.findAll();
        return new ResponseEntity<>(scheduleDTO, HttpStatus.OK);
    }

    @GetMapping(    "/child/{id}")
    public ResponseEntity<ResponseDTO> getScheduleById(@PathVariable("id") Integer id) {
        ResponseDTO responseDTO = scheduleServices.getScheduleById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/child/all/{id}")
    public ResponseEntity<List<ResponseDTO>> getAllScheduleList(@PathVariable("id") Integer id) {
        List<ResponseDTO> responseDTOS = scheduleServices.getAllScheduleList(id);
        return new ResponseEntity<>(responseDTOS, HttpStatus.OK);
    }

    @GetMapping("/sche/{id}")
    public ResponseEntity<List<ScheduleHistoryDTO>> getScheduleHistory(@PathVariable("id") Integer id) {
        List<ScheduleHistoryDTO> scheduleHistoryDTOS = scheduleServices.getPrevVersion(id);
        return new ResponseEntity<>(scheduleHistoryDTOS, HttpStatus.OK);
    }

    @GetMapping("/version/{id}")
    public ResponseEntity<ScheduleHistoryDTO> getScheduleHistoryDetail(@PathVariable("id") Integer id) {
        ScheduleHistoryDTO scheduleHistoryDTOS = scheduleServices.getPrevVersionDetail(id);
        return new ResponseEntity<>(scheduleHistoryDTOS, HttpStatus.OK);
    }

    @GetMapping("/video/{id}")
    public ResponseEntity<List<ResponseDTOV>> getVideoById(@PathVariable("id") Integer id) {
        List<ResponseDTOV> responseDTO = scheduleServices.getVideoById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/child/update/{id}")
    public ResponseEntity<ScheduleDTO> updateSchedule(@PathVariable("id") Integer id, @RequestBody ScheduleDTO scheduleDTO) {
        ScheduleDTO updateSchedule = scheduleServices.updateSchedule(id, scheduleDTO);
        return new ResponseEntity<>(updateSchedule, HttpStatus.OK);
    }


    @DeleteMapping("/child/delete/{id}")
    public ResponseEntity<Schedule> deleteSchedule(@PathVariable("id") Integer id) {
        try {
            scheduleServices.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/parent/add")
    public ResponseEntity<ScheduleParentDTO> createScheduleParent(@RequestBody ScheduleParentDTO scheduleParentDTO) {
        ScheduleParentDTO scheduleDTO = scheduleServices.createSchedule(scheduleParentDTO);
        return new ResponseEntity<>(scheduleDTO, HttpStatus.CREATED);
    }


    @GetMapping("/parent/all")
    public ResponseEntity<List<ScheduleParentDTO>> getAllscheduleParent() {
        List<ScheduleParentDTO> scheduleDTO = scheduleServices.getAllscheduleParent();
        return new ResponseEntity<>(scheduleDTO, HttpStatus.OK);
    }

    @GetMapping("/parent/{id}")
    public ResponseEntity<ScheduleParentDTO> getScheduleParentById(@PathVariable("id") Integer id) {
        ScheduleParentDTO scheduleParentDTO = scheduleServices.getScheduleParentDetailById(id);
        return new ResponseEntity<>(scheduleParentDTO, HttpStatus.OK);
    }

    @GetMapping("/parent/user/{id}")
    public ResponseEntity<List<ResponseDTO1>> getAllscheduleParentByUser(@PathVariable("id") Integer id) {
        List<ResponseDTO1> responseDTO1 = scheduleServices.getAllscheduleParentByUser(id);
        return new ResponseEntity<>(responseDTO1, HttpStatus.OK);
    }



    @PutMapping("/parent/update/{id}")
    public ResponseEntity<ScheduleParentDTO> updateScheduleParentById(@PathVariable("id") Integer id, @RequestBody ScheduleParentDTO scheduleParentDTO) {
        ScheduleParentDTO updateSchedule = scheduleServices.updateScheduleParentById(id, scheduleParentDTO);
        return new ResponseEntity<>(updateSchedule, HttpStatus.OK);
    }


    @DeleteMapping("/parent/delete/{id}")
    public ResponseEntity<ScheduleParentDTO> deleteScheduleParentById(@PathVariable("id") Integer id) {
        try {
            scheduleServices.deleteScheduleParentById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/parent/res/{id}")
    public ResponseEntity<List<ResponseDTO>> getAllScheduleParentRest(@PathVariable("id") Integer id) {
        List<ResponseDTO> responseDTO = scheduleServices.getAllScheduleParentRest(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/parent/res/all")
    public ResponseEntity<List<ResponseDTO>> getAllScheduleParentAllRest() {
        List<ResponseDTO> responseDTO = scheduleServices.getAllScheduleParentAllRest();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }




}