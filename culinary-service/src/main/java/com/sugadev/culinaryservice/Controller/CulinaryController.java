package com.sugadev.culinaryservice.Controller;

import com.sugadev.culinaryservice.Dto.CulinaryDTO;
import com.sugadev.culinaryservice.Model.Culinary;
import com.sugadev.culinaryservice.Services.CulinaryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/culinary")
@AllArgsConstructor

public class CulinaryController {
    @Autowired
    private CulinaryService culinaryService;
    private ModelMapper modelMapper;


    @PostMapping("/add")
    public ResponseEntity<CulinaryDTO> saveCulinary(@RequestBody CulinaryDTO culinaryDTO){
        CulinaryDTO createdCulinary = culinaryService.saveCulinary(culinaryDTO);
        return new ResponseEntity<>(createdCulinary, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<CulinaryDTO> getCulinary(@PathVariable("id") Integer culinaryId){
        CulinaryDTO culinaryDTO = culinaryService.getCulinary(culinaryId);
        return new ResponseEntity<>(culinaryDTO, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CulinaryDTO>>getAllCulinary(){
        List<CulinaryDTO> culinaryDTO = culinaryService.getAllCulinary();
        return new ResponseEntity<>(culinaryDTO, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CulinaryDTO> updateCulinary( @PathVariable("id") Integer id, @RequestBody CulinaryDTO culinaryDTO) {
        CulinaryDTO updatedCulinary = culinaryService.updateCulinary(id, culinaryDTO);
        return new ResponseEntity<>(updatedCulinary, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Culinary> deleteCulinary(@PathVariable("id") Integer id) {
        try {
            culinaryService.deleteCulinary(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}