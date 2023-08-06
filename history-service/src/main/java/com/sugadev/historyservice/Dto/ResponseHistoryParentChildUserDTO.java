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
public class ResponseHistoryParentChildUserDTO {
    private HistoryParentDTO historyParent;
    private UserDTO user;
    private HistoryChild historyChild;
}
