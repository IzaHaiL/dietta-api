package com.sugadev.scheduleservice.dto;


import lombok.Data;

@Data
public class ScheduleHistoryParentDTO {

    private int idScheHistoryParent;
    private String title;
    private String description;
    private int id_user;
    private int id_schedule_parent;
}
