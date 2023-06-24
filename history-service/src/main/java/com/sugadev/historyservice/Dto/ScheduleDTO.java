package com.sugadev.historyservice.Dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ScheduleDTO {

    private int idSchedule;
    private String title;
    private String date;
    private int id_video;
    private int idUser;
    private int version;

}
