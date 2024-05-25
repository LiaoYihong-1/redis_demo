package com.example.videoBack.controllers;

import com.example.videoBack.dao.model.User;
import com.example.videoBack.dao.request.AccountRequest;
import com.example.videoBack.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/user")
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AccountRequest u){
        return accountService.register(u);
    }
    @GetMapping("/login")
    public ResponseEntity<?> login(@Valid @NotBlank @NotNull @RequestParam String email,
                                   @Valid @NotBlank @NotNull @RequestParam String password) throws JsonProcessingException {
        return accountService.login(email, password) ;
    }
}
