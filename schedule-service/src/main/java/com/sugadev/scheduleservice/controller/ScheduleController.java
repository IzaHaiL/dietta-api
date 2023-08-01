package com.sugadev.scheduleservice.controller;

import com.sugadev.scheduleservice.dto.ResponseDTO;
import com.sugadev.scheduleservice.dto.ScheduleDTO;
import com.sugadev.scheduleservice.dto.ScheduleDetailDTO;
import com.sugadev.scheduleservice.dto.ScheduleHistoryDTO;
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


    @PostMapping("/add")
    public ResponseEntity<ScheduleDTO> saveScheduleAndVersion(@RequestBody ScheduleDTO scheduleDTO) {
        ScheduleDTO sche = scheduleServices.saveScheduleAndVersion(scheduleDTO);
        return new ResponseEntity<>(sche, HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public ResponseEntity<List<ScheduleDTO>> findAll() {
        List<ScheduleDTO> scheduleDTO = scheduleServices.findAll();
        return new ResponseEntity<>(scheduleDTO, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDTO> getScheduleById(@PathVariable("id") Integer id) {
        ResponseDTO responseDTO = scheduleServices.getScheduleById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
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
    public ResponseEntity<List<ResponseDTO>> getVideoById(@PathVariable("id") Integer id) {
        List<ResponseDTO> responseDTO = scheduleServices.getVideoById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ScheduleDTO> updateSchedule(@PathVariable("id") Integer id, @RequestBody ScheduleDTO scheduleDTO) {
        ScheduleDTO updateSchedule = scheduleServices.updateSchedule(id, scheduleDTO);
        return new ResponseEntity<>(updateSchedule, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Schedule> deleteSchedule(@PathVariable("id") Integer id) {
        try {
            scheduleServices.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/parent/add")
    public ResponseEntity<ScheduleDetailDTO> createScheduleParent(@RequestBody ScheduleDetailDTO scheduleDetailDTO) {
        ScheduleDetailDTO scheduleDTO = scheduleServices.createSchedule(scheduleDetailDTO);
        return new ResponseEntity<>(scheduleDTO, HttpStatus.CREATED);
    }


    @GetMapping("/parent/all")
    public ResponseEntity<List<ScheduleDetailDTO>> getAllscheduleParent() {
        List<ScheduleDetailDTO> scheduleDTO = scheduleServices.getAllscheduleParent();
        return new ResponseEntity<>(scheduleDTO, HttpStatus.OK);
    }

    @GetMapping("/parent/{id}")
    public ResponseEntity<ScheduleDetailDTO> getScheduleParentById(@PathVariable("id") Integer id) {
        ScheduleDetailDTO scheduleDetailDTO = scheduleServices.getScheduleParentDetailById(id);
        return new ResponseEntity<>(scheduleDetailDTO, HttpStatus.OK);
    }

    @PutMapping("/parent/update/{id}")
    public ResponseEntity<ScheduleDetailDTO> updateScheduleParentById( @PathVariable("id") Integer id, @RequestBody ScheduleDetailDTO scheduleDetailDTO) {
        ScheduleDetailDTO updateSchedule = scheduleServices.updateScheduleParentById(id, scheduleDetailDTO);
        return new ResponseEntity<>(updateSchedule, HttpStatus.OK);
    }


    @DeleteMapping("/parent/delete/{id}")
    public ResponseEntity<ScheduleDetailDTO> deleteScheduleParentById(@PathVariable("id") Integer id) {
        try {
            scheduleServices.deleteScheduleParentById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}