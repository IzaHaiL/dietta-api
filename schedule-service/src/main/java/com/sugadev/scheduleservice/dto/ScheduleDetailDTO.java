package com.sugadev.scheduleservice.dto;


import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ScheduleDetailDTO {

    private int idScheduleDetail;
    private String title;
    private int id_schedule;
    private int id_user;
}
