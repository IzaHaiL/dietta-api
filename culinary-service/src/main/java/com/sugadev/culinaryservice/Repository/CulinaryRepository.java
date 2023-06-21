package com.sugadev.culinaryservice.Repository;

import com.sugadev.culinaryservice.Model.Culinary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CulinaryRepository extends JpaRepository<Culinary,Integer> {
}
