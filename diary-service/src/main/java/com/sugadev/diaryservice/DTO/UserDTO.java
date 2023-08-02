package com.sugadev.diaryservice.DTO;

import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;

@Data
public class UserDTO {

    private int idUser;
    private String username;
    private String name;
    private int beratBadan;
    private int tinggiBadan;
    private Set<RoleDTO> roles = new HashSet<>();

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (RoleDTO role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getNama_role()));
        }
        return authorities;
    }
    public void addRole (RoleDTO userRole) { this.roles.add(userRole);}
}
