package com.example.videoBack.controllers;

import com.example.videoBack.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/videos")
public class VideoController {
    @Autowired
    VideoService videoService;
    @GetMapping("/test")
    public ResponseEntity<?> getVideoTest(){
        try {
            // 加载视频资源
            Resource resource = new ClassPathResource("/videos/1/1.mp4");

            // 读取视频资源的输入流
            try (InputStream inputStream = resource.getInputStream()) {
                // 将视频内容读取到字节数组中
                byte[] videoBytes = inputStream.readAllBytes();

                // 设置响应头
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.setContentLength(videoBytes.length);
                headers.setContentDispositionFormData("attachment", "test.mp4");
                // 返回视频内容
                return new ResponseEntity<>(videoBytes, headers, HttpStatus.OK);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getVideo(@PathVariable Integer id){
        return videoService.getVideo(id);
    }
}
