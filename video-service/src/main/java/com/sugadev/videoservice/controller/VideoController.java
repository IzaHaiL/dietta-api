package com.sugadev.videoservice.controller;


import com.sugadev.videoservice.dto.VideoDTO;
import com.sugadev.videoservice.model.Video;
import com.sugadev.videoservice.service.VideoServices;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/video")
@RestController
public class VideoController {


    @Autowired
    private VideoServices videoServices;

    private ModelMapper modelMapper;


    @PostMapping("/add")
    public ResponseEntity<VideoDTO> saveVideo(@RequestBody VideoDTO videoDTO){
        VideoDTO video = videoServices.saveVideo(videoDTO);
        return new ResponseEntity<>(video, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<VideoDTO>>findAll(){
        List<VideoDTO> videoDTO = videoServices.findAll();
        return new ResponseEntity<>(videoDTO, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<VideoDTO>getVideoById( @PathVariable("id") Integer id){
        VideoDTO videoDTO = videoServices.getVideoById(id);
        return new ResponseEntity<>(videoDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<VideoDTO> updateVideo( @PathVariable("id") Integer id, @RequestBody VideoDTO videoDTO) {
        VideoDTO updateVideo = videoServices.updateVideo(id, videoDTO);
        return new ResponseEntity<>(updateVideo, HttpStatus.OK);
    }





    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Video> deleteVideo(@PathVariable("id") Integer id) {
        try {
            videoServices.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





}
