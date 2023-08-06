package com.sugadev.historyservice.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseScheduleAndHistoryParentDTO {

    ScheduleHistoryParentDTO scheduleHistoryParent;
    HistoryParentDTO historyParent;
    UserDTO user;

}
