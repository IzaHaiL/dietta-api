package com.sugadev.videoservice.dto;

import lombok.Data;

@Data
public class RoleDTO {
    private Integer id;

    private String nama_role;

    public RoleDTO(String nama_role) {this.nama_role = nama_role; }
    @Override
    public String toString() {return this.nama_role;}

    public RoleDTO(Integer id, String nama_role) {
        this.id = id;
        this.nama_role = nama_role;
    }
    public RoleDTO() {
    }
}
