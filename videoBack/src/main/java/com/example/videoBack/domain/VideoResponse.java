package com.example.videoBack.domain;

import lombok.Data;

@Data
public class VideoResponse {
    private byte [] video;
    private String name;
    private Integer id;
}
