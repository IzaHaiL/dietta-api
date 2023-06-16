package com.sugadev.scheduleservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoDTO {

    private int id_video;
    private String title;
    private String description;
    private String category;
    private String url;
}
