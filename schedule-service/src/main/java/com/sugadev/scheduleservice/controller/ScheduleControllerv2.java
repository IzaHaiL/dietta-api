package com.sugadev.scheduleservice.controller;

import com.sugadev.scheduleservice.dto.ResponseDTO;
import com.sugadev.scheduleservice.dto.ResponseDTOv2;
import com.sugadev.scheduleservice.dto.ScheduleDTO;
import com.sugadev.scheduleservice.dto.ScheduleDTOv2;
import com.sugadev.scheduleservice.model.Schedule;
import com.sugadev.scheduleservice.service.ScheduleServices;
import com.sugadev.scheduleservice.service.ScheduleServicesv2;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/schedule/v2")
@RestController
public class ScheduleControllerv2 {


    @Autowired
    private ScheduleServicesv2 scheduleServices;

    private ModelMapper modelMapper;


    @PostMapping("/add")
    public ResponseEntity<ScheduleDTOv2> saveSchedule(@RequestBody ScheduleDTOv2 scheduleDTO) {
        ScheduleDTOv2 sche = scheduleServices.saveSchedule(scheduleDTO);
        return new ResponseEntity<>(sche, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ScheduleDTOv2>> findAll() {
        List<ScheduleDTOv2> scheduleDTO = scheduleServices.findAll();
        return new ResponseEntity<>(scheduleDTO, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDTOv2> getScheduleById(@PathVariable("id") Integer id) {
        ResponseDTOv2 responseDTO = scheduleServices.getScheduleById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ScheduleDTOv2> updateSchedule(@PathVariable("id") Integer id, @RequestBody ScheduleDTOv2 scheduleDTO) {
        ScheduleDTOv2 updateSchedule = scheduleServices.updateSchedule(id, scheduleDTO);
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
}