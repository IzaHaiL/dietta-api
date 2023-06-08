package com.sugadev.videoservice.service;

import com.sugadev.videoservice.model.Video;

public interface VideoService {

    Video saveVideo(Video video);

    Video getVideoById(Integer videoId);

    void deleteById(Integer videoId);

    Iterable<Video> findAll();

    Video updateVideo(Video updatedVideo);

}
