package com.sugadev.historyservice.Services;


import com.sugadev.historyservice.Dto.HistoryDTO;

import com.sugadev.historyservice.Dto.ResponseDTO;
import com.sugadev.historyservice.Dto.ResponseDTO1;

import java.util.List;

public interface HistoryService {

    HistoryDTO saveHistory (HistoryDTO historyDto);

    ResponseDTO getHistory(Integer historyId);

    List<HistoryDTO> getAllHistory();

//    HistoryDto updateHistory(int id, HistoryDto history);

    void deleteHistory(Integer historyId);

//    List<ResponseDTO> getHistoryByUser(Integer id);

    List<ResponseDTO> getHistoriesByUser(Integer id);

    List<ResponseDTO1> getAllHistoryParentByuser(Integer id);

    List<ResponseDTO> getAllHistoryByScheHistoryId(Integer id);



}
