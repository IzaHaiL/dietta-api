package com.sugadev.scheduleservice.dto;

import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class UserDTO {

    private int idUser;
    private String username;
    private String name;
    private int beratBadan;
    private int tinggiBadan;
   // private Set<RoleDTO> roles = new HashSet<>();



 //   public void addRole (RoleDTO userRole) { this.roles.add(userRole);}
}
