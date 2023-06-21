package com.sugadev.historyservice.Services;

import com.sugadev.historyservice.Dto.HistoryDto;
import com.sugadev.historyservice.Dto.ResponseDTO;

import java.util.List;

public interface HistoryService {
    HistoryDto saveHistory (HistoryDto historyDto);

    ResponseDTO getHistory(Integer historyId);

    List<HistoryDto> getAllHistory();

//    HistoryDto updateHistory(int id, HistoryDto history);

    void deleteHistory(Integer historyId);
}
