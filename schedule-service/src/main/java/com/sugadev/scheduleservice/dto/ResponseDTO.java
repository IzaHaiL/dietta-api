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

    private UserDTO user;
    private ScheduleDTO schedule;
    private VideoDTO video;
    private ScheduleParentDTO scheduleParent;
}
