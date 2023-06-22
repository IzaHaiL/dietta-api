package com.sugadev.culinaryservice.Dto;


import lombok.Data;
@Data

public class CulinaryDTO {
    private int idCulinary;
    private String foodname;
    private int kalori;
    private int lemak;
    private int karbohidrat;
    private int protein;
    private String deskripsi;
    private String imgurl;
}
