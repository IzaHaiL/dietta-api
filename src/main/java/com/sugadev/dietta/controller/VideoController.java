package com.sugadev.dietta.controller;

import com.sugadev.dietta.model.User;
import com.sugadev.dietta.model.Video;
import com.sugadev.dietta.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


    @Controller
    @RequestMapping(path = "/video")
    public class VideoController {

        @Autowired
        VideoRepository videoRepository;

        @PostMapping(path = "/add")
        public @ResponseBody String addVideo(@RequestParam String judul, @RequestParam String deskripsi, @RequestParam String kategori, @RequestParam String urlvideo) {

            Video video = new Video();
            video.setJudul(judul);
            video.setDeskripsi(deskripsi);
            video.setKategori(kategori);
            video.setUrlvideo(urlvideo);
            videoRepository.save(video);
            return "saved";

        }

        @GetMapping(path = "/all")
        public @ResponseBody Iterable<Video> getAllVideo() {
            return videoRepository.findAll();
        }

    }
