package com.sugadev.historyservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTOV2 {

    private UserDTO user;

    private HistoryDTO history;

    private ScheduleDTOV2 schedule2;

}
