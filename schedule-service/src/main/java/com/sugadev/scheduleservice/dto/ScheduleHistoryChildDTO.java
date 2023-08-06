package com.sugadev.scheduleservice.dto;

import com.sugadev.scheduleservice.model.ScheduleChild;
import lombok.Data;

@Data
public class ScheduleHistoryChildDTO {
    private int idScheHistoryChild;
    private int id_video;
    private int id_schedule_parent;
    private int version;
}
