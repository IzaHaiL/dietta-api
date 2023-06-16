package com.sugadev.scheduleservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name ="schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private int id_schedule;
    @Column(name = "title")
    private String title;
    @Column(name = "date")
    private String date;
    private int id_video;
    private int id_user;
}
