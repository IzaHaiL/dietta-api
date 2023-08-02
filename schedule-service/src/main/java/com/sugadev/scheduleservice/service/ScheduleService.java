package com.sugadev.scheduleservice.service;

import com.sugadev.scheduleservice.dto.*;
import com.sugadev.scheduleservice.model.Schedule;
import com.sugadev.scheduleservice.model.ScheduleHistory;
import com.sugadev.scheduleservice.repository.ScheduleHistoryRepository;
import com.sugadev.scheduleservice.repository.ScheduleRepository;
import jakarta.annotation.security.RolesAllowed;
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


 //add kedua table sekaligus
    public ScheduleDTO saveScheduleAndVersion(ScheduleDTO scheduleDTO) {
        Schedule schedule = modelMapper.map(scheduleDTO, Schedule.class);
        Schedule savedSchedule = scheduleRepository.save(schedule);
        ScheduleHistory scheduleHistory = modelMapper.map(scheduleDTO, ScheduleHistory.class);
        scheduleHistory.setSchedule(savedSchedule);
        scheduleHistory.setIdScheHistory(savedSchedule.getId_schedule());
        ScheduleHistory savedScheduleHistory = scheduleHistoryRepository.save(scheduleHistory);
        return modelMapper.map(savedSchedule, ScheduleDTO.class);
    }


//    @Override
//    public ScheduleDTO saveScheduleAndVersion(ScheduleDTO scheduleDTO) {
//        Schedule schedule = modelMapper.map(scheduleDTO, Schedule.class);
//        Schedule savedSchedule = scheduleRepository.save(schedule);
//        return modelMapper.map(savedSchedule, ScheduleDTO.class);
//    }



    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
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

// Update Biasa
//    @Override
//    public ScheduleDTO updateSchedule(int sheduleID, ScheduleDTO scheduleDTO) {
//        Schedule existing = scheduleRepository.findById(sheduleID).orElseThrow(() -> new IllegalArgumentException("Detail not found by id : " + scheduleDTO.getId_schedule()));
//        existing.setTitle(scheduleDTO.getTitle());
//        existing.setDate(scheduleDTO.getDate());
//        Schedule updatedSchedule = scheduleRepository.save(existing);
//        return modelMapper.map(updatedSchedule, ScheduleDTO.class);
//    }


    @Override
    public ScheduleDTO updateSchedule(int scheduleID, ScheduleDTO scheduleDTO) {
        Schedule existing = scheduleRepository.findById(scheduleID)
                .orElseThrow(() -> new IllegalArgumentException("Detail not found by id: " + scheduleID));


        existing.setTitle(scheduleDTO.getTitle());
        existing.setDate(scheduleDTO.getDate());
        existing.setId_user(scheduleDTO.getId_user());
        existing.setId_video(scheduleDTO.getId_video());
        //    existing.setVersion(scheduleDTO.getVersion());


        Schedule updatedSchedule = scheduleRepository.save(existing);

        // Ambil data yang ada di table schedule kemudian di masukin ke id scheule history
        ScheduleHistory scheduleHistory = new ScheduleHistory();
        scheduleHistory.setId_schedule(updatedSchedule.getId_schedule());
        scheduleHistory.setTitle(updatedSchedule.getTitle());
        scheduleHistory.setDate(updatedSchedule.getDate());
        scheduleHistory.setId_user(updatedSchedule.getId_user());
        scheduleHistory.setId_video(updatedSchedule.getId_video());
        scheduleHistory.setVersion(updatedSchedule.getVersion());
        scheduleHistory.setSchedule(updatedSchedule);

        scheduleHistoryRepository.save(scheduleHistory);

        return modelMapper.map(updatedSchedule, ScheduleDTO.class);
    }



    @Override
    public List<ScheduleHistoryDTO> getPrevVersion(Integer scheduleID) {
        List<ScheduleHistory> schedules = scheduleRepository.getProductVersionHistory(scheduleID);
        return schedules.stream().map(schedule -> modelMapper.map(schedule, ScheduleHistoryDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ScheduleHistoryDTO getPrevVersionDetail(Integer scheduleID) {
        ScheduleHistory scheduleHistory = scheduleHistoryRepository.findById(scheduleID)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + scheduleID));
        return modelMapper.map(scheduleHistory, ScheduleHistoryDTO.class);
    }




    @Override
    public void deleteById(Integer sid) {
        scheduleRepository.deleteById(sid);

    }
}
