package com.sugadev.scheduleservice.service;

import com.sugadev.scheduleservice.dto.*;
import com.sugadev.scheduleservice.model.ScheduleChild;
import com.sugadev.scheduleservice.model.ScheduleHistoryParent;
import com.sugadev.scheduleservice.model.ScheduleParent;
import com.sugadev.scheduleservice.model.ScheduleHistoryChild;
import com.sugadev.scheduleservice.repository.ScheduleParentRepository;
import com.sugadev.scheduleservice.repository.ScheduleHistoryParentRepository;
import com.sugadev.scheduleservice.repository.ScheduleHistoryChildRepository;
import com.sugadev.scheduleservice.repository.ScheduleChildRepository;
import jakarta.annotation.security.RolesAllowed;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private ScheduleChildRepository scheduleChildRepository;
    @Autowired
    private ScheduleHistoryChildRepository scheduleHistoryChildRepository;
    @Autowired
    private ScheduleParentRepository scheduleParentRepository;
    @Autowired
    private ScheduleHistoryParentRepository scheduleHistoryParentRepository;

    @Autowired
    ModelMapper modelMapper;
    RestTemplate restTemplate;

    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    public ScheduleChildDTO saveScheduleAndVersion(ScheduleChildDTO scheduleChildDTO) {
        ScheduleChild schedule = modelMapper.map(scheduleChildDTO, ScheduleChild.class);
        ScheduleChild savedSchedule = scheduleChildRepository.save(schedule);
        ScheduleHistoryChild scheduleHistoryChild = modelMapper.map(scheduleChildDTO, ScheduleHistoryChild.class);
        scheduleHistoryChild.setScheduleChild(savedSchedule);
        scheduleHistoryChild.setIdScheHistoryChild(savedSchedule.getId_schedule_child());
        ScheduleHistoryChild savedScheduleHistoryChild = scheduleHistoryChildRepository.save(scheduleHistoryChild);
        return modelMapper.map(savedSchedule, ScheduleChildDTO.class);
    }
    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    @Override
    public ScheduleParentDTO saveSchedulesAndVersionParent(ScheduleParentDTO scheduleParentDTO) {
        ScheduleParent scheduleParent= modelMapper.map(scheduleParentDTO, ScheduleParent.class);
        ScheduleParent savedSchedules = scheduleParentRepository.save(scheduleParent);
        ScheduleHistoryParent scheduleHistoryParent = modelMapper.map(scheduleParentDTO, ScheduleHistoryParent.class);
        scheduleHistoryParent.setScheduleParent(savedSchedules);
        scheduleHistoryParent.setIdScheHistoryParent(savedSchedules.getId_schedule_parent());
        ScheduleHistoryParent savedSchedulesHistory = scheduleHistoryParentRepository.save(scheduleHistoryParent);
        return  modelMapper.map(savedSchedules, ScheduleParentDTO.class);
    }
    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})

    @Override
    public List<ScheduleChildDTO> findAll() {
        List<ScheduleChild> schedules = scheduleChildRepository.findAll();
        return schedules.stream().map(schedule -> modelMapper.map(schedule, ScheduleChildDTO.class)).
                collect(Collectors.toList());
    }
    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    @Override
    public ResponseScheduleChildParentVideoDTO getScheduleById(Integer sheduleID) {
        ResponseScheduleChildParentVideoDTO responseScheduleChildParentVideoDTO = new ResponseScheduleChildParentVideoDTO();
        ScheduleChild schedule = scheduleChildRepository.findById(sheduleID).get();
        ScheduleChildDTO scheduleChildDTO = modelMapper.map(schedule, ScheduleChildDTO.class);

        HttpHeaders headers = new HttpHeaders();
        UserAuthDTO user = (UserAuthDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        headers.setBearerAuth(user.getToken());

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        ResponseEntity<VideoDTO> responseEntity = restTemplate
                .exchange("http://video/video/" + schedule.getId_video(),
                        HttpMethod.GET, entity, VideoDTO.class);

        VideoDTO videoDTO = responseEntity.getBody();

        ResponseEntity<ScheduleParentDTO> responseEntitypar = restTemplate
                .exchange("http://schedule/schedule/parent/" + schedule.getId_schedule_parent(),
                        HttpMethod.GET, entity, ScheduleParentDTO.class);

        ScheduleParentDTO scheduleParentDTO = responseEntitypar.getBody();

        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntitypar.getStatusCode());

        responseScheduleChildParentVideoDTO.setScheduleParent(scheduleParentDTO);
        responseScheduleChildParentVideoDTO.setSchedule(scheduleChildDTO);
        responseScheduleChildParentVideoDTO.setVideo(videoDTO);

        return responseScheduleChildParentVideoDTO;
    }
    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    @Override
    public List<ResponseScheduleChildParentVideoDTO> getAllScheduleList(Integer scheduleID) {
        List<ResponseScheduleChildParentVideoDTO> responseList = new ArrayList<>();

        List<ScheduleChild> schedules = (List<ScheduleChild>) scheduleChildRepository.getAllScheduleList(scheduleID);

        for (ScheduleChild schedule : schedules) {
            ResponseScheduleChildParentVideoDTO responseScheduleChildParentVideoDTO = new ResponseScheduleChildParentVideoDTO();

            ScheduleChildDTO scheduleChildDTO = modelMapper.map(schedule, ScheduleChildDTO.class);

            HttpHeaders headers = new HttpHeaders();
            UserAuthDTO user = (UserAuthDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            headers.setBearerAuth(user.getToken());
            HttpEntity<String> entity = new HttpEntity<>("body", headers);

            ResponseEntity<VideoDTO> responseEntity = restTemplate
                    .exchange("http://video/video/" + schedule.getId_video(),
                            HttpMethod.GET, entity, VideoDTO.class);

            VideoDTO videoDTO = responseEntity.getBody();

            ResponseEntity<ScheduleParentDTO> responseEntitypar = restTemplate
                    .exchange("http://schedule/schedule/parent/" + schedule.getId_schedule_parent(),
                            HttpMethod.GET, entity, ScheduleParentDTO.class);

            ScheduleParentDTO scheduleParentDTO = responseEntitypar.getBody();

            System.out.println(responseEntity.getStatusCode());
            System.out.println(responseEntitypar.getStatusCode());

            responseScheduleChildParentVideoDTO.setScheduleParent(scheduleParentDTO);
            responseScheduleChildParentVideoDTO.setSchedule(scheduleChildDTO);
            responseScheduleChildParentVideoDTO.setVideo(videoDTO);

            responseList.add(responseScheduleChildParentVideoDTO);
        }

        return responseList;
    }
    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    @Override
    public List<ResponseScheduleParentAndUserDTO> getAllscheduleParentByUser(Integer idUser) {
        List<ResponseScheduleParentAndUserDTO> responseList = new ArrayList<>();
        List<ScheduleParent> scheduleParents = scheduleParentRepository.getAllscheduleParentByUser(idUser);
        for (ScheduleParent scheduleParent : scheduleParents) {
            ResponseScheduleParentAndUserDTO responseScheduleParentAndUserDTO = new ResponseScheduleParentAndUserDTO();
            ScheduleParentDTO scheduleParentDTO = modelMapper.map(scheduleParent, ScheduleParentDTO.class);

            HttpHeaders headers = new HttpHeaders();
            UserAuthDTO user = (UserAuthDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            headers.setBearerAuth(user.getToken());
            HttpEntity<String> entity = new HttpEntity<>("body", headers);

            ResponseEntity<UserDTO> responseEntityUser = restTemplate.exchange("http://user/user/" +
                            scheduleParent.getId_user(),
                    HttpMethod.GET, entity, UserDTO.class);

            UserDTO userDTO = responseEntityUser.getBody();
            System.out.println(responseEntityUser.getStatusCode());

            responseScheduleParentAndUserDTO.setUser(userDTO);
            responseScheduleParentAndUserDTO.setScheduleParent(scheduleParentDTO);
            responseList.add(responseScheduleParentAndUserDTO);
        }

        return responseList;
    }
    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    public List<ScheduleHistoryParentDTO> getAllScheduleParentHistoryByScheduleParentId(Integer idScheduleParent) {
        List<ScheduleHistoryParent> schedules = scheduleParentRepository.
                getAllScheduleParentHistoryByScheduleParentId(idScheduleParent);
        return schedules.stream().map(schedule -> modelMapper.map(schedule, ScheduleHistoryParentDTO.
                class)).collect(Collectors.toList());
    }

//    @Override
//    public List<ScheduleHistoryParentDTO> getallScheduleParentHistoryByUser(Integer id) {
//        return null;
//    }

    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    public List<ScheduleHistoryParentDTO> getallScheduleParentHistoryByUser(Integer id) {
        List<ScheduleHistoryParent> schedules = scheduleParentRepository.
                getallScheduleParentHistoryByUser(id);
        return schedules.stream().map(schedule -> modelMapper.map(schedule, ScheduleHistoryParentDTO.
                class)).collect(Collectors.toList());
    }

    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    @Override
    public ScheduleHistoryParentDTO getScheduleParentHistoryDetailByScheduleParentHistoryId(Integer id) {
        ScheduleHistoryParent scheduleHistoryParent = scheduleParentRepository.
                getScheduleParentHistoryDetailByScheduleParentHistoryId(id);
        return modelMapper.map(scheduleHistoryParent, ScheduleHistoryParentDTO.class);
    }


    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    @Override
    public ScheduleHistoryChildDTO getScheduldeChildHistoryDetailByScheduleChildHistoryId(Integer id) {
        ScheduleHistoryChild scheduleHistoryChild = scheduleHistoryChildRepository.
                getScheduldeChildHistoryDetailByScheduleChildHistoryId(id);
        return modelMapper.map(scheduleHistoryChild, ScheduleHistoryChildDTO.class);
    }

    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    @Override
    public List<ResponseScheduleChildUserVideoDTO> getVideoById(Integer userID) {
        List<ResponseScheduleChildUserVideoDTO> responseList = new ArrayList<>();

        List<ScheduleChild> scheduleList = (List<ScheduleChild>) scheduleChildRepository.getVideoById(userID);

        for (ScheduleChild schedule : scheduleList) {
            ScheduleChildDTO scheduleChildDTO = modelMapper.map(schedule, ScheduleChildDTO.class);


            ResponseEntity<VideoDTO> responseEntity1 = restTemplate.getForEntity(
                    "http://video/video/" + schedule.getId_video(),
                    VideoDTO.class
            );

            VideoDTO videoDTO = responseEntity1.getBody();

            System.out.println(responseEntity1.getStatusCode());

            ResponseScheduleChildUserVideoDTO responseScheduleChildUserVideoDTO = new ResponseScheduleChildUserVideoDTO();
            responseScheduleChildUserVideoDTO.setSchedule(scheduleChildDTO);
            responseScheduleChildUserVideoDTO.setVideo(videoDTO);

            responseList.add(responseScheduleChildUserVideoDTO);
        }

        return responseList;
    }
    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    @Override
    public ScheduleParentDTO createSchedule(ScheduleParentDTO scheduleParentDTO) {
        ScheduleParent scheduleParent = modelMapper.map(scheduleParentDTO, ScheduleParent.class);
        ScheduleParent createSchedule = scheduleParentRepository.save(scheduleParent);
        return modelMapper.map(createSchedule, ScheduleParentDTO.class);


    }
    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    @Override
    public List<ScheduleParentDTO> getAllscheduleParent() {
        List<ScheduleParent> scheduleParents = scheduleParentRepository.findAll();
        return scheduleParents.stream().map(scheduleParent -> modelMapper.map(scheduleParent,
                ScheduleParentDTO.class)).collect(Collectors.toList());
    }
    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    @Override
    public ScheduleParentDTO getScheduleParentDetailById(Integer scheduleID) {
        ScheduleParent scheduleParent = scheduleParentRepository.findById(scheduleID).orElseThrow(() ->
                new RuntimeException("scheudle not found with id: " + scheduleID));
        return modelMapper.map(scheduleParent, ScheduleParentDTO.class);
    }
    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    @Override
    public ScheduleParentDTO updateScheduleParentById(int scheduleID, ScheduleParentDTO scheduleParentDTO) {
        ScheduleParent existing = scheduleParentRepository.findById(scheduleID).orElseThrow(() ->
                new IllegalArgumentException("Detail not found by id : " + scheduleParentDTO.getId_schedule_parent()));
        existing.setTitle(scheduleParentDTO.getTitle());
        existing.setDescription(scheduleParentDTO.getDescription());
        ScheduleParent updatedSchedule = scheduleParentRepository.save(existing);

        ScheduleHistoryParent scheduleHistoryParent =  new ScheduleHistoryParent();
        scheduleHistoryParent.setTitle(updatedSchedule.getTitle());
        scheduleHistoryParent.setDate(updatedSchedule.getDate());
        scheduleHistoryParent.setId_user(updatedSchedule.getId_user());
        scheduleHistoryParent.setDescription(updatedSchedule.getDescription());
        scheduleHistoryParent.setVersion(updatedSchedule.getVersion());
        scheduleHistoryParent.setScheduleParent(updatedSchedule);

        scheduleHistoryParentRepository.save(scheduleHistoryParent);

        return modelMapper.map(updatedSchedule, ScheduleParentDTO.class);
    }
    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    @Override
    public void deleteScheduleParentById(Integer scheduleID) {
        ScheduleParent scheduleParent = scheduleParentRepository.findById(scheduleID)
                .orElseThrow(() -> new EntityNotFoundException("ScheduleParent not found with id: " + scheduleID));

        for (ScheduleHistoryParent child : scheduleParent.getChildren()) {
            child.setScheduleParent(null);
        }
        scheduleParent.getChildren().clear();

        scheduleParentRepository.save(scheduleParent);

        scheduleParentRepository.delete(scheduleParent);

    }
    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    @Override
    public ScheduleChildDTO updateSchedule(int scheduleID, ScheduleChildDTO scheduleChildDTO) {
        ScheduleChild existing = scheduleChildRepository.findById(scheduleID)
                .orElseThrow(() -> new IllegalArgumentException("Detail not found by id: " + scheduleID));


//        existing.setTitle(scheduleChildDTO.getTitle());
//        existing.setDate(scheduleChildDTO.getDate());
        existing.setId_video(scheduleChildDTO.getId_video());
        existing.setVersion(scheduleChildDTO.getVersion());

        ScheduleChild updatedSchedule = scheduleChildRepository.save(existing);

        ScheduleHistoryChild scheduleHistoryChild = new ScheduleHistoryChild();
//        scheduleHistoryChild.setId_schedule_child(updatedSchedule.getId_schedule_child());
//        scheduleHistoryChild.setTitle(updatedSchedule.getTitle());
//        scheduleHistoryChild.setDate(updatedSchedule.getDate());
        scheduleHistoryChild.setId_video(updatedSchedule.getId_video());
        scheduleHistoryChild.setVersion(updatedSchedule.getVersion());
        scheduleHistoryChild.setScheduleChild(updatedSchedule);

        scheduleHistoryChildRepository.save(scheduleHistoryChild);

        return modelMapper.map(updatedSchedule, ScheduleChildDTO.class);
    }
    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    @Override
    public List<ScheduleChildDTO> getPrevVersion(Integer scheduleID) {
        List<ScheduleHistoryChild> schedules = scheduleChildRepository.getProductVersionHistory(scheduleID);
        return schedules.stream().map(schedule -> modelMapper.map(schedule, ScheduleChildDTO.class))
                .collect(Collectors.toList());
    }
    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    @Override
    public ScheduleChildDTO getPrevVersionDetail(Integer scheduleID) {
        ScheduleHistoryChild scheduleHistoryChild = scheduleHistoryChildRepository.findById(scheduleID)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + scheduleID));
        return modelMapper.map(scheduleHistoryChild, ScheduleChildDTO.class);
    }

    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    @Override
    public void deleteScheduleChildById(Integer scheduleID) {
        ScheduleChild scheduleChild = scheduleChildRepository.findById(scheduleID)
                .orElseThrow(() -> new EntityNotFoundException("ScheduleParent not found with id: " + scheduleID));

        for (ScheduleHistoryChild child : scheduleChild.getChildren()) {
            child.setScheduleChild(null);
        }
        scheduleChild.getChildren().clear();

        scheduleChildRepository.save(scheduleChild);

        scheduleChildRepository.delete(scheduleChild);

    }




