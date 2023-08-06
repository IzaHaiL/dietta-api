package com.sugadev.scheduleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseScheduleChildUserVideoDTO {
    private UserDTO user;
    private ScheduleChildDTO schedule;
    private VideoDTO video;
}
