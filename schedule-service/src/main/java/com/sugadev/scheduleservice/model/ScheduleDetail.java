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
@Table(name ="schedule_detail")
public class ScheduleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_schedule_detail")
    private int idScheduleDetail;
    @Column(name = "title")
    private String title;
    @Column (name = "id_schedule")
    private int id_schedule;
    @Column (name= "id_user")
    private int id_user;

}
