package com.sugadev.userservice.Repository;
import com.sugadev.userservice.Model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<UserRole, Integer> {
}
