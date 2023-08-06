package com.sugadev.historyservice.Model;


import jakarta.persistence.*;
import lombok.Data;


@Table(name = "history")
@Entity
@Data
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_history")
    private int idHistory;
    private String title;
    private String description;
    private String date;
    @Column(name = "total_exercise")
    private int totalExercise;
    private int idUser;

}
