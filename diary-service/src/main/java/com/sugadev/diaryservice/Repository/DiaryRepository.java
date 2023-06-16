package com.sugadev.diaryservice.Repository;

import com.sugadev.diaryservice.DTO.DiaryDTO;
import com.sugadev.diaryservice.Model.Diary;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Integer> {
}
