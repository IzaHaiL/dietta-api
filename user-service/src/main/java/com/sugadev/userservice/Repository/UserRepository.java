package com.sugadev.userservice.Repository;

import com.sugadev.userservice.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
