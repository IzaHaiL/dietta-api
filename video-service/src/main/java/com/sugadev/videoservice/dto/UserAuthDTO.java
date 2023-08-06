package com.sugadev.videoservice.dto;

import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class UserAuthDTO {

    private int idUser;
    private String username;
    private String name;
    private int beratBadan;
    private int tinggiBadan;
    private Set<RoleDTO> roles = new HashSet<>();
    private String token;
    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (RoleDTO role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getNama_role()));
        }
        return authorities;
    }
    public void addRole (RoleDTO userRole) { this.roles.add(userRole);}
}
