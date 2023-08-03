package com.sugadev.historyservice.Model;

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
@Table(name ="history_parent")
public class HistoryParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_history_parent")
    private int id_history_parent;
    private String title;
    private String description;
    private int idHistory;
    private int id_user;
    private int id_sche_history_parent;

}
