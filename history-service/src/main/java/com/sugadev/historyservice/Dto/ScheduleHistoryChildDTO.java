package com.sugadev.historyservice.Dto;

import lombok.Data;

@Data
public class ScheduleHistoryChildDTO {
    private int idScheHistoryChild;
    private int id_video;
    private int id_schedule_parent;
    private int version;
}
