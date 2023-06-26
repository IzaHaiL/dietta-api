package com.sugadev.scheduleservice.dto;

import com.sugadev.scheduleservice.model.Schedule;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ScheduleHistoryDTO {

    private int idScheHistory;
    private int id_schedule;
    private String title;
    private String date;
    private int id_video;
    private int id_user;
    private int version;
}