//    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
//    public ScheduleHistoryParentDTO getScheduleParentHistoryById(Integer id) {
//        ScheduleHistoryParent scheduleHistoryParent = scheduleHistoryParentRepository.getScheduleParentHistoryDetailByScheduleParentHistoryId(id)
//                .orElseThrow(()
//                -> new RuntimeException("scheudle not found with id: " + id));
//        return modelMapper.map(scheduleHistoryParent, ScheduleHistoryParentDTO.class);
//
//    }
//    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
//    @Override
//    public List<ResponseDTO> getAllScheduleParentAllRest() {
//        List<ResponseDTO> responseList = new ArrayList<>();
//        List<ScheduleParent> scheduleParents = (List<ScheduleParent>) scheduleDetailRepository.getAllScheduleParentAllRest();
//        List<Schedule> schedules = scheduleRepository.findAll();
//        for (ScheduleParent scheduleParent : scheduleParents) {
//            ScheduleParentDTO scheduleParentDTO = modelMapper.map(scheduleParent, ScheduleParentDTO.class);
//
////            ResponseEntity<ScheduleDTO> responseEntity = restTemplate.getForEntity(
////                    "http://schedule/schedule/" + scheduleParent.getId_schedule(), ScheduleDTO.class);
////            ScheduleDTO scheduleDTO = responseEntity.getBody();
//
//            ResponseEntity<UserDTO> responseEntity2 = restTemplate.getForEntity(
//                    "http://user/user/" + scheduleParent.getId_user(), UserDTO.class);
//            UserDTO userDTO = responseEntity2.getBody();
//
//
////            System.out.println(responseEntity.getStatusCode());
//            System.out.println(responseEntity2.getStatusCode());
//
//            ResponseDTO responseDTO = new ResponseDTO();
//            responseDTO.setUser(userDTO);
////            responseDTO.setSchedule(scheduleDTO);
//            responseDTO.setScheduleParent(scheduleParentDTO);
//
//            responseList.add(responseDTO);
//        }
//
//        return responseList;
//    }

    //    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
