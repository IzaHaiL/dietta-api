package com.sugadev.videoservice.dto;

import lombok.*;

import java.util.Optional;

@Data
public class VideoDTO {


    private int id_video;
    private String title;
    private String description;
    private String category;
    private String url;
    private String thumbnail;

}
