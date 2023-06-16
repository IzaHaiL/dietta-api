package com.sugadev.diaryservice.Controller;

import com.sugadev.diaryservice.DTO.DiaryDTO;
import com.sugadev.diaryservice.DTO.ResponseDTO;
import com.sugadev.diaryservice.Model.Diary;
import com.sugadev.diaryservice.Service.DiaryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("diary")
@AllArgsConstructor
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    private ModelMapper modelMapper;

    @PostMapping("/add")
    public ResponseEntity<DiaryDTO> saveDiary(@RequestBody DiaryDTO diaryDTO){
        DiaryDTO createdDiary = diaryService.saveDiary(diaryDTO);
        return new ResponseEntity<>(createdDiary, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDTO> getDiary(@PathVariable("id") Integer diaryId){
        ResponseDTO responseDTO = diaryService.getDiary(diaryId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DiaryDTO>>getAllDiary(){
        List<DiaryDTO> diaryDTO = diaryService.getAllDiary();
        return new ResponseEntity<>(diaryDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DiaryDTO> updateUser( @PathVariable("id") Integer id, @RequestBody DiaryDTO userDTO) {
        DiaryDTO updatedDiary = diaryService.updateDiary(id, userDTO);
        return new ResponseEntity<>(updatedDiary, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Diary> deleteUser(@PathVariable("id") Integer id) {
        try {
            diaryService.deleteDiary(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
