package com.example.videoBack.controllers;

import com.example.videoBack.dto.SecurityAccount;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @GetMapping("/test")
    @PreAuthorize("hasAnyAuthority('LEAVE_COMMENT')")
    public String test(){
        SecurityAccount user = (SecurityAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user.getEmail());
        System.out.println(user.getPermissions());
        return "sad";
    }
}
