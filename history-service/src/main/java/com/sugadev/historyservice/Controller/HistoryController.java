package com.sugadev.historyservice.Controller;


import com.sugadev.historyservice.Dto.HistoryDTO;


import com.sugadev.historyservice.Dto.ResponseDTO;
import com.sugadev.historyservice.Dto.ResponseDTOV2;
import com.sugadev.historyservice.Model.History;
import com.sugadev.historyservice.Services.HistoryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/history")
@AllArgsConstructor

public class HistoryController {

        @Autowired
        private HistoryService historyService;
        private ModelMapper modelMapper;


        @PostMapping("/add")

        public ResponseEntity<HistoryDTO> saveHistory(@RequestBody HistoryDTO historyDto){
            HistoryDTO createdHistory = historyService.saveHistory(historyDto);

            return new ResponseEntity<>(createdHistory, HttpStatus.CREATED);
        }
        @GetMapping("{id}")
        public ResponseEntity<ResponseDTO> getHistory(@PathVariable("id") Integer historyId){
            ResponseDTO responseDTO = historyService.getHistory(historyId);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }

        @GetMapping("/v2/{id}")
        public ResponseEntity<ResponseDTOV2> getHistory2(@PathVariable("id") Integer history2Id){
            ResponseDTOV2 responseDTOV2 = historyService.getHistory2(history2Id);
            return new ResponseEntity<>(responseDTOV2, HttpStatus.OK);


        }



        @GetMapping("/all")

        public ResponseEntity<List<HistoryDTO>>getAllHistory(){
            List<HistoryDTO> historyDto = historyService.getAllHistory();

            return new ResponseEntity<>(historyDto, HttpStatus.OK);
        }

//    @PutMapping("/update/{id}")
//    public ResponseEntity<HistoryDto> updateHistory( @PathVariable("id") Integer id, @RequestBody HistoryDto historyDto) {
//        HistoryDto updatedHistory = historyService.updateHistory(id, historyDto);
//        return new ResponseEntity<>(updatedHistory, HttpStatus.OK);
//    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<History> deleteCulinary(@PathVariable("id") Integer id){
            try {
                historyService.deleteHistory(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
}
