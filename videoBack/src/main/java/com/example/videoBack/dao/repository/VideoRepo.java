package com.example.videoBack.dao.repository;

import com.example.videoBack.dao.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepo extends JpaRepository<Video, Integer>, JpaSpecificationExecutor<Video> {
}
