package com.sugadev.diaryservice.Service;


import com.sugadev.diaryservice.DTO.*;
import com.sugadev.diaryservice.Model.Diary;
import com.sugadev.diaryservice.Repository.DiaryRepository;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
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
        DiaryDTO diaryDTO = modelMapper.map(diaryId, DiaryDTO.class);

        HttpHeaders headers = new HttpHeaders();
        UserAuthDTO user = (UserAuthDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        headers.setBearerAuth(user.getToken());

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        ResponseEntity<UserDTO> responseEntity = restTemplate
                .exchange("http://user/user/" + diary.getIdUser(),
                        HttpMethod.GET, entity, UserDTO.class);

        ResponseEntity<CulinaryDTO> responseEntity1 = restTemplate
                .exchange("http://culinary/culinary/" + diary.getIdCulinary(),
                        HttpMethod.GET, entity, CulinaryDTO.class);


        UserDTO userDTO = responseEntity.getBody();
        CulinaryDTO culinaryDTO = responseEntity1.getBody();

        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity1.getStatusCode());


        responseDTO.setCulinary(culinaryDTO);
        responseDTO.setUser(userDTO);
        responseDTO.setDiary(diaryDTO);
        return responseDTO;
    }



//    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
//    @Override
//    public ResponseDTO getDiary(Integer diaryId) {
//        ResponseDTO responseDTO = new ResponseDTO();
//        Diary diary = diaryRepository.findById(diaryId).get();
//        DiaryDTO diaryDTO = modelMapper.map(diary, DiaryDTO.class);
//
//        HttpHeaders headers = new HttpHeaders();
//        UserAuthDTO user = (UserAuthDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        headers.setBearerAuth(user.getToken());
//
//        HttpEntity<String> entity = new HttpEntity<>("body", headers);
//
//        ResponseEntity<UserDTO> responseEntity = restTemplate
//                .exchange("http://user/user/" + diary.getIdUser(),
//                        HttpMethod.GET, entity, UserDTO.class);
//
//        UserDTO userDTO = responseEntity.getBody();
//
//        ResponseEntity<CulinaryDTO> responseEntitypar = restTemplate
//                .exchange("http://culinary/culinary/" + diary.getIdCulinary(),
//                        HttpMethod.GET, entity, CulinaryDTO.class);
//
//        CulinaryDTO culinaryDTO = responseEntitypar.getBody();
//
//        System.out.println(responseEntity.getStatusCode());
//        System.out.println(responseEntitypar.getStatusCode());
//
//        responseDTO.setDiary(diaryDTO);
//        responseDTO.setUser(userDTO);
//        responseDTO.setCulinary(culinaryDTO);
//
//        return responseDTO;
//    }


    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    public List<ResponseDTO> getCulinaryBy(Integer IdCulinary) {
        List<ResponseDTO> responseList = new ArrayList<>();

        List<Diary> diaryList = (List<Diary>) diaryRepository.getCulinaryById(IdCulinary);

        for (Diary diary : diaryList) {
            DiaryDTO diaryDTO = modelMapper.map(diary, DiaryDTO.class);

            HttpHeaders headers = new HttpHeaders();
            UserAuthDTO user = (UserAuthDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            headers.setBearerAuth(user.getToken());

            HttpEntity<String> entity = new HttpEntity<>("body", headers);

            ResponseEntity<UserDTO> responseEntityUser = restTemplate
                    .exchange("http://user/user/" + diary.getIdUser(),
                    HttpMethod.GET, entity, UserDTO.class);

            ResponseEntity<CulinaryDTO> responseEntity1 = restTemplate
                    .exchange("http://culinary/culinary/" + diary.getIdCulinary(),
                            HttpMethod.GET, entity, CulinaryDTO.class);


            UserDTO userDTO = responseEntityUser.getBody();

            CulinaryDTO culinaryDTO = responseEntity1.getBody();

            System.out.println(responseEntityUser.getStatusCode());
            System.out.println(responseEntity1.getStatusCode());

            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setCulinary(culinaryDTO);
            responseDTO.setUser(userDTO);
            responseDTO.setDiary(diaryDTO);

            responseList.add(responseDTO);
        }

        return responseList;
    }
    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    public List<ResponseDTO> getUserBy(Integer IdUser) {
        List<ResponseDTO> responseList = new ArrayList<>();

        List<Diary> diaryList = (List<Diary>) diaryRepository.getUserById(IdUser);


        for (Diary diary : diaryList) {
            DiaryDTO diaryDTO = modelMapper.map(diary, DiaryDTO.class);

            HttpHeaders headers = new HttpHeaders();
            UserAuthDTO user = (UserAuthDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            headers.setBearerAuth(user.getToken());

            HttpEntity<String> entity = new HttpEntity<>("body", headers);

            ResponseEntity<UserDTO> responseEntityUser = restTemplate
                    .exchange("http://user/user/" + diary.getIdUser(),
                    HttpMethod.GET, entity, UserDTO.class);

            ResponseEntity<CulinaryDTO> responseEntity1 = restTemplate
                    .exchange("http://culinary/culinary/" + diary.getIdCulinary(),
                            HttpMethod.GET, entity, CulinaryDTO.class);

            UserDTO userDTO = responseEntityUser.getBody();
            CulinaryDTO culinaryDTO = responseEntity1.getBody();

            System.out.println(responseEntityUser.getStatusCode());
            System.out.println(responseEntity1.getStatusCode());

            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setCulinary(culinaryDTO);
            responseDTO.setUser(userDTO);
            responseDTO.setDiary(diaryDTO);

            responseList.add(responseDTO);
        }

        return responseList;
    }
    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    public DiaryDTO updateDiary(int id, DiaryDTO updateDiary) {

        Diary existingDiary = diaryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Detail not found by id : " + id));

//        existingDiary.setIdDiary(updateDiary.getIdDiary());
        existingDiary.setName(updateDiary.getName());
        existingDiary.setDesc(updateDiary.getDesc());
        existingDiary.setTanggal(updateDiary.getTanggal());
        existingDiary.setIdCulinary(updateDiary.getIdCulinary());

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
        return diaries.stream().map(diary -> modelMapper.map(diary, DiaryDTO.class)).collect(Collectors.toList());
    }





}
