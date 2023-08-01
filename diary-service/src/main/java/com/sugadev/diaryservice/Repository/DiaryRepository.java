package com.sugadev.diaryservice.Repository;

import com.sugadev.diaryservice.DTO.CulinaryDTO;
import com.sugadev.diaryservice.DTO.DiaryDTO;
import com.sugadev.diaryservice.Model.Diary;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Integer> {

    @Query("SELECT svd FROM Diary svd WHERE svd.idCulinary = :id")
    List <Diary> getCulinaryById(@Param("id") int idCulinary);

    @Query("SELECT svd FROM Diary svd WHERE svd.idUser = :id")
    List <Diary> getUserById(@Param("id") int IdUser);


}

