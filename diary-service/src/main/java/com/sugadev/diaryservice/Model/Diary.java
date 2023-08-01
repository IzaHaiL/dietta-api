package com.sugadev.diaryservice.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "diary")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDiary;

    @Column(name = "name")
    private String name;

    @Column(name = "deskripsi")
    private String desc;

    @Column(name = "tanggal")
    private String tanggal;

    @Column(name = "id_user")
    private int idUser;

    @Column (name="id_culinary")
    private  int idCulinary;

//    @Column (name="total_kalori")
//    private int totalKalori;

}
