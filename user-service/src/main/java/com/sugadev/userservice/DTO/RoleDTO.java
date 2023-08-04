package com.sugadev.userservice.DTO;

import lombok.Data;

@Data
public class RoleDTO {
    private Integer id;

    private String nama_role;

    public RoleDTO() {
    }

    public RoleDTO(Integer id, String nama_role) {
        this.id = id;
        this.nama_role = nama_role;
    }
}
