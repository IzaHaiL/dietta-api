package com.sugadev.historyservice.Model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@Table(name ="history_parent")
public class HistoryParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_history_parent")
    private int idHistoryParent;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "id_user")
    private int idUser;
    @Column(name = "id_sche_history_parent")
    private int idScheHistoryParent;

}
