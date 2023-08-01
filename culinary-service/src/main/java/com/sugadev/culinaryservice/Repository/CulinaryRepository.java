package com.sugadev.culinaryservice.Repository;

import com.sugadev.culinaryservice.Model.Culinary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CulinaryRepository extends JpaRepository<Culinary,Integer> {



}
