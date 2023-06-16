package com.sugadev.videoservice.service;

import com.sugadev.videoservice.dto.VideoDTO;
import com.sugadev.videoservice.model.Video;

import java.util.List;

public interface VideoServices {

    VideoDTO saveVideo(VideoDTO videoDTO);

    VideoDTO getVideoById(Integer videoID);

    void deleteById(Integer videoID);

    List<VideoDTO> findAll();

    VideoDTO updateVideo( int videoID,VideoDTO videoDTO);


}
