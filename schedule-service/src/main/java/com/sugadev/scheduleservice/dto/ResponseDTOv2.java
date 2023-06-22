package com.sugadev.scheduleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTOv2 {

    private UserDTO user;
    private ScheduleDTOv2 schedule;
    private VideoDTO video;
}
