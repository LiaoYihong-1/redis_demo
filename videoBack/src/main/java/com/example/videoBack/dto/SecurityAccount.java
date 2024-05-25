package com.example.videoBack.dto;

import lombok.Data;

import java.util.List;

@Data
public class SecurityAccount {
    Integer id;
    String email;
    String password;
    List<String> permissions;
}
