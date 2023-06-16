package com.sugadev.scheduleservice.dto;


import lombok.*;

@Data
public class ScheduleDTO {

    private int id_schedule;
    private String title;
    private String date;
    private int id_video;
}
