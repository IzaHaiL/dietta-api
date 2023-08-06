package com.sugadev.historyservice.Dto;


import lombok.Data;

@Data
public class HistoryChildDTO {
    private int idHistoryChild;
    private String title;
    private  String totaltime;
    private int burncalories;
    private int idScheHistoryChild;
    private int idHistoryParent;

}
