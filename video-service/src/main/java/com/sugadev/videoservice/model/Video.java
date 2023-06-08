package com.sugadev.videoservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "Video")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String tittle;
    private String description;
    private String type;
    private String url;


}
