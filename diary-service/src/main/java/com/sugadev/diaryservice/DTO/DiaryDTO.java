package com.sugadev.diaryservice.DTO;

import lombok.Data;

@Data
public class DiaryDTO {

    private int idDiary;
    private String name;
    private String desc;
    private String tanggal;
    private int idUser;
    private int IdCulinary;
    private int totalKalori;
}
