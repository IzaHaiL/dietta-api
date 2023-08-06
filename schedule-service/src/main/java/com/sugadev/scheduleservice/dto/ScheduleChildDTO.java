package com.sugadev.scheduleservice.dto;


import jakarta.persistence.Column;
import jakarta.persistence.Version;
import lombok.Data;

@Data
public class ScheduleChildDTO {
    private int id_schedule_child;

    private int id_video;
    private int id_schedule_parent;
    private int version;
}
