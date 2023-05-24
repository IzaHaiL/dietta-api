package com.sugadev.dietta.repository;

import com.sugadev.dietta.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
