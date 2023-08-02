package com.sugadev.diaryservice.Service;


import com.sugadev.diaryservice.DTO.CulinaryDTO;
import com.sugadev.diaryservice.DTO.DiaryDTO;
import com.sugadev.diaryservice.DTO.ResponseDTO;
import com.sugadev.diaryservice.DTO.UserDTO;
import com.sugadev.diaryservice.Model.Diary;
import com.sugadev.diaryservice.Repository.DiaryRepository;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiaryServiceImpl implements DiaryService{

    @Autowired
    DiaryRepository diaryRepository;
    @Autowired
    ModelMapper modelMapper;
    RestTemplate restTemplate;


    public DiaryDTO saveDiary(DiaryDTO addDiary) {
        Diary diary = modelMapper.map(addDiary, Diary.class);
        Diary savedDiary = diaryRepository.save(diary);
        return modelMapper.map(savedDiary, DiaryDTO.class);
    }


    public ResponseDTO getDiary(Integer diaryId) {
        ResponseDTO responseDTO = new ResponseDTO();
        Diary diary = diaryRepository.findById(diaryId).get();
        DiaryDTO diaryDTO = modelMapper.map(diary, DiaryDTO.class);

        ResponseEntity<UserDTO> responseEntity = restTemplate
                .getForEntity("http://user/user/" + diary.getIdUser(),
                        UserDTO.class);

        ResponseEntity<CulinaryDTO> responseEntity1 = restTemplate
                .getForEntity("http://culinary/culinary/" + diary.getIdCulinary(),
                        CulinaryDTO.class);


        UserDTO userDTO = responseEntity.getBody();
        CulinaryDTO culinaryDTO = responseEntity1.getBody();


        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity1.getStatusCode());


        responseDTO.setCulinary(culinaryDTO);
        responseDTO.setUser(userDTO);
        responseDTO.setDiary(diaryDTO);

        return responseDTO;
    }



    public List<ResponseDTO> getCulinaryBy(Integer IdCulinary) {
        List<ResponseDTO> responseList = new ArrayList<>();

        List<Diary> diaryList = (List<Diary>) diaryRepository.getCulinaryById(IdCulinary);

        for (Diary diary : diaryList) {
            DiaryDTO diaryDTO = modelMapper.map(diary, DiaryDTO.class);

            ResponseEntity<UserDTO> responseEntity = restTemplate.getForEntity(
                    "http://user/user/" + diary.getIdUser(),
                    UserDTO.class
            );
            ResponseEntity<CulinaryDTO> responseEntity1 = restTemplate.getForEntity(
                    "http://culinary/culinary/" + diary.getIdCulinary(),
                    CulinaryDTO.class
            );

            UserDTO userDTO = responseEntity.getBody();
            CulinaryDTO culinaryDTO = responseEntity1.getBody();

            System.out.println(responseEntity.getStatusCode());
            System.out.println(responseEntity1.getStatusCode());

            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setCulinary(culinaryDTO);
            responseDTO.setUser(userDTO);
            responseDTO.setDiary(diaryDTO);

            responseList.add(responseDTO);
        }

        return responseList;
    }
       // return modelMapper.map(diary, DiaryDTO.class);



    public List<ResponseDTO> getUserBy(Integer IdUser) {
        List<ResponseDTO> responseList = new ArrayList<>();

        List<Diary> diaryList = (List<Diary>) diaryRepository.getUserById(IdUser);

        for (Diary diary : diaryList) {
            DiaryDTO diaryDTO = modelMapper.map(diary, DiaryDTO.class);

            ResponseEntity<UserDTO> responseEntity = restTemplate.getForEntity(
                    "http://user/user/" + diary.getIdUser(),
                    UserDTO.class
            );
            ResponseEntity<CulinaryDTO> responseEntity1 = restTemplate.getForEntity(
                    "http://culinary/culinary/" + diary.getIdCulinary(),
                    CulinaryDTO.class
            );

            UserDTO userDTO = responseEntity.getBody();
            CulinaryDTO culinaryDTO = responseEntity1.getBody();

            System.out.println(responseEntity.getStatusCode());
            System.out.println(responseEntity1.getStatusCode());

            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setCulinary(culinaryDTO);
            responseDTO.setUser(userDTO);
            responseDTO.setDiary(diaryDTO);

            responseList.add(responseDTO);
        }

        return responseList;
    }


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

    public void deleteDiary(Integer diaryId) {
        diaryRepository.deleteById(diaryId);
    }

    public List<DiaryDTO> getAllDiary() {
        List<Diary> diaries = diaryRepository.findAll();
        return diaries.stream().map(diary -> modelMapper.map(diary, DiaryDTO.class)).collect(Collectors.toList());
    }





}
