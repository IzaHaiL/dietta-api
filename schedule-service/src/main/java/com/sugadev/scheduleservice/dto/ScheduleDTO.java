package com.sugadev.scheduleservice.dto;


import lombok.Data;

@Data
public class ScheduleDTO {


    private int id_schedule;
    private String title;
    private String date;
    private int id_video;
    private int id_user;
    private int id_schedule_parent;
    private int version;

}
