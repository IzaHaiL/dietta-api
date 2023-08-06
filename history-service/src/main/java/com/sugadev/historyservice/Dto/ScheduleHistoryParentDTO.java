package com.sugadev.historyservice.Dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ScheduleHistoryParentDTO {
    @Column(name = "id_history_parent")
    private int idHistoryParent;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "id_user")
    private int idUser;
    @Column(name = "id_sche_history_parent")
    private int idScheHistoryParent;
}