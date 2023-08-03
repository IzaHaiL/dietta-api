package com.sugadev.historyservice.Services;


import com.sugadev.historyservice.Dto.*;

import com.sugadev.historyservice.Model.History;
import com.sugadev.historyservice.Model.HistoryParent;
import com.sugadev.historyservice.Repository.HistoryParentRepository;
import com.sugadev.historyservice.Repository.HistoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j

public class HistoryServiceImpl implements HistoryService {

    HistoryRepository historyRepository;
    ModelMapper modelMapper;
    RestTemplate restTemplate;

    HistoryParentRepository historyParentRepository;

    @Override

    public HistoryDTO saveHistory(HistoryDTO historyDto) {
        History history = modelMapper.map(historyDto, History.class);
        History savedHistory = historyRepository.save(history);
        return modelMapper.map(savedHistory, HistoryDTO.class);

    }
    @Override
    public ResponseDTO getHistory(Integer historyId) {
        ResponseDTO responseDTO = new ResponseDTO();
        History history = historyRepository.findById(historyId).get();

        HistoryDTO historyDto = modelMapper.map(history, HistoryDTO.class);


        ResponseEntity<UserDTO> responseEntity = restTemplate
                .getForEntity("Http://user/user/" + history.getIdUser(),
                        UserDTO.class);

        UserDTO userDTO = responseEntity.getBody();

        ResponseEntity<ScheduleHistoryDTO> responseEntity2 = restTemplate
                .getForEntity("Http://schedule/schedule/version/" + history.getIdScheduleHistory(),
                        ScheduleHistoryDTO.class);

        ScheduleHistoryDTO scheduleHistoryDTO = responseEntity2.getBody();

        System.out.println(responseEntity.getStatusCode());

        responseDTO.setUser(userDTO);
        responseDTO.setHistory(historyDto);
        responseDTO.setScheduleHistory(scheduleHistoryDTO);

        return responseDTO;
    }




    @Override
    public List<HistoryDTO> getAllHistory() {
        List<History> histories = historyRepository.findAll();
        return histories.stream()
                .map(history -> modelMapper.map(history, HistoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteHistory(Integer historyId) {historyRepository.deleteById(historyId);}



    @Override
    public List<ResponseDTO> getHistoriesByUser(Integer id) {
        return null;
    }
//        List<ResponseDTO> responseList = new ArrayList<>();
//
//        List<History> historyList = (List<History>) historyRepository.getHistoriesByUser(id);
//
//        for (History history : historyList) {
//            HistoryDTO historyDTO = modelMapper.map(history, HistoryDTO.class);
//
//            ResponseEntity<UserDTO> responseEntity = restTemplate.getForEntity(
//                    "http://user/user/" + history.getIdUser(),
//                    UserDTO.class);
//
//            ResponseEntity<ScheduleHistoryDTO> responseEntity2 = restTemplate.getForEntity(
//                    "Http://schedule/schedule/sche/" + history.getIdScheduleHistory(),
//                    ScheduleHistoryDTO.class);
//
//
//            ScheduleHistoryDTO scheduleHistoryDTO = responseEntity2.getBody();
//            UserDTO userDTO = responseEntity.getBody();
//
//            System.out.println(responseEntity.getStatusCode());
//
//            ResponseDTO responseDTO = new ResponseDTO();
//            responseDTO.setUser(userDTO);
//            responseDTO.setHistory(historyDTO);
//            responseDTO.setScheduleHistory(scheduleHistoryDTO);
//            responseList.add(responseDTO);
//        }
//
//        return responseList;
//    }


    @Override
    public List<ResponseDTO1> getAllHistoryParentByuser(Integer idUser) {
        List<ResponseDTO1> responseList = new ArrayList<>();

        List<HistoryParent> historyParents = historyParentRepository.getAllHistoryParentByuser(idUser);

        for (HistoryParent historyParent : historyParents) {
            ResponseDTO1 responseDTO1 = new ResponseDTO1();

            HistoryParentDTO historyParentDTO = modelMapper.map(historyParent, HistoryParentDTO.class);

            ResponseEntity<UserDTO> responseEntityUser = restTemplate
                    .getForEntity("http://user/user/" + historyParent.getId_user(),
                            UserDTO.class);

            UserDTO userDTO = responseEntityUser.getBody();

            System.out.println(responseEntityUser.getStatusCode());

            // Add this part to fetch and map the schedule
            Integer historyID = historyParent.getIdHistory();
            History history = historyRepository.findById(historyID).orElse(null);
            HistoryDTO historyDTO = modelMapper.map(history, HistoryDTO.class);

            responseDTO1.setUserDTO(userDTO);
            responseDTO1.setHistoryParentDTO(historyParentDTO);
            responseDTO1.setHistory(history); // Set the scheduleDTO
            responseList.add(responseDTO1);
        }

        return responseList;
    }

    @Override
    public List<ResponseDTO> getAllHistoryByScheHistoryId(Integer id) {
        List<ResponseDTO> responseList = new ArrayList<>();

        List<History> historyParents = historyRepository.getAllHistoryByScheHistoryId(id);

        for (History history : historyParents) {
            ResponseDTO responseDTO = new ResponseDTO();

            HistoryDTO historyDTO = modelMapper.map(history, HistoryDTO.class);

            ResponseEntity<UserDTO> responseEntityUser = restTemplate
                    .getForEntity("http://user/user/" + history.getIdUser(),
                            UserDTO.class);

            UserDTO userDTO = responseEntityUser.getBody();

            System.out.println(responseEntityUser.getStatusCode());

            ResponseEntity<ScheduleHistoryDTO> responseEntitysch = restTemplate
                    .getForEntity("http://schedule/schedule/sch/" + history.getIdScheduleHistory(),
                            ScheduleHistoryDTO.class);

            ScheduleHistoryDTO scheduleHistoryDTO = responseEntitysch.getBody();

            System.out.println(responseEntityUser.getStatusCode());



            responseDTO.setHistory(historyDTO);
            responseDTO.setUser(userDTO);
            responseList.add(responseDTO);
        }

        return responseList;
    }


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


//    }

}
