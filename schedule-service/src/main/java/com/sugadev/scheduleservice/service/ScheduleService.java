package com.sugadev.scheduleservice.service;

import com.sugadev.scheduleservice.dto.*;
import com.sugadev.scheduleservice.model.Schedule;
import com.sugadev.scheduleservice.model.ScheduleHistoryParent;
import com.sugadev.scheduleservice.model.ScheduleParent;
import com.sugadev.scheduleservice.model.ScheduleHistory;
import com.sugadev.scheduleservice.repository.ScheduleDetailRepository;
import com.sugadev.scheduleservice.repository.ScheduleHistoryParentRepository;
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

import java.util.ArrayList;
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

    @Autowired
    private ScheduleDetailRepository scheduleDetailRepository;
    @Autowired
    private ScheduleHistoryParentRepository scheduleHistoryParentRepository;


    @Autowired
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
    public ScheduleParentDTO saveSchedulesAndVersionParent(ScheduleParentDTO scheduleParentDTO) {
        ScheduleParent scheduleParent= modelMapper.map(scheduleParentDTO, ScheduleParent.class);
        ScheduleParent savedSchedules = scheduleDetailRepository.save(scheduleParent);
        ScheduleHistoryParent scheduleHistoryParent = modelMapper.map(scheduleParentDTO, ScheduleHistoryParent.class);
        scheduleHistoryParent.setScheduleParent(savedSchedules);
        scheduleHistoryParent.setIdScheHistoryParent(savedSchedules.getId_schedule_parent());
        ScheduleHistoryParent savedSchedulesHistory = scheduleHistoryParentRepository.save(scheduleHistoryParent);
        return  modelMapper.map(savedSchedules, ScheduleParentDTO.class);
    }



//    @Override
//    public ScheduleDTO saveScheduleAndVersion(ScheduleDTO scheduleDTO) {
//        Schedule schedule = modelMapper.map(scheduleDTO, Schedule.class);
//        Schedule savedSchedule = scheduleRepository.save(schedule);
//        return modelMapper.map(savedSchedule, ScheduleDTO.class);
//    }



    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})

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

        ResponseEntity<ScheduleParentDTO> responseEntitypar = restTemplate
                .getForEntity("http://schedule/schedule/parent/" + schedule.getId_schedule_parent(),
                        ScheduleParentDTO.class);

        ScheduleParentDTO scheduleParentDTO = responseEntitypar.getBody();

        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntityUser.getStatusCode());
        System.out.println(responseEntitypar.getStatusCode());

        responseDTO.setScheduleParent(scheduleParentDTO);
        responseDTO.setUser(userDTO);
        responseDTO.setSchedule(scheduleDTO);
        responseDTO.setVideo(videoDTO);

        return responseDTO;
    }


    @Override
    public List<ResponseDTO> getAllScheduleParentRest(Integer scheduleID) {
        List<ResponseDTO> responseList = new ArrayList<>();

        List<ScheduleParent> scheduleParents = scheduleDetailRepository.getAllScheduleParentRest(scheduleID);
        // The above line retrieves a list of ScheduleParent objects based on the provided scheduleID.
        // Make sure that the repository method returns the correct list of ScheduleParent objects.

        for (ScheduleParent scheduleParent : scheduleParents) {
            ScheduleParentDTO scheduleParentDTO = modelMapper.map(scheduleParent, ScheduleParentDTO.class);

            ResponseEntity<ScheduleDTO> responseEntity = restTemplate.getForEntity(
                    "http://schedule/schedule/" + scheduleParent.getId_schedule(), ScheduleDTO.class);
            ScheduleDTO scheduleDTO = responseEntity.getBody();

            ResponseEntity<UserDTO> responseEntity2 = restTemplate.getForEntity(
                    "http://user/user/" + scheduleParent.getId_user(), UserDTO.class);
            UserDTO userDTO = responseEntity2.getBody();

            System.out.println(responseEntity.getStatusCode());
            System.out.println(responseEntity2.getStatusCode());

            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setUser(userDTO);
            responseDTO.setSchedule(scheduleDTO);
            responseDTO.setScheduleParent(scheduleParentDTO);

            responseList.add(responseDTO);
        }

        return responseList;
    }

    @Override
    public List<ResponseDTO> getAllScheduleParentAllRest() {
        List<ResponseDTO> responseList = new ArrayList<>();
        List<ScheduleParent> scheduleParents = (List<ScheduleParent>) scheduleDetailRepository.getAllScheduleParentAllRest();
        List<Schedule> schedules = scheduleRepository.findAll();
        for (ScheduleParent scheduleParent : scheduleParents) {
            ScheduleParentDTO scheduleParentDTO = modelMapper.map(scheduleParent, ScheduleParentDTO.class);

            ResponseEntity<ScheduleDTO> responseEntity = restTemplate.getForEntity(
                    "http://schedule/schedule/" + scheduleParent.getId_schedule(), ScheduleDTO.class);
            ScheduleDTO scheduleDTO = responseEntity.getBody();

            ResponseEntity<UserDTO> responseEntity2 = restTemplate.getForEntity(
                    "http://user/user/" + scheduleParent.getId_user(), UserDTO.class);
            UserDTO userDTO = responseEntity2.getBody();


            System.out.println(responseEntity.getStatusCode());
            System.out.println(responseEntity2.getStatusCode());

            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setUser(userDTO);
            responseDTO.setSchedule(scheduleDTO);
            responseDTO.setScheduleParent(scheduleParentDTO);

            responseList.add(responseDTO);
        }

        return responseList;
    }




