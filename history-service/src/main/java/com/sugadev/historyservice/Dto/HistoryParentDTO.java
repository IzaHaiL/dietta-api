package com.sugadev.historyservice.Dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class HistoryParentDTO {

    private int id_history_parent;
    private String title;
    private String description;
    private int idHistory;
    private int id_user;
    private int id_sche_history_parent;
}
