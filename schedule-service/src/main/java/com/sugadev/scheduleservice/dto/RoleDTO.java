package com.sugadev.scheduleservice.dto;

import lombok.Data;

@Data
public class RoleDTO {
    private Integer id;

    private String nama_role;

    public RoleDTO(String nama_role) {this.nama_role = nama_role; }
    @Override
    public String toString() {return this.nama_role;}
}
