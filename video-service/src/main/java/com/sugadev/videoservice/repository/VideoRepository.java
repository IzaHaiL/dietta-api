package com.sugadev.videoservice.repository;

import com.sugadev.videoservice.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Integer>{
    @Query("SELECT sv FROM Video sv WHERE sv.category = :category")
    List<Video> getVideoByCategory(@Param("category") String category);
}
