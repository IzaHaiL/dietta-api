package com.sugadev.diaryservice.Service;

import com.sugadev.diaryservice.DTO.DiaryDTO;
import com.sugadev.diaryservice.DTO.ResponseDTO;

import java.util.List;

public interface DiaryService {

    DiaryDTO saveDiary(DiaryDTO addDiary);

    ResponseDTO getDiary(Integer diaryId);

    DiaryDTO updateDiary(int id, DiaryDTO updateDiary);

    void deleteDiary(Integer diaryId);

    List<DiaryDTO> getAllDiary();

}
