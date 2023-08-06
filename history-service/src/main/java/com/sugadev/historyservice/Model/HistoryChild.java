package com.sugadev.historyservice.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "history_child")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryChild {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHistoryChild;
    @Column(name = "id_history_parent")
    private int idHistoryParent;
    @Column(name = "title")
    private String title;
    @Column(name ="total_time")
    private String totaltime;
    @Column(name = "kalori_terbakar")
    private int burncalories;
    @Column(name = "id_sche_history_child")
    private int idScheHistoryChild;



}
