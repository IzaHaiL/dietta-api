package com.sugadev.userservice.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "role")
@Data
public class UserRole {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50, unique = true)
    private String nama_role;

    public UserRole(String nama_role) {this.nama_role = nama_role; }
    public UserRole(Integer id, String nama_role) {
        super();
        this.id = id;
        this.nama_role = nama_role;
    }

    public UserRole(Integer id) {
        this.id = id;
    }

    public UserRole() {

    }

    @Override
    public String toString() {return this.nama_role;}
}