//    @Override
//    public List<ResponseDTO> getAllScheduleParentRest(Integer scheduleID) {
//        List<ResponseDTO> responseList = new ArrayList<>();
//
//        List<ScheduleParent> scheduleParents = scheduleDetailRepository.getAllScheduleParentRest(scheduleID);
//        // The above line retrieves a list of ScheduleParent objects based on the provided scheduleID.
//        // Make sure that the repository method returns the correct list of ScheduleParent objects.
//
//        for (ScheduleParent scheduleParent : scheduleParents) {
//            ScheduleParentDTO scheduleParentDTO = modelMapper.map(scheduleParent, ScheduleParentDTO.class);
//
//            ResponseEntity<ScheduleDTO> responseEntity = restTemplate.getForEntity(
//                    "http://schedule/schedule/" + scheduleParent.getId_schedule(), ScheduleDTO.class);
//            ScheduleDTO scheduleDTO = responseEntity.getBody();
//
//            ResponseEntity<UserDTO> responseEntity2 = restTemplate.getForEntity(
//                    "http://user/user/" + scheduleParent.getId_user(), UserDTO.class);
//
//            UserDTO userDTO = responseEntity2.getBody();
//
//            System.out.println(responseEntity.getStatusCode());
//            System.out.println(responseEntity2.getStatusCode());
//
//            ResponseDTO responseDTO = new ResponseDTO();
//            responseDTO.setUser(userDTO);
//            responseDTO.setSchedule(scheduleDTO);
//            responseDTO.setScheduleParent(scheduleParentDTO);
//
//            responseList.add(responseDTO);
//        }
//
//        return responseList;
//    }

    // Update Biasa
//    @Override
//    public ScheduleDTO updateSchedule(int sheduleID, ScheduleDTO scheduleDTO) {
//        Schedule existing = scheduleRepository.findById(sheduleID).orElseThrow(() ->
//        new IllegalArgumentException("Detail not found by id : " + scheduleDTO.getId_schedule()));
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
