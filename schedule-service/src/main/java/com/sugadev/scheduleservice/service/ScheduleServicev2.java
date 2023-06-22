package com.sugadev.scheduleservice.service;

import com.sugadev.scheduleservice.dto.*;
import com.sugadev.scheduleservice.model.Schedule;
import com.sugadev.scheduleservice.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ScheduleServicev2 implements ScheduleServicesv2 {

    ScheduleRepository scheduleRepository;

    ModelMapper modelMapper;
     RestTemplate restTemplate;

    @Override
    public ScheduleDTOv2 saveSchedule(ScheduleDTOv2 scheduleDTOv2) {
        Schedule schedule = modelMapper.map(scheduleDTOv2, Schedule.class);
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return modelMapper.map(savedSchedule, ScheduleDTOv2.class);
    }

    @Override
    public List<ScheduleDTOv2> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream().map(schedule -> modelMapper.map(schedule, ScheduleDTOv2.class)).collect(Collectors.toList());
    }

    @Override
    public ResponseDTOv2 getScheduleById(Integer sheduleID) {
        ResponseDTOv2 responseDTO = new ResponseDTOv2();
        Schedule schedule = scheduleRepository.findById(sheduleID).get();
        ScheduleDTOv2 scheduleDTO = modelMapper.map(schedule, ScheduleDTOv2.class);

        ResponseEntity<VideoDTO> responseEntity = restTemplate
                .getForEntity("http://video/video/" + schedule.getId_video(),
                        VideoDTO.class);

        VideoDTO videoDTO = responseEntity.getBody();

        ResponseEntity<UserDTO> responseEntityUser = restTemplate
                .getForEntity("http://user/user/" + schedule.getId_user(),
                        UserDTO.class);

        UserDTO userDTO = responseEntityUser.getBody();

        System.out.println(responseEntity.getStatusCode());

        responseDTO.setUser(userDTO);
        responseDTO.setSchedule(scheduleDTO);
        responseDTO.setVideo(videoDTO);

        return responseDTO;
    }





    @Override
    public ScheduleDTOv2 updateSchedule(int sheduleID, ScheduleDTOv2 scheduleDTO) {
        Schedule existing = scheduleRepository.findById(sheduleID).orElseThrow(() -> new IllegalArgumentException("Detail not found by id : " + scheduleDTO.getId_schedule()));

        existing.setTitle(scheduleDTO.getTitle());
        existing.setDate(scheduleDTO.getDate());

        Schedule updatedSchedule = scheduleRepository.save(existing);
        return modelMapper.map(updatedSchedule, ScheduleDTOv2.class);
    }

    @Override
    public void deleteById(Integer sid) {
        scheduleRepository.deleteById(sid);

    }
}
