package com.sugadev.videoservice.repository;

import com.sugadev.videoservice.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Integer>{
}
