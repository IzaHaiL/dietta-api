package com.sugadev.scheduleservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name ="schedule_parent")

@OnDelete(action = OnDeleteAction.NO_ACTION)
public class ScheduleParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_schedule_parent")

    private int id_schedule_parent;
    private String title;
    private String description;
    private String date;
    private int id_user;
    @Version
    private int version;

    @OneToMany(mappedBy = "scheduleParent", orphanRemoval = false)
    private List<ScheduleHistoryParent> children = new ArrayList<>();

}
