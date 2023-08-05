package com.sugadev.scheduleservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter
@Table(name ="schedule_child")
public class ScheduleChild {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_schedule_child")
    private int id_schedule_child;
//    @Column(name = "title")
//    private String title;
//    @Column(name = "date")
//    private String date;
//    private String description;
    private int id_video;
    private int id_schedule_parent;
    @Version
    private int version;

    @OneToMany(mappedBy = "scheduleChild", orphanRemoval = false)
    private List<ScheduleHistoryChild> children = new ArrayList<>();


}
