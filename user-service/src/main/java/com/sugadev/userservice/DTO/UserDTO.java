package com.sugadev.userservice.DTO;

import com.sugadev.userservice.Model.UserRole;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {

    private int idUser;
    private String username;
    private String password;
    private String name;
    private String email;
    private int noTelp;
    private int beratBadan;
    private int tinggiBadan;
    private Set<UserRole> roles;
}
