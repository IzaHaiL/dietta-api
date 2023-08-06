package com.sugadev.historyservice.Dto;

import com.sugadev.historyservice.Model.HistoryChild;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO1 {
    private HistoryParentDTO historyParentDTO;
    private UserDTO userDTO;
    private HistoryChild historyChild;
}
