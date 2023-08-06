package com.sugadev.historyservice.Dto;

import lombok.Data;

@Data
public class ScheduleHistoryParentDTO {
    private int idScheHistoryParent;
    private String title;
    private String description;
    private int id_user;
    private int version;
}