package com.sugadev.videoservice.service;

import com.sugadev.videoservice.VideoRepository.VideoRepository;
import com.sugadev.videoservice.model.Video;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class VideoServ implements VideoService {
    @Autowired
    private VideoRepository videoRepository;


    @Override
    public Video saveVideo(Video video) {
        return videoRepository.save(video);
    }

    @Override
    public Video getVideoById(Integer videoId) {
        return videoRepository.findById(videoId).get();
    }

    @Override
    public void deleteById(Integer videoId) {
        videoRepository.deleteById(videoId);
    }

    @Override
    public Iterable<Video> findAll() {
        return videoRepository.findAll();
    }

    @Override
    public Video updateVideo(Video updatedVideo) {
        Video existingVideo = videoRepository.findById(updatedVideo.getId())
                .orElseThrow(() -> new IllegalArgumentException("Video not found with id: " + updatedVideo.getId()));

        existingVideo.setTittle(updatedVideo.getTittle());
        existingVideo.setDescription(updatedVideo.getDescription());
        existingVideo.setType(updatedVideo.getType());
        existingVideo.setUrl(updatedVideo.getUrl());

        return videoRepository.save(existingVideo);
    }


}
