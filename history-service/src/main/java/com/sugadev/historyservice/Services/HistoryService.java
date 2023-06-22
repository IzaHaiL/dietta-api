package com.sugadev.historyservice.Services;


import com.sugadev.historyservice.Dto.HistoryDTO;

import com.sugadev.historyservice.Dto.ResponseDTO;

import java.util.List;

public interface HistoryService {

    HistoryDTO saveHistory (HistoryDTO historyDto);

    ResponseDTO getHistory(Integer historyId);

    List<HistoryDTO> getAllHistory();

//    HistoryDto updateHistory(int id, HistoryDto history);

    void deleteHistory(Integer historyId);
}
