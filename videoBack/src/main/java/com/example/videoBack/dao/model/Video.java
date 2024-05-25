package com.example.videoBack.dao.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="video")
@Data
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,name="name")
    private String name;

    @Column(nullable = false,name="path")
    private String path;

}