//    public List<ScheduleDTO> findAll() {
//        List<Schedule> schedules = scheduleRepository.findAll();
//        return schedules.stream().map(schedule -> modelMapper.map(schedule, ScheduleDTO.class)).collect(Collectors.toList());
//    }

    @Override
    public List<ResponseDTO> getAllScheduleList(Integer scheduleID) {
        List<ResponseDTO> responseList = new ArrayList<>();

        List<Schedule> schedules = (List<Schedule>) scheduleRepository.getAllScheduleList(scheduleID);

        for (Schedule schedule : schedules) {
            ResponseDTO responseDTO = new ResponseDTO(); // Deklarasi dan inisialisasi objek ResponseDTO

            ScheduleDTO scheduleDTO = modelMapper.map(schedule, ScheduleDTO.class);

            ResponseEntity<VideoDTO> responseEntity = restTemplate
                    .getForEntity("http://video/video/" + schedule.getId_video(),
                            VideoDTO.class);

            VideoDTO videoDTO = responseEntity.getBody();

            ResponseEntity<UserDTO> responseEntityUser = restTemplate
                    .getForEntity("http://user/user/" + schedule.getId_user(),
                            UserDTO.class);

            UserDTO userDTO = responseEntityUser.getBody();

            ResponseEntity<ScheduleParentDTO> responseEntitypar = restTemplate
                    .getForEntity("http://schedule/schedule/parent/" + schedule.getId_schedule_parent(),
                            ScheduleParentDTO.class);

            ScheduleParentDTO scheduleParentDTO = responseEntitypar.getBody();

            System.out.println(responseEntity.getStatusCode());
            System.out.println(responseEntityUser.getStatusCode());
            System.out.println(responseEntitypar.getStatusCode());

            responseDTO.setScheduleParent(scheduleParentDTO);
            responseDTO.setUser(userDTO);
            responseDTO.setSchedule(scheduleDTO);
            responseDTO.setVideo(videoDTO);

            responseList.add(responseDTO);
        }

        return responseList;
    }


    @Override
    public List<ResponseDTO1> getAllscheduleParentByUser(Integer idUser) {
        List<ResponseDTO1> responseList = new ArrayList<>();

        List<ScheduleParent> scheduleParents = scheduleDetailRepository.getAllscheduleParentByUser(idUser);

        for (ScheduleParent scheduleParent : scheduleParents) {
            ResponseDTO1 responseDTO1 = new ResponseDTO1();

            ScheduleParentDTO scheduleParentDTO = modelMapper.map(scheduleParent, ScheduleParentDTO.class);

            ResponseEntity<UserDTO> responseEntityUser = restTemplate
                    .getForEntity("http://user/user/" + scheduleParent.getId_user(),
                            UserDTO.class);

            UserDTO userDTO = responseEntityUser.getBody();

            System.out.println(responseEntityUser.getStatusCode());

//            Integer scheduleID = scheduleParent.getId_schedule();
//            Schedule schedule = scheduleRepository.findById(scheduleID).orElse(null);
//            ScheduleDTO scheduleDTO = modelMapper.map(schedule, ScheduleDTO.class);

            responseDTO1.setUser(userDTO);
            responseDTO1.setScheduleParent(scheduleParentDTO);
//            responseDTO1.setSchedule(scheduleDTO); // Set the scheduleDTO
            responseList.add(responseDTO1);
        }

        return responseList;
    }

    @Override
    public List<ResponseDTOV> getVideoById(Integer userID) {
        List<ResponseDTOV> responseList = new ArrayList<>();

        List<Schedule> scheduleList = (List<Schedule>) scheduleRepository.getVideoById(userID);

        for (Schedule schedule : scheduleList) {
            ScheduleDTO scheduleDTO = modelMapper.map(schedule, ScheduleDTO.class);

            ResponseEntity<UserDTO> responseEntity = restTemplate.getForEntity(
                    "http://user/user/" + schedule.getId_user(),
                    UserDTO.class
            );
            ResponseEntity<VideoDTO> responseEntity1 = restTemplate.getForEntity(
                    "http://video/video/" + schedule.getId_video(),
                    VideoDTO.class
            );

            UserDTO userDTO = responseEntity.getBody();
            VideoDTO videoDTO = responseEntity1.getBody();

            System.out.println(responseEntity.getStatusCode());
            System.out.println(responseEntity1.getStatusCode());

            ResponseDTOV responseDTOV = new ResponseDTOV();
            responseDTOV.setUser(userDTO);
            responseDTOV.setSchedule(scheduleDTO);
            responseDTOV.setVideo(videoDTO);

            responseList.add(responseDTOV);
        }

        return responseList;
    }

    @Override
    public ScheduleParentDTO createSchedule(ScheduleParentDTO scheduleParentDTO) {
        ScheduleParent scheduleParent = modelMapper.map(scheduleParentDTO, ScheduleParent.class);
        ScheduleParent createSchedule = scheduleDetailRepository.save(scheduleParent);
        return modelMapper.map(createSchedule, ScheduleParentDTO.class);


    }

    @Override
    public List<ScheduleParentDTO> getAllscheduleParent() {
        List<ScheduleParent> scheduleParents = scheduleDetailRepository.findAll();
        return scheduleParents.stream().map(scheduleParent -> modelMapper.map(scheduleParent, ScheduleParentDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ScheduleParentDTO getScheduleParentDetailById(Integer scheduleID) {
        ScheduleParent scheduleParent = scheduleDetailRepository.findById(scheduleID).orElseThrow(() -> new RuntimeException("scheudle not found with id: " + scheduleID));
        return modelMapper.map(scheduleParent, ScheduleParentDTO.class);
    }


    @Override
    public ScheduleParentDTO updateScheduleParentById(int scheduleID, ScheduleParentDTO scheduleParentDTO) {
        ScheduleParent existing = scheduleDetailRepository.findById(scheduleID).orElseThrow(() -> new IllegalArgumentException("Detail not found by id : " + scheduleParentDTO.getId_schedule_parent()));
        existing.setTitle(scheduleParentDTO.getTitle());
        ScheduleParent updatedSchedule = scheduleDetailRepository.save(existing);
        return modelMapper.map(updatedSchedule, ScheduleParentDTO.class);
    }

    @Override
    public void deleteScheduleParentById(Integer scheduleID) {
        scheduleDetailRepository.deleteById(scheduleID);

    }

    @Override
    public ScheduleDTO updateSchedule(int scheduleID, ScheduleDTO scheduleDTO) {
        Schedule existing = scheduleRepository.findById(scheduleID)
                .orElseThrow(() -> new IllegalArgumentException("Detail not found by id: " + scheduleID));


        existing.setTitle(scheduleDTO.getTitle());
        existing.setDate(scheduleDTO.getDate());
        existing.setId_user(scheduleDTO.getId_user());
        existing.setId_video(scheduleDTO.getId_video());
        //existing.setVersion(scheduleDTO.getVersion());

        Schedule updatedSchedule = scheduleRepository.save(existing);

        ScheduleHistory scheduleHistory = new ScheduleHistory();
        scheduleHistory.setId_schedule(updatedSchedule.getId_schedule());
        scheduleHistory.setTitle(updatedSchedule.getTitle());
        scheduleHistory.setDate(updatedSchedule.getDate());
        scheduleHistory.setId_user(updatedSchedule.getId_user());
        scheduleHistory.setId_video(updatedSchedule.getId_video());
//        scheduleHistory.setVersion(updatedSchedule.getVersion());
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

    // Update Biasa
//    @Override
//    public ScheduleDTO updateSchedule(int sheduleID, ScheduleDTO scheduleDTO) {
//        Schedule existing = scheduleRepository.findById(sheduleID).orElseThrow(() -> new IllegalArgumentException("Detail not found by id : " + scheduleDTO.getId_schedule()));
//        existing.setTitle(scheduleDTO.getTitle());
//        existing.setDate(scheduleDTO.getDate());
//        Schedule updatedSchedule = scheduleRepository.save(existing);
//        return modelMapper.map(updatedSchedule, ScheduleDTO.class);
//    }


    //    @Override
//    public ScheduleDTO saveScheduleAndVersion(ScheduleDTO scheduleDTO) {
//        Schedule schedule = modelMapper.map(scheduleDTO, Schedule.class);
//        Schedule savedSchedule = scheduleRepository.save(schedule);
//        return modelMapper.map(savedSchedule, ScheduleDTO.class);
//    }

//    @Override
//    public List<ResponseDTO> getAllScheduleParentRes(Integer scheduleID) {
//        List<ResponseDTO> responseDTOList = new ArrayList<>();
//
//        ScheduleDetail scheduleDetail = scheduleDetailRepository.findById(scheduleID).orElse(null);
//        if (scheduleDetail == null) {
//            // Handle the case when scheduleDetail is not found for the provided scheduleID
//            return responseDTOList;
//        }
//
//        ScheduleDetailDTO scheduleDetailDTO = modelMapper.map(scheduleDetail, ScheduleDetailDTO.class);
//
//        ResponseEntity<ScheduleDTO> responseEntity = restTemplate.getForEntity(
//                "http://schedule/schedule/" + scheduleDetail.getId_schedule(), ScheduleDTO.class);
//        ScheduleDTO scheduleDTO = responseEntity.getBody();
//
//        ResponseEntity<UserDTO> responseEntity2 = restTemplate.getForEntity(
//                "http://user/user/" + scheduleDetail.getId_user(), UserDTO.class);
//        UserDTO userDTO = responseEntity2.getBody();
//
//        System.out.println(responseEntity.getBody());
//        System.out.println(responseEntity2.getBody());
//
//        ResponseDTO responseDTO = new ResponseDTO();
//        responseDTO.setUser(userDTO);
//        responseDTO.setSchedule(scheduleDTO);
//
//
//        responseDTOList.add(responseDTO);
//
//        return responseDTOList;
//    }
}
