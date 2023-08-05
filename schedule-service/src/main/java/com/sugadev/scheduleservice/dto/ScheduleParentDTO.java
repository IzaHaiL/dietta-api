package com.sugadev.scheduleservice.dto;


import lombok.Data;

@Data
public class ScheduleParentDTO {
    private int id_schedule_parent;
    private String title;
    private String description;
    private int id_user;
    private int version;
}
