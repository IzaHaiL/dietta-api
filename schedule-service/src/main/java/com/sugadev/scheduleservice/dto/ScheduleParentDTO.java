package com.sugadev.scheduleservice.dto;


import lombok.Data;

@Data
public class ScheduleParentDTO {

    private int idScheduleParent;
    private String title;
    private int id_schedule;
    private int id_user;
}
