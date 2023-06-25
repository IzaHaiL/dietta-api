package com.sugadev.scheduleservice.controller;

import com.sugadev.scheduleservice.dto.ResponseDTO;
import com.sugadev.scheduleservice.dto.ScheduleDTO;
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

    @GetMapping("/history/{id}")
    public ResponseEntity<List<ScheduleDTO>> getScheduleHistory(@PathVariable("id") Integer id) {
        List<ScheduleDTO> scheduleDTO = scheduleServices.getPrevVersion(id);
        return new ResponseEntity<>(scheduleDTO, HttpStatus.OK);
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
}