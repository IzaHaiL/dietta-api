package com.sugadev.historyservice.Services;


import com.sugadev.historyservice.Dto.*;

import com.sugadev.historyservice.Model.History;
import com.sugadev.historyservice.Repository.HistoryRepository;
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

public class HistoryServiceImpl implements HistoryService {

    HistoryRepository historyRepository;
    ModelMapper modelMapper;
    RestTemplate restTemplate;

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


        ResponseEntity<ScheduleDTO> responseEntity2 = restTemplate
                .getForEntity("Http://schedule/schedule/" + history.getIdSchedule(),
                        ScheduleDTO.class);

        ScheduleDTO scheduleDTO = responseEntity2.getBody();

        System.out.println(responseEntity.getStatusCode());

        responseDTO.setUser(userDTO);
        responseDTO.setHistory(historyDto);
        responseDTO.setSchedule(scheduleDTO);

        return responseDTO;
    }


    @Override
    public ResponseDTOV2 getHistory2(Integer history2Id) {
        ResponseDTOV2 responseDTOV2 = new ResponseDTOV2();
        History history = historyRepository.findById(history2Id).get();

        HistoryDTO historyDto = modelMapper.map(history, HistoryDTO.class);


        ResponseEntity<UserDTO> responseEntity = restTemplate
                .getForEntity("Http://user/user/" + history.getIdUser(),
                        UserDTO.class);

        UserDTO userDTO = responseEntity.getBody();


        ResponseEntity<ScheduleDTOV2> responseEntity2 = restTemplate
                .getForEntity("Http://schedule/schedule/" + history.getIdSchedule(),
                        ScheduleDTOV2.class);

        ScheduleDTOV2 scheduleDTOV2 = responseEntity2.getBody();

        System.out.println(responseEntity.getStatusCode());

        responseDTOV2.setUser(userDTO);
        responseDTOV2.setHistory(historyDto);
        responseDTOV2.setSchedule2(scheduleDTOV2);

        return responseDTOV2;
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
