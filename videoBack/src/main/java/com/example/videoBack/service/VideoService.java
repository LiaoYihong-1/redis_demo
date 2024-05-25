package com.example.videoBack.service;

import com.example.videoBack.dao.model.Video;
import com.example.videoBack.dao.repository.VideoRepo;
import com.example.videoBack.domain.VideoResponse;
import com.example.videoBack.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
public class VideoService {
    @Autowired
    VideoRepo videoRepo;
    public ResponseEntity<?> getVideo(Integer id) throws ResourceNotFoundException {
        Optional<Video> optionalVideo = videoRepo.findById(id);
        if(optionalVideo.isEmpty()) throw new ResourceNotFoundException("No this video");
        try {
            // 加载视频资源
            Resource resource = new ClassPathResource(optionalVideo.get().getPath());
            // 读取视频资源的输入流
            try (InputStream inputStream = resource.getInputStream()) {
                // 将视频内容读取到字节数组中
                byte[] videoBytes = inputStream.readAllBytes();

                // 设置响应头
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.setContentLength(videoBytes.length);
                headers.setContentDispositionFormData("attachment", optionalVideo.get().getName());
                // 返回视频内容
                return new ResponseEntity<>(videoBytes, headers, HttpStatus.OK);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
