package com.sugadev.diaryservice.DTO;

import lombok.*;
import org.springframework.stereotype.Service;

@Data
public class UserDTO {

    private int idUser;
    private String username;
    private String name;
    private int beratBadan;
    private int tinggiBadan;
}
