package com.sugadev.historyservice.Services;


import com.sugadev.historyservice.Dto.*;

import com.sugadev.historyservice.Model.History;
import com.sugadev.historyservice.Model.HistoryChild;
import com.sugadev.historyservice.Model.HistoryParent;
import com.sugadev.historyservice.Repository.HistoryParentRepository;
import com.sugadev.historyservice.Repository.HistoryChildRepository;
import com.sugadev.historyservice.Repository.HistoryRepository;
import jakarta.annotation.security.RolesAllowed;
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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j

public class HistoryServiceImpl implements HistoryService {

    @Autowired
    HistoryChildRepository historyChildRepository;
    @Autowired
    HistoryParentRepository historyParentRepository;

    @Autowired
    HistoryRepository historyRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    RestTemplate restTemplate;

    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public HistoryChildDTO createHistoryChild(HistoryChildDTO historyChildDto) {
        HistoryChild historyChild = modelMapper.map(historyChildDto, HistoryChild.class);
        HistoryChild savedHistoryChild = historyChildRepository.save(historyChild);
        return modelMapper.map(savedHistoryChild, HistoryChildDTO.class);

    }
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public HistoryParentDTO createHistoryParent(HistoryParentDTO historyParentDTO) {
        HistoryParent historyParent = modelMapper.map(historyParentDTO, HistoryParent.class);
        HistoryParent savedHistoryParent = historyParentRepository.save(historyParent);
        return modelMapper.map(savedHistoryParent, HistoryParentDTO.class);
    }

    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public List<ResponseScheduleAndHistoryParentDTO> getHistoryParentByUserId(Integer id) {
        List<ResponseScheduleAndHistoryParentDTO> responseScheduleAndHistoryParentDTOList = new ArrayList<>();
        List<HistoryParent> historyParents = historyParentRepository.getHistoryParentByUserId(id);
        for (HistoryParent historyParent : historyParents) {
            ResponseScheduleAndHistoryParentDTO responseScheduleAndHistoryParentDTO = new ResponseScheduleAndHistoryParentDTO();
            HistoryParentDTO historyParentDTO = modelMapper.map(historyParent, HistoryParentDTO.class);

            HttpHeaders headers = new HttpHeaders();
            UserAuthDTO userAuthDTO = (UserAuthDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            headers.setBearerAuth(userAuthDTO.getToken());
            HttpEntity<String> entity = new HttpEntity<>("body", headers);

            ResponseEntity<UserDTO> responseEntityUser = restTemplate.exchange
                    ("http://user/user/" + historyParent.getIdUser(),
                            HttpMethod.GET, entity, UserDTO.class);
            UserDTO userDTO = responseEntityUser.getBody();
            System.out.println(responseEntityUser.getStatusCode());

            ResponseEntity<ScheduleHistoryParentDTO> responseEntity = restTemplate.exchange
                    ("http://schedule/schedule/parent/history/" + historyParent.getIdScheHistoryParent(),
                            HttpMethod.GET, entity, ScheduleHistoryParentDTO.class);
            ScheduleHistoryParentDTO scheduleHistoryParentDTO = responseEntity.getBody();
            System.out.println(responseEntity.getStatusCode());

            responseScheduleAndHistoryParentDTO.setScheduleHistoryParent(scheduleHistoryParentDTO);
            responseScheduleAndHistoryParentDTO.setUser(userDTO);
            responseScheduleAndHistoryParentDTO.setHistoryParent(historyParentDTO);
            responseScheduleAndHistoryParentDTOList.add(responseScheduleAndHistoryParentDTO);
        }
        return responseScheduleAndHistoryParentDTOList;
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public List<HistoryChildDTO> getAllHistory() {
        List<HistoryChild> histories = historyChildRepository.findAll();
        return histories.stream()
                .map(historyChild -> modelMapper.map(historyChild, HistoryChildDTO.class))
                .collect(Collectors.toList());
    }








    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public List<ResponseScheduleAndHistoryChildDTO> getHistoryChildByHistoryParentId(Integer id) {
        List<ResponseScheduleAndHistoryChildDTO> responseScheduleAndHistoryChildDTOList = new ArrayList<>();
        List<HistoryChild> historyParents = historyChildRepository.getHistoryChildByHistoryParentId(id);
        for (HistoryChild historyChild : historyParents) {
            ResponseScheduleAndHistoryChildDTO responseScheduleAndHistoryChildDTO = new ResponseScheduleAndHistoryChildDTO();
            HistoryChildDTO historyChildDTO = modelMapper.map(historyChild, HistoryChildDTO.class);

            HttpHeaders headers = new HttpHeaders();
            UserAuthDTO userAuthDTO = (UserAuthDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            headers.setBearerAuth(userAuthDTO.getToken());
            HttpEntity<String> entity = new HttpEntity<>("body", headers);

            ResponseEntity<ScheduleHistoryChildDTO> responseEntity = restTemplate.exchange
                    ("http://schedule/schedule/child/history/" + historyChild.getIdScheHistoryChild(),
                    HttpMethod.GET, entity, ScheduleHistoryChildDTO.class);
            ScheduleHistoryChildDTO scheduleHistoryChildDTO = responseEntity.getBody();
            System.out.println(responseEntity.getStatusCode());

            responseScheduleAndHistoryChildDTO.setHistoryChildDTO(historyChildDTO);
            responseScheduleAndHistoryChildDTO.setScheduleHistoryChildDTO(scheduleHistoryChildDTO);
            responseScheduleAndHistoryChildDTOList.add(responseScheduleAndHistoryChildDTO);

        }
        return responseScheduleAndHistoryChildDTOList;
    }
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @Override
    public void deleteAllByIdUser(Integer id) {
        historyParentRepository.deleteAllById(Collections.singleton(id));
    }

//    @Override
//    public HistoryDTO addBiasa(HistoryDTO historyDTO) {
//        return null;
//    }


    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public HistoryDTO addBiasa(HistoryDTO historyDTO) {
        History history = modelMapper.map(historyDTO, History.class);
        History savedHistory = historyRepository.save(history);
        return modelMapper.map(savedHistory, HistoryDTO.class);

    }

    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    public List<HistoryDTO> getAllBiasaUser(Integer id) {
        List<History> culinaries = historyRepository.getAllBiasaUser(id);
        return culinaries.stream()
                .map(culinary -> modelMapper.map(culinary, HistoryDTO.class))
                .collect(Collectors.toList());
    }


        @RolesAllowed({"ROLE_ADMIN"})
        @Override
        public void deleteBiasa(Integer id)
        {historyRepository.deleteById(id);}


    }





//    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
//    @Override
//    public void deleteAllByUserId(Integer id) {
//        historyParentRepository.deleteAllById(Collections.singleton(id));
//    }
//
//    @Override
//    public void deleteHistoryParent(Integer id) {
//    }
//    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
//    public ResponseDTO getHistory(Integer historyId) {
//        ResponseDTO responseDTO = new ResponseDTO();
//        HistoryChild historyChild = historyChildRepository.findById(historyId).get();
//
//        HistoryChildDTO historyChildDto = modelMapper.map(historyChild, HistoryChildDTO.class);
//
//
//        ResponseEntity<UserDTO> responseEntity = restTemplate
//                .getForEntity("Http://user/user/" + historyChild.getIdUser(),
//                        UserDTO.class);
//
//        UserDTO userDTO = responseEntity.getBody();
//
//        ResponseEntity<ScheduleHistoryChild> responseEntity2 = restTemplate
//                .getForEntity("Http://schedule/schedule/version/" + historyChild.getIdScheduleHistory(),
//                        ScheduleHistoryChild.class);
//
//        ScheduleHistoryChild scheduleHistoryDTO = responseEntity2.getBody();
//
//        System.out.println(responseEntity.getStatusCode());
//
//        responseDTO.setUser(userDTO);
//        responseDTO.setHistory(historyChildDto);
//        responseDTO.setScheduleHistory(scheduleHistoryDTO);
//
//        return responseDTO;
//    }

//    @Override
//    public List<ResponseDTO> getAllHistoryByScheHistoryId(Integer id) {
//        List<ResponseDTO> responseList = new ArrayList<>();
//
//        List<HistoryChild> historyChildParents = historyRepository.getAllHistoryByScheHistoryId(id);
//
//        for (HistoryChild historyChild : historyChildParents) {
//            ResponseDTO responseDTO = new ResponseDTO();
//
//            HistoryDTO historyDTO = modelMapper.map(historyChild, HistoryDTO.class);
//
//            ResponseEntity<UserDTO> responseEntityUser = restTemplate
//                    .getForEntity("http://user/user/" + historyChild.getIdUser(),
//                            UserDTO.class);
//
//            UserDTO userDTO = responseEntityUser.getBody();
//
//            System.out.println(responseEntityUser.getStatusCode());
//
//            ResponseEntity<ScheduleHistoryDTO> responseEntitysch = restTemplate
//                    .getForEntity("http://schedule/schedule/sch/" + historyChild.getIdScheduleHistory(),
//                            ScheduleHistoryDTO.class);
//
//            ScheduleHistoryDTO scheduleHistoryDTO = responseEntitysch.getBody();
//
//            System.out.println(responseEntityUser.getStatusCode());
//
//
//
//            responseDTO.setHistory(historyDTO);
//            responseDTO.setUser(userDTO);
//            responseList.add(responseDTO);
//        }
//
//        return responseList;
//    }


//    @Override
//    public HistoryDto updateHistory(int id, HistoryDto historyDto) {
//        History existingHistory = historyRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Detail not found by id : " + id));
//
//        existingHistory.setIdHistory(historyDto.getIdHistory());
//        existingHistory.setIdUser(historyDto.getIdUser());
//        existingHistory.setTitle(historyDto.getTitle());
//        existingHistory.setStarttime(historyDto.getStarttime());
//        existingHistory.setEndtime(historyDto.getEndtime());
//        existingHistory.setBurncalories(historyDto.getBurncalories());
//        existingHistory.setLongitude(historyDto.getLongitude());
//        existingHistory.setLatitude(historyDto.getLatitude());
//        existingHistory.setJarak(historyDto.getJarak());
//
//        History updatedHistory = historyRepository.save(existingHistory);
//        return modelMapper.map(updatedHistory, HistoryDto.class);
//
//
//    }


