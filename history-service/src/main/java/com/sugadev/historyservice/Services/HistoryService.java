package com.sugadev.historyservice.Services;


import com.sugadev.historyservice.Dto.HistoryDTO;

import com.sugadev.historyservice.Dto.ResponseDTO;
import com.sugadev.historyservice.Dto.ResponseDTOV2;

import java.util.List;

public interface HistoryService {

    HistoryDTO saveHistory (HistoryDTO historyDto);

    ResponseDTO getHistory(Integer historyId);

    ResponseDTOV2 getHistory2(Integer history2Id);

    List<HistoryDTO> getAllHistory();

//    HistoryDto updateHistory(int id, HistoryDto history);

    void deleteHistory(Integer historyId);
}
