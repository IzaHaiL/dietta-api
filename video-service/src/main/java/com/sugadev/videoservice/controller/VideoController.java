package com.sugadev.videoservice.controller;


import com.sugadev.videoservice.model.Video;
import com.sugadev.videoservice.service.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/video")
@AllArgsConstructor
public class VideoController {

    private VideoService videoService;

    @PostMapping("/add")
    public ResponseEntity<Video> saveVideo(@RequestBody Video video){
        Video saveVideo = videoService.saveVideo(video);
        return new ResponseEntity<>(saveVideo, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Video> findAll() {
        return videoService.findAll();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Video> updateVideo(@PathVariable("id") Integer id, @RequestBody Video updatedVideo) {
        updatedVideo.setId(id);
        Video updated = videoService.updateVideo(updatedVideo);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<Video> getVideoById(@PathVariable("id") Integer videoID){
        Video video = videoService.getVideoById(videoID);
        return ResponseEntity.ok(video);
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<Video> deleteVideo(@PathVariable("id") Integer id) {
        try {
            videoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
