package com.sugadev.historyservice.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseScheduleAndHistoryParentDTO {

    ScheduleHistoryParentDTO scheduleHistoryParentDTO;
    HistoryParentDTO historyParentDTO;
    UserDTO userDTO;

}
