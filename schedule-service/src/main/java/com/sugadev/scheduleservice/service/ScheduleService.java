package com.sugadev.scheduleservice.service;

import com.sugadev.scheduleservice.dto.ResponseDTO;
import com.sugadev.scheduleservice.dto.ScheduleDTO;
import com.sugadev.scheduleservice.dto.UserDTO;
import com.sugadev.scheduleservice.dto.VideoDTO;
import com.sugadev.scheduleservice.model.Schedule;
import com.sugadev.scheduleservice.model.ScheduleHistory;
import com.sugadev.scheduleservice.repository.ScheduleHistoryRepository;
import com.sugadev.scheduleservice.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ScheduleService implements ScheduleServices {

    @Autowired
  private ScheduleRepository scheduleRepository;

    @Autowired
    private ScheduleHistoryRepository scheduleHistoryRepository;

    ModelMapper modelMapper;
    RestTemplate restTemplate;



    public ScheduleDTO saveScheduleAndVersion(ScheduleDTO scheduleDTO) {
        Schedule schedule = modelMapper.map(scheduleDTO, Schedule.class);
        Schedule savedSchedule = scheduleRepository.save(schedule);
        ScheduleHistory scheduleHistory = modelMapper.map(scheduleDTO, ScheduleHistory.class);
        scheduleHistory.setSchedule(savedSchedule);
        scheduleHistory.setIdScheHistory(savedSchedule.getId_schedule());
        ScheduleHistory savedScheduleHistory = scheduleHistoryRepository.save(scheduleHistory);
        return modelMapper.map(savedSchedule, ScheduleDTO.class);
    }



    @Override
    public List<ScheduleDTO> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream().map(schedule -> modelMapper.map(schedule, ScheduleDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ResponseDTO getScheduleById(Integer sheduleID) {
        ResponseDTO responseDTO = new ResponseDTO();
        Schedule schedule = scheduleRepository.findById(sheduleID).get();
        ScheduleDTO scheduleDTO = modelMapper.map(schedule, ScheduleDTO.class);

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


//    @Override
//    public ScheduleDTO updateSchedule(int sheduleID, ScheduleDTO scheduleDTO) {
//        Schedule existing = scheduleRepository.findById(sheduleID).orElseThrow(() -> new IllegalArgumentException("Detail not found by id : " + scheduleDTO.getId_schedule()));
//
//        existing.setTitle(scheduleDTO.getTitle());
//        existing.setDate(scheduleDTO.getDate());
//
//        Schedule updatedSchedule = scheduleRepository.save(existing);
//
//        return modelMapper.map(updatedSchedule, ScheduleDTO.class);
//    }


    @Override
    public ScheduleDTO updateSchedule(int scheduleID, ScheduleDTO scheduleDTO) {
        Schedule existing = scheduleRepository.findById(scheduleID)
                .orElseThrow(() -> new IllegalArgumentException("Detail not found by id: " + scheduleID));

        ScheduleHistory scheduleHistory = new ScheduleHistory();
        scheduleHistory.setTitle(existing.getTitle());
        scheduleHistory.setDate(existing.getDate());
        scheduleHistory.setId_user(existing.getId_user());
        scheduleHistory.setId_video(existing.getId_video());
        scheduleHistory.setVersion(existing.getVersion());
        scheduleHistory.setSchedule(existing); // Set the Schedule entity to establish the relationship

        scheduleHistoryRepository.save(scheduleHistory);

      // existing.setId_schedule(scheduleDTO.getId_schedule());
        existing.setTitle(scheduleDTO.getTitle());
        existing.setDate(scheduleDTO.getDate());
        existing.setId_user(scheduleDTO.getId_user());
        existing.setId_video(scheduleDTO.getId_video());
       // existing.setVersion(scheduleDTO.getVersion());


        Schedule updatedSchedule = scheduleRepository.save(existing);

        return modelMapper.map(updatedSchedule, ScheduleDTO.class);
    }



    @Override
    public List<ScheduleDTO> getPrevVersion(Integer scheduleID) {
        List<ScheduleHistory> schedules = scheduleRepository.getProductVersionHistory(scheduleID);
        return schedules.stream().map(schedule -> modelMapper.map(schedule, ScheduleDTO.class)).collect(Collectors.toList());
    }




    @Override
    public void deleteById(Integer sid) {
        scheduleRepository.deleteById(sid);

    }
}
