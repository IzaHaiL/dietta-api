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
@Table(name = "schedule_history_parent")
public class ScheduleHistoryParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idScheHistoryParent;

    @ManyToOne
    @JoinColumn(name = "id_schedule_parent")
    private ScheduleParent scheduleParent;

    @Column(insertable=false, updatable=false)
    private int id_shedule_parent ;
    private String title;
    private String description;
    private int id_user;
}
