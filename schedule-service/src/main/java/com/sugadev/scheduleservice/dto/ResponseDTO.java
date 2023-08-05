package com.sugadev.scheduleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    private ScheduleChildDTO schedule;
    private VideoDTO video;
    private ScheduleParentDTO scheduleParent;
}
