package com.sugadev.historyservice.Dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class HistoryDto {
    private int idHistory;

    private String title;
    private String starttime;
    private String endtime;
    private int burncalories;
    private int latitude;
    private int longitude;
    private int jarak;
    private int idUser;
}
