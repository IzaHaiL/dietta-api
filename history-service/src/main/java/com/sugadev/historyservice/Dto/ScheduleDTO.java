package com.sugadev.historyservice.Dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ScheduleDTO {
    private int id_schedule;

    private String title;

    private String date;

}
