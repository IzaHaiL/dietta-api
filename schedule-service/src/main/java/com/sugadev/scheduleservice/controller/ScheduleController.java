package com.sugadev.scheduleservice.controller;

import com.sugadev.scheduleservice.model.Schedule;
import com.sugadev.scheduleservice.service.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/schedule")
@AllArgsConstructor
public class ScheduleController {

    private ScheduleService scheduleService;


    @PostMapping("/add")
    public ResponseEntity<Schedule> saveSchedule(@RequestBody Schedule schedule){
        Schedule saveSchedule = scheduleService.saveSchedule(schedule);
        return new ResponseEntity<>(saveSchedule, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Schedule> findAll() {
        return scheduleService.findAll();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Schedule> updatedScedule( @PathVariable("id") Integer id, @RequestBody Schedule updatedScedule) {
        updatedScedule.setId(id);
        Schedule updated = scheduleService.updatedSchedule(updatedScedule);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable("id") Integer scheduleId){
        Schedule schedule = scheduleService.GetScheduleById(scheduleId);
        return ResponseEntity.ok(schedule);
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<Schedule> deleteSchedule(@PathVariable("id") Integer id) {
        try {
            scheduleService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
