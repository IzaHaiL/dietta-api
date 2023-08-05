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
@Table(name ="schedule_history_child")
public class ScheduleHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idScheHistory;

    @ManyToOne
    @JoinColumn(name = "id_schedule_child")
    private ScheduleChild scheduleChild;

    @Column(insertable=false, updatable=false)
    private int id_schedule_child;
    private String title;
    private String date;
    private int id_video;
    private int id_user;
//    private int version;

}
