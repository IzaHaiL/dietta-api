package com.sugadev.historyservice.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHistory;

    @Column(name = "id_user")
    private int idUser;

    @Column(name = "title")
    private String title;
    @Column(name ="total_time")
    private String totaltime;
    @Column(name = "kalori_terbakar")
    private int burncalories;
    @Column(name = "id_sche_history")
    private int idScheduleHistory;


}
