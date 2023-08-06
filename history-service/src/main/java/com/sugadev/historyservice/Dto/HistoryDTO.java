package com.sugadev.historyservice.Dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class HistoryDTO {

    private int idHistory;
    private String title;
    private String description;
    private String date;
    private int totalExercise;
    private int idUser;
}
