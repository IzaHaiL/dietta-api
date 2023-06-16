package com.sugadev.videoservice.service;

import com.sugadev.videoservice.dto.VideoDTO;
import com.sugadev.videoservice.model.Video;
import com.sugadev.videoservice.repository.VideoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
@Slf4j
public class VideoService implements VideoServices{


    VideoRepository videoRepository;
    ModelMapper modelMapper;

    @Override
    public VideoDTO saveVideo(VideoDTO videoDTO) {
        Video video = modelMapper.map(videoDTO, Video.class);
        Video savedVideo = videoRepository.save(video);
        return modelMapper.map(savedVideo, VideoDTO.class);
    }

    @Override
    public VideoDTO getVideoById(Integer videoID) {
        Video video = videoRepository.findById(videoID).orElseThrow(() -> new RuntimeException("User not found with id: " + videoID));
        return modelMapper.map(video, VideoDTO.class);
    }



    @Override
    public List<VideoDTO> findAll() {
        List<Video> videos = videoRepository.findAll();
        return videos.stream().map(video -> modelMapper.map(video, VideoDTO.class)).collect(Collectors.toList());
    }

    @Override
    public VideoDTO updateVideo(int videoID,VideoDTO videoDTO) {
        Video existing = videoRepository.findById(videoID).orElseThrow(() -> new IllegalArgumentException("Detail not found by id : " + videoDTO.getId_video()));

        existing.setTitle(videoDTO.getTitle());
        existing.setDescription(videoDTO.getDescription());
        existing.setCategory(videoDTO.getCategory());
        existing.setUrl(videoDTO.getUrl());


        Video updatedVideo = videoRepository.save(existing);
        return modelMapper.map(updatedVideo, VideoDTO.class);
    }

    @Override
    public void deleteById(Integer videoID) {
        videoRepository.deleteById(videoID);

    }
}

