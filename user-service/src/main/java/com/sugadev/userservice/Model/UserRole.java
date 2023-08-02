package com.sugadev.userservice.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
public class UserRole {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50, unique = true)
    private String nama_role;

    public UserRole(String nama_role) {this.nama_role = nama_role; }
    public UserRole(Integer id) {
        super();
        this.id = id;
    }
    @Override
    public String toString() {return this.nama_role;}
}
