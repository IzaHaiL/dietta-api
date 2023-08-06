package com.sugadev.historyservice.Services;


import com.sugadev.historyservice.Dto.*;
import jakarta.annotation.security.RolesAllowed;

import java.util.List;

public interface HistoryService {

    HistoryChildDTO createHistoryChild (HistoryChildDTO historyChildDto);
    List<HistoryChildDTO> getAllHistory();
    HistoryParentDTO createHistoryParent(HistoryParentDTO historyParentDTO);
    List<ResponseScheduleAndHistoryParentDTO> getHistoryParentByUserId(Integer id);
    List<ResponseScheduleAndHistoryChildDTO> getHistoryChildByHistoryParentId(Integer id);
    void deleteAllByIdUser(Integer idHistory);


//    void deleteAllByUserId(Integer id);
//    ResponseDTO getHistory(Integer historyId);
//    List<ResponseDTO1> getAllHistoryParentByuser(Integer id);
//    List<ResponseDTO> getAllHistoryByScheHistoryId(Integer id);



}
