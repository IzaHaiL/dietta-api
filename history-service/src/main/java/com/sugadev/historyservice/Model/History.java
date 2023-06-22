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

    @Column(name = "Waktu_Mulai")
    private String starttime;

    @Column(name = "Waktu_Selesai")
    private String endtime;

    @Column(name = "Kalori_Terbakar")
    private int burncalories;

    @Column(name = "latitude")
    private int latitude;

    @Column(name = "Longitude")
    private int longitude;

    @Column(name = "jarak")
    private int jarak;

    private int id_schedule;


}
