package com.sugadev.historyservice.Dto;


import lombok.Data;

@Data
public class HistoryDTO {
    private int idHistory;
    private String title;
    private  String totaltime;
    private int burncalories;
    private int idUser;
    private int idScheduleHistory;
}
