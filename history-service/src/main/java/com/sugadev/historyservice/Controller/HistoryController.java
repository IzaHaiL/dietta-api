package com.sugadev.historyservice.Controller;


import com.sugadev.historyservice.Dto.*;

import com.sugadev.historyservice.Model.History;
import com.sugadev.historyservice.Model.HistoryParent;
import com.sugadev.historyservice.Services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;
    @PostMapping("/parent/add")
    public ResponseEntity<HistoryParentDTO> createHistoryParent(@RequestBody HistoryParentDTO historyParentDTO) {
        HistoryParentDTO createdHistory = historyService.createHistoryParent(historyParentDTO);
        return new ResponseEntity<>(createdHistory, HttpStatus.CREATED);
    }
    @PostMapping("/child/add")
    public ResponseEntity<HistoryChildDTO> createHistoryChild(@RequestBody HistoryChildDTO historyChildDto) {
        HistoryChildDTO createdHistory = historyService.createHistoryChild(historyChildDto);
        return new ResponseEntity<>(createdHistory, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<HistoryChildDTO>> getAllHistory() {
        List<HistoryChildDTO> historyChildDto = historyService.getAllHistory();
        return new ResponseEntity<>(historyChildDto, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HistoryParent> deleteAllByUserId(@PathVariable("id") Integer idHistory) {
        try {
            historyService.deleteAllByIdUser(idHistory);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/parent/{id}")
    public ResponseEntity<List<ResponseScheduleAndHistoryParentDTO>> getHistoryParentByUserId(@PathVariable("id") Integer id) {
        List<ResponseScheduleAndHistoryParentDTO> responseScheduleAndHistoryParentDTOList = historyService.getHistoryParentByUserId(id);
        return new ResponseEntity<>(responseScheduleAndHistoryParentDTOList, HttpStatus.OK);
    }

    @GetMapping("/child/{id}")
    public ResponseEntity<List<ResponseScheduleAndHistoryChildDTO>> getHistoryChildByHistoryParentId(@PathVariable("id") Integer id) {
        List<ResponseScheduleAndHistoryChildDTO> responseScheduleAndHistoryChildDTOList = historyService.getHistoryChildByHistoryParentId(id);
        return new ResponseEntity<>(responseScheduleAndHistoryChildDTOList, HttpStatus.OK);
    }

    @PostMapping("/add/biasa")
    public ResponseEntity<HistoryDTO> addBiasa(@RequestBody HistoryDTO historyDTO){
        HistoryDTO createdCulinary = historyService.addBiasa(historyDTO);
        return new ResponseEntity<>(createdCulinary, HttpStatus.CREATED);
    }

    @GetMapping("/get/biasa/{id}")
    public ResponseEntity<List<HistoryDTO>>getAllBiasaUser(@PathVariable("id") Integer id){
        List<HistoryDTO> historyDTOS = historyService.getAllBiasaUser(id);
        return new ResponseEntity<>(historyDTOS, HttpStatus.OK);
    }

    @DeleteMapping("/delete/biasa")
    public ResponseEntity<History> deleteBiasa(@PathVariable("id") Integer id) {
        try {
            historyService.deleteBiasa(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // @GetMapping("{id}")
//    public ResponseEntity<ResponseDTO> getHistory(@PathVariable("id") Integer historyId) {
//        ResponseDTO responseDTO = historyService.getHistory(historyId);
//        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
//    }
//    @PutMapping("/update/{id}")
//    public ResponseEntity<HistoryDto> updateHistory( @PathVariable("id") Integer id, @RequestBody HistoryDto historyDto) {
//        HistoryDto updatedHistory = historyService.updateHistory(id, historyDto);
//        return new ResponseEntity<>(updatedHistory, HttpStatus.OK);
//    }
//    @GetMapping("/user/{id}")
//    public ResponseEntity<List<ResponseDTO>> getHistoryByUser(@PathVariable("id") Integer id) {
//        List<ResponseDTO> responseDTO = historyService.getHistoriesByUser(id);
//        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
//    }

//    @GetMapping("/child/{id}")
//    public ResponseEntity<List<ResponseDTO>> getAllHistoryByScheHistoryId(@PathVariable("id") Integer id) {
//        List<ResponseDTO> responseDTOS = historyService.getAllHistoryByScheHistoryId(id);
//        return new ResponseEntity<>(responseDTOS, HttpStatus.OK);
//    }
}
