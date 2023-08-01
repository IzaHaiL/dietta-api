package com.sugadev.diaryservice.Service;

import com.sugadev.diaryservice.DTO.DiaryDTO;
import com.sugadev.diaryservice.DTO.ResponseDTO;
import com.sugadev.diaryservice.DTO.UserDTO;
import com.sugadev.diaryservice.Model.Diary;
import com.sugadev.diaryservice.Repository.DiaryRepository;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class DiaryServiceImpl implements DiaryService{

    DiaryRepository diaryRepository;
    ModelMapper modelMapper;
    RestTemplate restTemplate;

    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    public DiaryDTO saveDiary(DiaryDTO addDiary) {
        Diary diary = modelMapper.map(addDiary, Diary.class);
        Diary savedDiary = diaryRepository.save(diary);
        return modelMapper.map(savedDiary, DiaryDTO.class);
    }

    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    public ResponseDTO getDiary(Integer diaryId) {
        ResponseDTO responseDTO = new ResponseDTO();
        Diary diary = diaryRepository.findById(diaryId).get();
        DiaryDTO diaryDTO = modelMapper.map(diary, DiaryDTO.class);

        ResponseEntity<UserDTO> responseEntity = restTemplate
                .getForEntity("Http://user/user/" + diary.getIdUser(),
                        UserDTO.class);


        UserDTO userDTO = responseEntity.getBody();

        System.out.println(responseEntity.getStatusCode());

        responseDTO.setUser(userDTO);
        responseDTO.setDiary(diaryDTO);

        return responseDTO;
    }

    @RolesAllowed({"ROLE_ADMIN"})
    public DiaryDTO updateDiary(int id, DiaryDTO updateDiary) {

        Diary existingDiary = diaryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Detail not found by id : " + id));

//        existingDiary.setIdDiary(updateDiary.getIdDiary());
        existingDiary.setName(updateDiary.getName());
        existingDiary.setDesc(updateDiary.getDesc());
        existingDiary.setTanggal(updateDiary.getTanggal());

        Diary updatedDiary = diaryRepository.save(existingDiary);
        return modelMapper.map(updatedDiary, DiaryDTO.class);
    }
    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    public void deleteDiary(Integer diaryId) {
        diaryRepository.deleteById(diaryId);
    }

    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    public List<DiaryDTO> getAllDiary() {
        List<Diary> diaries = diaryRepository.findAll();
        return diaries.stream()
                .map(diary -> modelMapper.map(diary, DiaryDTO.class))
                .collect(Collectors.toList());
    }
}
