package com.sugadev.culinaryservice.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "culinary")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Culinary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCulinary;

    @Column(name = "foodname")
    private String foodname;

    @Column(name = "Kalori")
    private int kalori;

    @Column(name = "Lemak")
    private int lemak;
    @Column(name = "Karbohidrat")
    private int karbohidrat;
    @Column(name = "protein")
    private int protein;

    @Column(name = "deskripsi")
    private String deskripsi;

    @Column(name = "imgurl")
    private String imgurl;


}
